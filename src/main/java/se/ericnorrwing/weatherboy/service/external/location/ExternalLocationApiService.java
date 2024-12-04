package se.ericnorrwing.weatherboy.service.external.location;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import se.ericnorrwing.weatherboy.configuration.client.ExternalLocationClient;
import se.ericnorrwing.weatherboy.model.external.location.LocationDetails;
import se.ericnorrwing.weatherboy.configuration.configproperties.ConfigProperties;

@Service
public class ExternalLocationApiService implements ExternalLocationService {

    private final ExternalLocationClient externalLocationClient;
    private final ConfigProperties notionConfigProperties;

    public ExternalLocationApiService(ExternalLocationClient externalLocationClient, ConfigProperties notionConfigProperties) {
        this.externalLocationClient = externalLocationClient;
        this.notionConfigProperties = notionConfigProperties;
    }


    @Override
    public Flux<LocationDetails> getLocationByName(String cityName) {
        return externalLocationClient.getLocation(cityName,1,  notionConfigProperties.externalWeatherApiKey());
    }


}
