package se.ericnorrwing.weatherboy.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import se.ericnorrwing.weatherboy.configuration.client.ExternalLocationClient;
import se.ericnorrwing.weatherboy.service.internal.keys.EnvService;

@Configuration
public class WebClientConfiguration {

    private final EnvService keyService;

    public WebClientConfiguration(EnvService keyService) {
        this.keyService = keyService;
    }

    @Bean
    public WebClient client(WebClient.Builder builder) {
        return builder
                .baseUrl(keyService.getWeatherApiBaseUrl())
                .build();
    }

    @Bean
    public ExternalLocationClient externalLocationClient(WebClient client) {
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(WebClientAdapter.create(client)).build();
        return factory.createClient(ExternalLocationClient.class);
    }


}
