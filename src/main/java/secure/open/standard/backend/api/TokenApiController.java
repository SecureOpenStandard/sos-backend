package secure.open.standard.backend.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Optional;
import java.util.Random;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-04-03T17:12:46.725+02:00")

@Controller
@Api(value = "token", description = "the token API")
@RequestMapping(value = "")
public class TokenApiController  {

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public TokenApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    @ApiOperation(value = "",
            nickname = "getToken",
            notes = "returns a simple token, spice it up with the surveilance and authority you like.",
            response = String.class,
            tags={ "Authority"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = String.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = Object.class) })
    @RequestMapping(value = "/token",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<String> getToken() {
        byte[] array = new byte[64]; // length is bounded by 64
        new Random().nextBytes(array);
        String generatedString = new String(array, Charset.forName("UTF-8"));
        return new ResponseEntity<String>(generatedString, HttpStatus.OK);
    }

}
