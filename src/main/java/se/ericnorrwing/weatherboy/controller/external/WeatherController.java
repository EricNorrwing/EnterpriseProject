package se.ericnorrwing.weatherboy.controller.external;



import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import se.ericnorrwing.weatherboy.model.external.location.LocationDetails;
import se.ericnorrwing.weatherboy.model.external.weather.WeatherDetails;
import se.ericnorrwing.weatherboy.service.external.location.ExternalLocationService;
import se.ericnorrwing.weatherboy.service.external.weather.ExternalWeatherService;

@RestController
@RequestMapping("/api")
public class WeatherController {

    private final ExternalLocationService externalLocationService;
    private final ExternalWeatherService externalWeatherService;


    public WeatherController(ExternalLocationService externalLocationService, ExternalWeatherService externalWeatherService) {
        this.externalLocationService = externalLocationService;
        this.externalWeatherService = externalWeatherService;
    }

    @GetMapping("/location")
    public Flux<LocationDetails> getLocationByName(@RequestParam String cityName) {
        return externalLocationService.getLocationByName(cityName);
    }

    @GetMapping("/weather")
    public Flux<WeatherDetails> getWeatherByLocation(@RequestParam String cityName) {
        return externalWeatherService.getWeatherByLocationName(cityName);
    }

    @GetMapping("/test")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Flux<WeatherDetails> getTestWeather() {
        return externalWeatherService.getWeatherByLocationName("Stockholm");
    }



}

