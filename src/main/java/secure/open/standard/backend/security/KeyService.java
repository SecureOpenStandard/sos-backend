package secure.open.standard.backend.security;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.jwk.KeyUse;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.gen.RSAKeyGenerator;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

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
public class KeyService {


    private RSAKey currentJwk = null;

    private String keyStoreFile = "myKeyStore.jsk";
    private String keyStoreSecret = "secret";

    private String rsaKeyPin = "1234";

    private String keyFileName = "key.json";

    @EventListener
    public void onApplicationEvent(final ApplicationStartedEvent event) throws Exception {
        loadOrGenerateKey();
    }

    public RSAKey getKey() {
        return currentJwk;
    }

    private void loadOrGenerateKey() throws JOSEException, CertificateException, NoSuchAlgorithmException, KeyStoreException, IOException {

        if (currentJwk != null) {
            return;
        }

        try {
            this.currentJwk = loadKey();
        } catch (Exception e) {
            this.currentJwk = generateAndStoreNewKey();
        }

    }

    private RSAKey generateAndStoreNewKey() throws JOSEException, CertificateException, NoSuchAlgorithmException, KeyStoreException, IOException {
        // Generate 2048-bit RSA key pair in JWK format, attach some metadata
        RSAKey jwk = new RSAKeyGenerator(2048)
                .keyUse(KeyUse.ENCRYPTION) // indicate the intended use of the key
                .keyID(UUID.randomUUID().toString()) // give the key a unique ID
                .generate();
        storeKey(jwk);
        return jwk;

    }

    private KeyStore getKeyStore() throws KeyStoreException, IOException, CertificateException, NoSuchAlgorithmException {
        // Specify the key store type, e.g. JKS
        KeyStore keyStore = KeyStore.getInstance("JKS");

        // If you need a password to unlock the key store
        char[] password = keyStoreSecret.toCharArray();

        // Load the key store from file
        keyStore.load(new FileInputStream(keyStoreFile), password);

        return keyStore;
    }


    private RSAKey loadKey() throws IOException, KeyStoreException, CertificateException, NoSuchAlgorithmException, JOSEException, ParseException {

        //KeyStore keyStore = getKeyStore();
        // Extract keys and output into JWK set; the secord parameter allows lookup
        // of passwords for individual private and secret keys in the store
        // JWKSet jwkSet = JWKSet.load(keyStore, null);
        //         this.currentJwk = RSAKey.load(keyStore, "key", "1234".toCharArray());

        String data = new String(Files.readAllBytes(Paths.get(keyFileName)));
        return RSAKey.parse(data);

    }

    private void storeKey(RSAKey jwk) throws IOException {

        BufferedWriter writer = new BufferedWriter(new FileWriter(keyFileName, true));
        writer.append(jwk.toJSONString());
        writer.close();

        //RSAPrivateKey privateKey = (RSAPrivateKey)jwk.toKeyPair().getPrivate();


        // JWKSet localKeys = JWKSet.load(new File("my-key-store.json"));
    }
}
