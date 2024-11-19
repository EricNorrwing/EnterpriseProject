package se.ericnorrwing.weatherboy.model.external.weather;

import com.fasterxml.jackson.annotation.JsonProperty;

//TODO Sealevel pressure is most likely a copy of pressure
public record BasicWeatherInformation(@JsonProperty("temp") Temperature temperature,
									  @JsonProperty("temp_min") Temperature temperatureMinimum,
									  @JsonProperty("grnd_level") int atmosphericPressureAtGroundLevel,
									  @JsonProperty("humidity") double humidityInPercent,
									  @JsonProperty("pressure") int pressureAtSeaLevel,
									  @JsonProperty("sea_level") int atmosphericPressureAtSeaLevel,
									  @JsonProperty("feels_like") Temperature feelsLike,
									  @JsonProperty("temp_max") Temperature tempMax
) {}