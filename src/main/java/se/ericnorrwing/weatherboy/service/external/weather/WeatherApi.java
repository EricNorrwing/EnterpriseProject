package se.ericnorrwing.weatherboy.service.external.weather;

import reactor.core.publisher.Mono;

public interface WeatherApi {
    Mono<String> getWeatherByCity(String city);
}
