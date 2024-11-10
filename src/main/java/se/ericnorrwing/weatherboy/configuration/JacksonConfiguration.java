package se.ericnorrwing.weatherboy.configuration;

import org.n52.jackson.datatype.jts.JtsModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.fasterxml.jackson.databind.Module;

@Configuration
public class JacksonConfiguration {

    @Bean
    public Module javaTopologyModule () {
        return new JtsModule();
    }
}
