package se.ericnorrwing.weatherboy.controller.external;



import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import se.ericnorrwing.weatherboy.model.external.response.ResponseItem;
import se.ericnorrwing.weatherboy.service.external.location.ExternalLocationService;

@RestController
@RequestMapping("/api")
public class WeatherController {

    private final ExternalLocationService externalLocationService;

    public WeatherController(ExternalLocationService externalLocationService) {
        this.externalLocationService = externalLocationService;
    }

    @GetMapping("/location")
    public Flux<ResponseItem> getLocationByName(@RequestParam String cityName) {
        return externalLocationService.getLocationByName(cityName);
    }
}

