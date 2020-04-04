package secure.open.standard.backend.security;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.RSADecrypter;
import com.nimbusds.jose.crypto.RSAEncrypter;
import com.nimbusds.jose.jwk.KeyUse;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.gen.RSAKeyGenerator;
import com.nimbusds.jwt.EncryptedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.text.ParseException;
import java.util.UUID;

@Service
public class JweService {


    @Autowired
    KeyService keyService;

    public String decrypt(String jweString) throws JOSEException, ParseException {

        JWEObject jwe = JWEObject.parse(jweString);
        JWEDecrypter decrypter = new RSADecrypter(keyService.getKey().toPrivateKey());

        jwe.decrypt(decrypter);

        return jwe.getPayload().toString();
    }

    public String encrypt(String payload) throws JOSEException, ParseException, NoSuchAlgorithmException {
        // Generate the preset Content Encryption (CEK) key
        JWEAlgorithm alg = JWEAlgorithm.RSA_OAEP_256;
        EncryptionMethod enc = EncryptionMethod.A128CBC_HS256;
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(enc.cekBitLength());
        SecretKey cek = keyGenerator.generateKey();
        JWEObject jwe = new JWEObject(
                new JWEHeader(alg, enc),
                new Payload(payload));
        jwe.encrypt(new RSAEncrypter(keyService.getKey().toRSAPublicKey(), cek));
        return jwe.serialize();
    }
}
