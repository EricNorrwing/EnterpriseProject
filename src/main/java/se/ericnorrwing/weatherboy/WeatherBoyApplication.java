package se.ericnorrwing.weatherboy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//WOULDN'T YOU LIKE TO KNOW
@SpringBootApplication
public class WeatherBoyApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeatherBoyApplication.class, args);

        System.out.println("API Key: " + System.getenv("WEATHER_API_KEY"));
    }

}
