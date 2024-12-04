package se.ericnorrwing.weatherboy.service.external.weather;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import se.ericnorrwing.weatherboy.configuration.client.ExternalWeatherClient;
import se.ericnorrwing.weatherboy.model.external.location.LocationDetails;
import se.ericnorrwing.weatherboy.model.external.location.dto.LocationDTO;
import se.ericnorrwing.weatherboy.model.external.weather.WeatherDetails;
import se.ericnorrwing.weatherboy.configuration.configproperties.ConfigProperties;
import se.ericnorrwing.weatherboy.service.external.location.ExternalLocationService;

@Service
public class ExternalWeatherApiService implements ExternalWeatherService {

    private final ExternalWeatherClient externalWeatherClient;
    private final ExternalLocationService externalLocationService;
    private final ConfigProperties notionConfigProperties;

    public ExternalWeatherApiService(ExternalWeatherClient externalWeatherClient, ExternalLocationService externalLocationService, ConfigProperties notionConfigProperties) {
        this.externalWeatherClient = externalWeatherClient;
        this.externalLocationService = externalLocationService;
        this.notionConfigProperties = notionConfigProperties;
    }

    //TODO Convert to Coords

    @Override
    public Flux<WeatherDetails> getWeatherByLocationName(String cityName) {
        Flux<LocationDetails> locationDetailsFlux = externalLocationService.getLocationByName(cityName);

        return locationDetailsFlux
                .map(locationDetails -> new LocationDTO(
                        locationDetails.name(),
                        locationDetails.latitude(),
                        locationDetails.longitude()
                ))
                .flatMap(locationDTO -> externalWeatherClient.getWeatherByLocation(
                        locationDTO.getLatitude(),
                        locationDTO.getLongitude(),
                        notionConfigProperties.externalWeatherApiKey()
                ));
    }
}
