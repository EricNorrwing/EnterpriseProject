package se.ericnorrwing.weatherboy.externalResponse;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultHandler;
import se.ericnorrwing.weatherboy.controller.external.WeatherController;
import se.ericnorrwing.weatherboy.handler.exception.LocationNotFoundException;
import se.ericnorrwing.weatherboy.service.external.weather.ExternalWeatherService;

@SpringBootTest
@AutoConfigureMockMvc
public class WeatherControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ExternalWeatherService externalWeatherService;

    @Autowired
    WeatherController weatherController;
    private String token;

    @BeforeEach
    public void fetchToken() throws Exception {
        String response = mockMvc.perform(post("/auth/login")
                        .contentType("application/json")
                        .content("{\"username\": \"EricN\", \"password\": \"any12345\"}"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        ObjectMapper objectMapper = new ObjectMapper();
        token = objectMapper.readTree(response).get("token").asText();
    }

    @Test
    public void testThatInvalidCityNameReturns404() throws Exception {
        mockMvc.perform(get("/api/weather")
                        .param("cityName", "Gibberish12345")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().is(404));
    }

    @Test
    public void testThatServiceThrowsLocationNotFoundException() {
        assertThrows(LocationNotFoundException.class, () -> {
            externalWeatherService.getWeatherByLocationName("Gibberish12345").blockFirst();
        });
    }


    @Test
    public void testThatValidLocationReturns200() throws Exception {
        mockMvc.perform(get("/api/weather")
                        .param("cityName", "Stockholm")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk());
    }
}
