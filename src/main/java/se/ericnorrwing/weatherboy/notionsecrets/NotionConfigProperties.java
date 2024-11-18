package se.ericnorrwing.weatherboy.notionsecrets;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("notion")
public record NotionConfigProperties(String weatherApiBaseUrl, String externalWeatherApiKey, String postGresUsername, String postGresPassword) {}
