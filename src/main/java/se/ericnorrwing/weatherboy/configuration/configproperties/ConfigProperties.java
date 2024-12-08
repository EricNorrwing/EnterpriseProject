package se.ericnorrwing.weatherboy.configuration.configproperties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("weatherboy-secrets")
public record ConfigProperties(
        String locationApiBaseUrl,
        String weatherApiBaseUrl,
        String externalWeatherApiKey,
        String postGresUsername,
        String postGresPassword,
        String postGresDBName,
        String JwtSecretPrivateKey,
        String JwtSecretPublicKey
) {}
