package se.ericnorrwing.weatherboy.configuration.client;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import reactor.core.publisher.Flux;
import se.ericnorrwing.weatherboy.model.external.location.LocationDetails;


@HttpExchange
public interface ExternalLocationClient {
    @GetExchange("/direct")
    Flux<LocationDetails> getLocation(@RequestParam("q") String city,
                                      @RequestParam("limit") int limit,
                                      @RequestParam("appid") String apiKey);


}
