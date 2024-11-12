package se.ericnorrwing.weatherboy.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.ericnorrwing.weatherboy.model.internal.WeatherInternalObject;


@Repository
public interface WeatherRepository extends JpaRepository<WeatherInternalObject, Long> {
}
