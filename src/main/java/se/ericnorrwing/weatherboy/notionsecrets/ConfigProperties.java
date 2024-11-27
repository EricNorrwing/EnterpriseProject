package se.ericnorrwing.weatherboy.notionsecrets;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("weatherboy-secrets")
public record ConfigProperties(
        String locationApiBaseUrl,
        String weatherApiBaseUrl,
        String externalWeatherApiKey,
        String postGresUsername,
        String postGresPassword,
        String postGresDBName,
        String githubClientId,
        String githubSecret
) {}
