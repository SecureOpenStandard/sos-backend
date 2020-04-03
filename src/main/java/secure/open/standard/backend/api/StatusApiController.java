package secure.open.standard.backend.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import secure.open.standard.backend.events.UserInfected;
import secure.open.standard.backend.model.Body;
import secure.open.standard.backend.model.InlineResponse200;
import secure.open.standard.backend.services.UserInfectedService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.IOException;
import java.util.Optional;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-04-03T17:12:46.725+02:00")

@Controller
public class StatusApiController implements StatusApi {

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired()
    UserInfectedService userInfectedService;

    @org.springframework.beans.factory.annotation.Autowired
    public StatusApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    @Override
    public Optional<ObjectMapper> getObjectMapper() {
        return Optional.ofNullable(objectMapper);
    }

    @Override
    public Optional<HttpServletRequest> getRequest() {
        return Optional.ofNullable(request);
    }



    @ApiOperation(value = "", nickname = "postStatus", notes = "push your seen reference IDs and the \"message\"  cascade with other systems -> tokenid based idempotency ", tags={ "required", })
    @ApiResponses(value = {
            @ApiResponse(code = 202, message = "Accepted"),
            @ApiResponse(code = 401, message = "Unauthorized", response = Object.class) })
    @RequestMapping(value = "/status",
            produces = { "application/json" },
            consumes = { "application/json" },
            method = RequestMethod.POST)
    public ResponseEntity<Void> postStatus(@ApiParam(value = "JWT one time validity! ", required = true) @RequestHeader(value = "Authorization", required = true) String authorization, @ApiParam(value = "") @Valid @RequestBody Body body) {
        if(getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {

            this.userInfectedService.send(new UserInfected("1", body.getRefids()));
        } else {
            log.warn("ObjectMapper or HttpServletRequest not configured in default StatusApi interface so no example is generated");
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

}
