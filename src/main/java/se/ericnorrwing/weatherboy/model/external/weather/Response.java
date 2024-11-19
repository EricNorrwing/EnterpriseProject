package se.ericnorrwing.weatherboy.model.external.weather;

import java.time.Instant;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
/*TODO VISIBILITY CAPPED AT 10KM*/
/*TODO TimeZone is Shift in seconds from UTC */
//TODO Fix DateTime Conversion
public record Response(@JsonProperty("visibility") int visibilityInMeters, int timezone,
					   @JsonProperty("main") BasicWeatherInformation basicWeatherInformationin,
					   @JsonProperty("clouds") Cloud cloud,
					   @JsonProperty("sys") ApiParameters apiParameters,
					   @JsonProperty("dt") Instant timeOfWeatherCalculation,
					   @JsonProperty("coord") Coordinate coordinate,
					   @JsonProperty("weather") List<WeatherItem> weather,
					   String name,
					   @JsonProperty("cod") int httpStatusCode,
					   int id,
					   @JsonProperty("base") String base,
					   Wind wind,
					   Rain rain,
					   Snow snow

) {}