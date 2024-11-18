package se.ericnorrwing.weatherboy.controller.external;



import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import se.ericnorrwing.weatherboy.model.external.response.ResponseItem;
import se.ericnorrwing.weatherboy.notionsecrets.NotionConfigProperties;
import se.ericnorrwing.weatherboy.service.external.location.ExternalLocationService;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class WeatherController {

    private final ExternalLocationService externalLocationService;
    private final NotionConfigProperties notionConfigProperties;

    public WeatherController(ExternalLocationService externalLocationService, NotionConfigProperties notionConfigProperties) {
        this.externalLocationService = externalLocationService;
        this.notionConfigProperties = notionConfigProperties;
    }

    @GetMapping("/location")
    public Flux<ResponseItem> getLocationByName(@RequestParam String cityName) {
        return externalLocationService.getLocationByName(cityName);
    }

    @GetMapping("/test")
    public Map<String, String> printAllProps() {
        return Map.of("WEATHER_API_BASE_URL", notionConfigProperties.weatherApiBaseUrl(),
                "WEATHER_API_KEY", notionConfigProperties.externalWeatherApiKey());
    }
}

