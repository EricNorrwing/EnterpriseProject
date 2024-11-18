package se.ericnorrwing.weatherboy.model.external.response;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import reactor.core.publisher.Flux;

public record Response(

	@JsonProperty("Response")
	Flux<ResponseItem> response
) {
}