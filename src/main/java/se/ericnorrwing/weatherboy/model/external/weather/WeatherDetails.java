package se.ericnorrwing.weatherboy.model.external.weather;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Optional;

public record WeatherDetails(
        @JsonProperty("coord") Coordinate coordinate,
        @JsonProperty("weather") List<WeatherDescription> weather,
        @JsonProperty("base") String base,
        @JsonProperty("main") BasicWeatherInformation main,
        @JsonProperty("visibility") int visibility,
        @JsonProperty("wind") Wind wind,
        @JsonProperty("clouds") Clouds clouds,
        @JsonProperty("dt") long dt,
        @JsonProperty("sys") ApiParameters sys,
        @JsonProperty("timezone") int timezone,
        @JsonProperty("id") int id,
        @JsonProperty("name") String name,
        @JsonProperty("cod") int cod,
        @JsonProperty("snow") Optional<Snow> snow,
        @JsonProperty("rain") Optional<Rain> rain
) {}

