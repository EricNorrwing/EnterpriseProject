package se.ericnorrwing.weatherboy.controller.external;



import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
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
    public ResponseEntity<Flux<LocationDetails>> getLocationByName(@RequestParam String cityName) {
        Flux<LocationDetails> locationDetails = externalLocationService.getLocationByName(cityName);
        return ResponseEntity.ok(locationDetails);
    }

    @GetMapping("/weather")
    public ResponseEntity<Flux<WeatherDetails>> getWeatherByLocation(@RequestParam String cityName) {
        Flux<WeatherDetails> weatherDetails = externalWeatherService.getWeatherByLocationName(cityName);
        return ResponseEntity.ok(weatherDetails);
    }

    @GetMapping("/test")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Flux<WeatherDetails> getTestWeather() {
        return externalWeatherService.getWeatherByLocationName("Stockholm");
    }

    @GetMapping("/test/user")
    @PreAuthorize("hasRole('SCOPE_read:user')")
    public String testUsers (Authentication authentication) {
        return authentication.getAuthorities().toString();
    }



}

