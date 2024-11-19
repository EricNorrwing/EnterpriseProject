package se.ericnorrwing.weatherboy.configuration.client;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import reactor.core.publisher.Flux;
import se.ericnorrwing.weatherboy.model.external.location.ResponseItem;

@HttpExchange
public interface ExternalWeatherClient {

    //TODO Fix weatherClient
    @GetExchange("/direct")
    Flux<ResponseItem> getWeatherByLocation(@RequestParam("lat") double lat,
                                            @RequestParam("lon") double lon,
                                            @RequestParam("appid") String apiKey);
}
