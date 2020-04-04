package secure.open.standard.backend.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.security.interfaces.RSAPublicKey;
import java.util.List;


public class KeysResponse {
    @JsonProperty("keys")
    public List<JsonRsaKeyResponse> keys = null;
}

