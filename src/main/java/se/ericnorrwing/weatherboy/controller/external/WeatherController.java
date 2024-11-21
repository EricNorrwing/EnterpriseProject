package se.ericnorrwing.weatherboy.controller.external;



import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import se.ericnorrwing.weatherboy.model.external.location.LocationDetails;
import se.ericnorrwing.weatherboy.notionsecrets.NotionConfigProperties;
import se.ericnorrwing.weatherboy.service.external.location.ExternalLocationService;

@RestController
@RequestMapping("/api")
public class WeatherController {

    private final ExternalLocationService externalLocationService;
    private final NotionConfigProperties notionConfigProperties;


    public WeatherController(ExternalLocationService externalLocationService, NotionConfigProperties notionConfigProperties, NotionConfigProperties notionConfigProperties1) {
        this.externalLocationService = externalLocationService;
        this.notionConfigProperties = notionConfigProperties1;
    }

    @GetMapping("/location")
    public Flux<LocationDetails> getLocationByName(@RequestParam String cityName) {
        return externalLocationService.getLocationByName(cityName);
    }

}

