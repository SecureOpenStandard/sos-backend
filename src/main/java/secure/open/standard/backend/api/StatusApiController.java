package secure.open.standard.backend.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import secure.open.standard.backend.events.UserInfected;
import secure.open.standard.backend.model.Body;
import secure.open.standard.backend.model.PublishedUuidsResponse;
import secure.open.standard.backend.security.JweService;
import secure.open.standard.backend.services.UserInfectedService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;

@Controller
@Api(value = "status")
@RequestMapping(value = "status")
public class StatusApiController {

    private ObjectMapper objectMapper;

    private HttpServletRequest request;

    @Autowired
    private JweService jweService;

    @Autowired()
    UserInfectedService userInfectedService;

    @org.springframework.beans.factory.annotation.Autowired
    public StatusApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }


    @ApiOperation(value = "", nickname = "postStatus", notes = "push your seen reference PLAIN IDs and the \"message\"  cascade with other systems -> tokenid based idempotency ", tags = {"IDs"})
    @ApiResponses(value = {
            @ApiResponse(code = 202, message = "Accepted"),
            @ApiResponse(code = 401, message = "Unauthorized", response = Object.class)})
    @RequestMapping(value = "plain",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.POST)
    public ResponseEntity<Void> postStatus(@ApiParam(value = "JWT one time validity! ", required = true) @RequestHeader(value = "Authorization", required = true) String authorization, @ApiParam(value = "") @Valid @RequestBody Body body) {

        ArrayList<String> encryptedIds = new ArrayList<>();
        for (String s : body.getRefids()) {
            try {
                encryptedIds.add(jweService.encrypt(s));
            } catch (Exception e) {
                e.printStackTrace();
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }

        this.userInfectedService.send(new UserInfected("1", encryptedIds));

        return new ResponseEntity<>(HttpStatus.ACCEPTED);

    }

    @ApiOperation(value = "", nickname = "postStatusJwe", notes = "push your seen reference IDs as JWE and the \"message\"  cascade with other systems -> tokenid based idempotency ", tags = {"IDs"})
    @ApiResponses(value = {
            @ApiResponse(code = 202, message = "Accepted"),
            @ApiResponse(code = 401, message = "Unauthorized", response = Object.class)})
    @RequestMapping(value = "jwe",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.POST)
    public ResponseEntity<Void> postJwe(@ApiParam(value = "JWT one time validity! ", required = true) @RequestHeader(value = "Authorization", required = true) String authorization, @ApiParam(value = "") @Valid @RequestBody Body body) {

        this.userInfectedService.send(new UserInfected("1", body.getRefids()));
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @ApiOperation(value = "", nickname = "getStatus", notes = "returns the current collected uuids ", response = PublishedUuidsResponse.class, tags = {"IDs"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = PublishedUuidsResponse.class),
            @ApiResponse(code = 404, message = "Not Found", response = Object.class)})
    @RequestMapping(value = "/",
            produces = {"application/json"},
            method = RequestMethod.GET)
    public ResponseEntity<PublishedUuidsResponse> getStatus() {
        return new ResponseEntity<PublishedUuidsResponse>(
                new PublishedUuidsResponse(this.userInfectedService.getPublishedKeys()),
                HttpStatus.OK
        );
    }

}
