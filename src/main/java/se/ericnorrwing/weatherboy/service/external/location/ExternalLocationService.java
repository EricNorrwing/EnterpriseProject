package se.ericnorrwing.weatherboy.service.external.location;

import reactor.core.publisher.Flux;
import se.ericnorrwing.weatherboy.model.external.location.LocationDetails;

public interface ExternalLocationService {
    Flux<LocationDetails> getLocationByName(String cityName);
}
