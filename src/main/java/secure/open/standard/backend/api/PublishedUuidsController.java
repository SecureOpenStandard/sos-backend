/**
 * NOTE: This class is auto generated by the swagger code generator program (2.4.13).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package secure.open.standard.backend.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import secure.open.standard.backend.model.InlineResponse200;
import secure.open.standard.backend.model.PublishedUuidsResponse;
import secure.open.standard.backend.services.UserInfectedService;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;


@Controller
public class PublishedUuidsController implements PublishedUuidsApi {


    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired()
    UserInfectedService userInfectedService;

    @org.springframework.beans.factory.annotation.Autowired
    public PublishedUuidsController(ObjectMapper objectMapper, HttpServletRequest request) {
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

    @ApiOperation(value = "", nickname = "getStatus", notes = "returns the current status ", response = InlineResponse200.class, tags={ "suggested", })
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK", response = PublishedUuidsResponse.class),
        @ApiResponse(code = 404, message = "Not Found", response = Object.class) })
    @RequestMapping(value = "/published-uuids",
        produces = { "application/json" },
        method = RequestMethod.GET)
    public ResponseEntity<PublishedUuidsResponse> getStatus() {
        return new ResponseEntity<PublishedUuidsResponse>(
                new PublishedUuidsResponse(this.userInfectedService.getPublishedKeys()),
                HttpStatus.OK
        );
    }


}