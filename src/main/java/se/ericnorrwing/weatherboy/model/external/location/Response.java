package se.ericnorrwing.weatherboy.model.external.location;

import com.fasterxml.jackson.annotation.JsonProperty;
import reactor.core.publisher.Flux;

public record Response(

	@JsonProperty("Response")
	Flux<LocationDetails> response
) {
}