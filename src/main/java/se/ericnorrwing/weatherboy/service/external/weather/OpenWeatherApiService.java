package se.ericnorrwing.weatherboy.service.external.weather;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class OpenWeatherApiService implements WeatherApi {

    private final WebClient webClient;

    public OpenWeatherApiService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder
                .baseUrl("https://api.openweathermap.org")
                .build();
    }

    //TODO BOILERPLATE

    public Mono<String> getWeatherByCity(String city) {
        String apiKey = "YOUR_API_KEY";

        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/data/2.5/weather")
                        .queryParam("q", city)
                        .queryParam("appid", apiKey)
                        .queryParam("units", "metric")
                        .build())
                .retrieve()
                .bodyToMono(String.class);
    }
}