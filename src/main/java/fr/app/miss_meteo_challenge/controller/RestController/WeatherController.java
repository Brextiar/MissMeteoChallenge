package fr.app.miss_meteo_challenge.controller.RestController;

import fr.app.miss_meteo_challenge.bo.WeatherResponse;
import fr.app.miss_meteo_challenge.services.WeatherService;
import fr.app.miss_meteo_challenge.services.impl.WeatherServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * RestController to get the weather of the next day for a city
 */
@RestController
@RequestMapping("/weather")
public class WeatherController {

    private final WeatherService weatherService;

    /**
     * Constructor
     *
     * @param weatherServiceFacade The service who will try to get the weather from external services
     */
    public WeatherController(WeatherServiceFacade weatherServiceFacade) {
        this.weatherService = weatherServiceFacade;
    }

    /**
     * Get the weather of the next day for a city
     *
     * @param cityname the name of the city
     *
     * @return WeatherResponse with the weather of the next day or an error message if no service is available
     */
    @GetMapping("/{cityname}")
    public ResponseEntity<WeatherResponse> getWeather(@PathVariable String cityname) {
        try {
            WeatherResponse weather = weatherService.getNextDayWeather(cityname);

            return ResponseEntity.ok(weather);
        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(new WeatherResponse(e.getMessage(), 0d));
        }
    }
}
