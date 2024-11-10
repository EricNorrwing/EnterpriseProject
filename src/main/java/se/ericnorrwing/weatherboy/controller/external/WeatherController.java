package se.ericnorrwing.weatherboy.controller.external;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import se.ericnorrwing.weatherboy.service.external.weather.OpenWeatherApiService;

@RestController
public class WeatherController {


    //TODO BOILERPLATE
    private final OpenWeatherApiService weatherService;

    @Autowired
    public WeatherController(OpenWeatherApiService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/api/weather")
    public Mono<String> getWeather(@RequestParam String city) {
        return weatherService.getWeatherByCity(city);
    }
}

