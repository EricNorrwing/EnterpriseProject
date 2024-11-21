package se.ericnorrwing.weatherboy.model.external.location.dto;

import lombok.Getter;
import lombok.Setter;
import se.ericnorrwing.weatherboy.model.external.location.City;

@Getter
@Setter
public class LocationDTO {
    private String cityName;
    private double latitude;
    private double longitude;

    // Constructor
    public LocationDTO(String cityName, double latitude, double longitude) {
        this.cityName = cityName;
        this.latitude = latitude;
        this.longitude = longitude;
    }



}
