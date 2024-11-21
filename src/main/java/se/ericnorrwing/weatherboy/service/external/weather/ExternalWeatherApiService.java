package se.ericnorrwing.weatherboy.service.external.weather;

import reactor.core.publisher.Flux;
import se.ericnorrwing.weatherboy.configuration.client.ExternalWeatherClient;
import se.ericnorrwing.weatherboy.model.external.location.LocationDetails;
import se.ericnorrwing.weatherboy.model.external.location.dto.LocationDTO;
import se.ericnorrwing.weatherboy.model.external.weather.WeatherDetails;
import se.ericnorrwing.weatherboy.notionsecrets.NotionConfigProperties;
import se.ericnorrwing.weatherboy.service.external.location.ExternalLocationService;

public class ExternalWeatherApiService implements ExternalWeatherService {

    private final ExternalWeatherClient externalWeatherClient;
    private final ExternalLocationService externalLocationService;
    private final NotionConfigProperties notionConfigProperties;

    public ExternalWeatherApiService(ExternalWeatherClient externalWeatherClient, ExternalLocationService externalLocationService, NotionConfigProperties notionConfigProperties) {
        this.externalWeatherClient = externalWeatherClient;
        this.externalLocationService = externalLocationService;
        this.notionConfigProperties = notionConfigProperties;
    }

    //TODO Convert to Coords

    @Override
    public Flux<WeatherDetails> getWeatherByLocationName(String cityName) {
        return externalWeatherClient.getWeatherByLocation(externalLocationService.getLocationByName(cityName).
                map(locationDetails -> new LocationDTO(
                        locationDetails.name(),
                        locationDetails.latitude(),
                        locationDetails.longitude())));
    }
}
