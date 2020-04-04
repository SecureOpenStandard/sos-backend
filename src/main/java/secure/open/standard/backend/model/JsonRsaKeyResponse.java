package secure.open.standard.backend.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.security.interfaces.RSAPublicKey;
import java.util.List;


public class JsonRsaKeyResponse {
    @JsonProperty("kty")
    public String kty = null;
    @JsonProperty("n")
    public String n = null;
    @JsonProperty("e")
    public String e = null;

}

