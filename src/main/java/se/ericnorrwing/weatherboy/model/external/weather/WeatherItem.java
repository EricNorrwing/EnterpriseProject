package se.ericnorrwing.weatherboy.model.external.weather;

public record WeatherItem(String icon, String description,  String main, int id) {}


	// TODO
	// ICON URL SAMPLE https://openweathermap.org/img/wn/10d@2x.png
	// https://openweathermap.org/img/wn/<STRINGHERE>@2x.png