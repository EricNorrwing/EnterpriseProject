package se.ericnorrwing.weatherboy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import se.ericnorrwing.weatherboy.notionsecrets.NotionConfigProperties;

//WOULDN'T YOU LIKE TO KNOW
@SpringBootApplication
@EnableConfigurationProperties({NotionConfigProperties.class})
public class WeatherBoyApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeatherBoyApplication.class, args);

    }

}
