package se.ericnorrwing.weatherboy.service.external.location;

import reactor.core.publisher.Flux;
import se.ericnorrwing.weatherboy.model.external.location.ResponseItem;

public interface ExternalLocationService {
    Flux<ResponseItem> getLocationByName(String cityName);
}
