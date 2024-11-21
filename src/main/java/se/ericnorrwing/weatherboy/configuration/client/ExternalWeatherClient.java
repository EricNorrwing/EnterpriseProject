package se.ericnorrwing.weatherboy.configuration.client;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import reactor.core.publisher.Flux;
import se.ericnorrwing.weatherboy.model.external.location.LocationDetails;
import se.ericnorrwing.weatherboy.model.external.weather.WeatherDetails;

@HttpExchange
public interface ExternalWeatherClient {

    @GetExchange("/weather")
    Flux<WeatherDetails> getWeatherByLocation(@RequestParam("lat") double lat,
                                              @RequestParam("lon") double lon,
                                              @RequestParam("appid") String apiKey);
}
