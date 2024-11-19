package se.ericnorrwing.weatherboy.service.external.location;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import se.ericnorrwing.weatherboy.configuration.client.ExternalLocationClient;
import se.ericnorrwing.weatherboy.model.external.location.ResponseItem;
import se.ericnorrwing.weatherboy.notionsecrets.NotionConfigProperties;

@Service
public class ExternalLocationApiService implements ExternalLocationService {

    private final ExternalLocationClient externalLocationClient;
    private final NotionConfigProperties notionConfigProperties;

    public ExternalLocationApiService(ExternalLocationClient externalLocationClient, NotionConfigProperties notionConfigProperties) {
        this.externalLocationClient = externalLocationClient;
        this.notionConfigProperties = notionConfigProperties;
    }


    @Override
    public Flux<ResponseItem> getLocationByName(String cityName) {
        return externalLocationClient.getLocation(cityName,1,  notionConfigProperties.externalWeatherApiKey());
    }


}
