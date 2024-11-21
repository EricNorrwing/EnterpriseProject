package se.ericnorrwing.weatherboy.model.external.weather;

public record WeatherDetails(
        String icon,
        String description,
        BasicWeatherInformation main,
        int id
) {}



