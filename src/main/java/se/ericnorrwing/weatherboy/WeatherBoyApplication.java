package se.ericnorrwing.weatherboy;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.OctetSequenceKey;
import com.nimbusds.jose.jwk.source.ImmutableSecret;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import se.ericnorrwing.weatherboy.configuration.configproperties.ConfigProperties;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.time.Instant;

//WOULDN'T YOU LIKE TO KNOW
@SpringBootApplication
@EnableConfigurationProperties({ConfigProperties.class})
public class WeatherBoyApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeatherBoyApplication.class, args);

    }

}
