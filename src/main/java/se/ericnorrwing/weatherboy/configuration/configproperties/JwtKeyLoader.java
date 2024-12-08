package se.ericnorrwing.weatherboy.configuration.configproperties;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class JwtKeyLoader {

    private static final String PRIVATE_KEY_PATH = "/certs/private_key.pem";
    private static final String PUBLIC_KEY_PATH = "/certs/public_key.pem";

    public static PrivateKey loadPrivateKey() throws Exception {
        ClassPathResource resource = new ClassPathResource(PRIVATE_KEY_PATH);
        try (InputStream inputStream = resource.getInputStream()) {

            String key = new String(inputStream.readAllBytes());
            key = key.replace("-----BEGIN PRIVATE KEY-----", "")
                    .replace("-----END PRIVATE KEY-----", "")
                    .replaceAll("\\s+", "");



            byte[] decodedKey = Base64.getDecoder().decode(key);
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(decodedKey);


            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            return keyFactory.generatePrivate(keySpec);
        }
    }

    public static PublicKey loadPublicKey() throws Exception {
        ClassPathResource resource = new ClassPathResource(PUBLIC_KEY_PATH);
        try (InputStream inputStream = resource.getInputStream()) {

            String key = new String(inputStream.readAllBytes());
            key = key.replace("-----BEGIN PUBLIC KEY-----", "")
                    .replace("-----END PUBLIC KEY-----", "")
                    .replaceAll("\\s+", "");



            byte[] decodedKey = Base64.getDecoder().decode(key);
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(decodedKey);


            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            return keyFactory.generatePublic(keySpec);
        }
    }
}
