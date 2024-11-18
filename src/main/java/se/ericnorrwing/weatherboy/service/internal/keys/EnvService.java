package se.ericnorrwing.weatherboy.service.internal.keys;


import org.springframework.stereotype.Service;


@Service
public class EnvService {


    public String getWeatherApiKey() {
        return System.getenv("WEATHER_API_KEY");
    }

    public String getPostgresUsername() {
        return System.getenv("POSTGRES_USERNAME");
    }

    public String getPostgresPassword() {
        return System.getenv("POSTGRES_PASSWORD");
    }

    public String getWeatherApiBaseUrl () {
        return System.getenv("WEATHER_API_BASE_URL");
    }
}
