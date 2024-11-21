package se.ericnorrwing.weatherboy.service.external.weather;

import reactor.core.publisher.Flux;
import se.ericnorrwing.weatherboy.model.external.weather.WeatherDetails;

public interface ExternalWeatherService {
    Flux<WeatherDetails> getWeatherByLocationName(String cityName);

}
