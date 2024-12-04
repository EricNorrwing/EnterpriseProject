package se.ericnorrwing.weatherboy.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import se.ericnorrwing.weatherboy.configuration.client.ExternalLocationClient;
import se.ericnorrwing.weatherboy.configuration.client.ExternalWeatherClient;
import se.ericnorrwing.weatherboy.configuration.configproperties.ConfigProperties;

@Configuration
public class WebClientConfiguration {

    private final ConfigProperties notionConfigProperties;

    public WebClientConfiguration(ConfigProperties notionConfigProperties) {
        this.notionConfigProperties = notionConfigProperties;
    }

    @Bean(name = "locationWebClient")
    public WebClient locationWebClient(WebClient.Builder builder) {
        return builder
                .baseUrl(notionConfigProperties.locationApiBaseUrl())
                .build();
    }

    @Bean(name = "weatherWebClient")
    public WebClient weatherWebClient(WebClient.Builder builder) {
        return builder
                .baseUrl(notionConfigProperties.weatherApiBaseUrl())
                .build();
    }

    @Bean
    public ExternalLocationClient externalLocationClient(@Qualifier("locationWebClient") WebClient client) {
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(WebClientAdapter.create(client)).build();
        return factory.createClient(ExternalLocationClient.class);
    }

    @Bean
    public ExternalWeatherClient externalWeatherClient(@Qualifier("weatherWebClient") WebClient client) {
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(WebClientAdapter.create(client)).build();
        return factory.createClient(ExternalWeatherClient.class);
    }
}

