package se.ericnorrwing.weatherboy.externalResponse;

import org.junit.jupiter.api.Test;
import se.ericnorrwing.weatherboy.model.external.weather.*;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WeatherResponseTest {

    //TODO write proper tests

        @Test
        void testWeatherResponse() {
            WeatherDetails response = new WeatherDetails(
                    new Coordinate(10.99, 44.34),
                    List.of(new WeatherDescription(803, "Clouds", "broken clouds", "04d")),
                    "stations",
                    new BasicWeatherInformation(278.39, 273.65, 276.42, 280.3, 1000, 57, 1000, 932),
                    10000,
                    new Wind(7.95, 303, 14.22),
                    new Clouds(77),
                    1732267953L,
                    new ApiParameters(2, 2004688, "IT", 1732256490L, 1732290201L),
                    3600,
                    3163858,
                    "Zocca",
                    200,
                    Optional.empty(),
                    Optional.of(new Rain(0.25))
            );

            assertEquals("Zocca", response.name());
            assertEquals(278.39, response.main().temp());
        }
    }

