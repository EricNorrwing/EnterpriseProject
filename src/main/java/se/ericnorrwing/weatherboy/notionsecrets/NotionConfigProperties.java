package se.ericnorrwing.weatherboy.notionsecrets;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("weatherboy-secrets")
public record NotionConfigProperties(String locationApiBaseUrl,
                                     String weatherApiBaseUrl,
                                     String externalWeatherApiKey,
                                     String postGresUsername,
                                     String postGresPassword,
                                     String githubClientId,
                                     String githubSecret
) {}
