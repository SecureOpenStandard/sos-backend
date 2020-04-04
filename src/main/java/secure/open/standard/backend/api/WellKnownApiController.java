package secure.open.standard.backend.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.jose4j.jwk.JsonWebKey;
import org.jose4j.jwk.JsonWebKeySet;
import org.jose4j.jwk.RsaJsonWebKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import secure.open.standard.backend.model.KeysResponse;
import secure.open.standard.backend.security.KeyService;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-04-03T17:12:46.725+02:00")

@Controller
@Api(value = ".well-known", description = "the .well-known API")
@RequestMapping(value = ".well-known")
public class WellKnownApiController {

    @Autowired
    KeyService keyService;

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public WellKnownApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    @ApiOperation(value = "getPublicKeys", notes = "Returns all well known public keys ", response = KeysResponse.class, tags = {"Keys"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = KeysResponse.class)})
    @RequestMapping(value = "/jwks.json",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<String> getPublickeys() {

        try {
            JsonWebKeySet keySet = new JsonWebKeySet();

            RsaJsonWebKey jwk = new RsaJsonWebKey(keyService.getKey().toRSAPublicKey());
            keySet.addJsonWebKey(jwk);

            return new ResponseEntity<String>(keySet.toJson(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }
}
