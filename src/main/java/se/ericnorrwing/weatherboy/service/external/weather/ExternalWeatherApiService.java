package se.ericnorrwing.weatherboy.service.external.weather;

import reactor.core.publisher.Flux;
import se.ericnorrwing.weatherboy.configuration.client.ExternalWeatherClient;
import se.ericnorrwing.weatherboy.model.external.weather.WeatherItem;
import se.ericnorrwing.weatherboy.notionsecrets.NotionConfigProperties;

public class ExternalWeatherApiService implements ExternalWeatherService {

    private final ExternalWeatherClient externalWeatherClient;
    private final NotionConfigProperties notionConfigProperties;

    public ExternalWeatherApiService(ExternalWeatherClient externalWeatherClient, NotionConfigProperties notionConfigProperties) {
        this.externalWeatherClient = externalWeatherClient;
        this.notionConfigProperties = notionConfigProperties;
    }

    //TODO Convert to Coords

    @Override
    public Flux<WeatherItem> getWeatherByLocationName(String cityName) {
        return externalWeatherClient.getWeatherByLocation(cityName);
    }
}
