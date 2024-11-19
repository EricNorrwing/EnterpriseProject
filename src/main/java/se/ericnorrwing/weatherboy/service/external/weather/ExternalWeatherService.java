package se.ericnorrwing.weatherboy.service.external.weather;

import reactor.core.publisher.Flux;
import se.ericnorrwing.weatherboy.model.external.weather.WeatherItem;

public interface ExternalWeatherService {
    Flux<WeatherItem> getWeatherByLocationName(String cityName);

}
