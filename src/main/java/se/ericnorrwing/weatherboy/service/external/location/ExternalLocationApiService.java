package se.ericnorrwing.weatherboy.service.external.location;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import se.ericnorrwing.weatherboy.configuration.client.ExternalLocationClient;
import se.ericnorrwing.weatherboy.model.external.response.ResponseItem;
import se.ericnorrwing.weatherboy.service.internal.keys.EnvService;

@Service
public class ExternalLocationApiService implements ExternalLocationService {

    private final ExternalLocationClient externalLocationClient;
    private final EnvService keyService;

    public ExternalLocationApiService(ExternalLocationClient externalLocationClient, EnvService keyService) {
        this.externalLocationClient = externalLocationClient;
        this.keyService = keyService;
    }

    @Override
    public Flux<ResponseItem> getLocationByName(String cityName) {
        String apiKey = keyService.getWeatherApiKey();
        return externalLocationClient.getLocation(cityName, apiKey);
    }
}
