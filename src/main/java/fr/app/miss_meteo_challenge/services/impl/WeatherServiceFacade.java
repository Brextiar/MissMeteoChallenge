package fr.app.miss_meteo_challenge.services.impl;

import fr.app.miss_meteo_challenge.bo.WeatherResponse;
import fr.app.miss_meteo_challenge.services.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service to try to get the weather from external services, if they are not available, it will return the weather of Paris from the local service
 */
@Service
public class WeatherServiceFacade implements WeatherService {

    private final WeatherService parisLocalWeatherService;
    private final List<WeatherService> externalWeatherServices;

    /**
     * Constructor
     * @param weatherApiService The first external service
     * @param meteoConceptApiService The second external service
     * @param parisLocalWeatherService The local service
     */
    @Autowired
    public WeatherServiceFacade(
            @Qualifier("weatherApiService") WeatherService weatherApiService,
            @Qualifier("meteoConceptApiService") WeatherService meteoConceptApiService,
            @Qualifier("parisLocalService") WeatherService parisLocalWeatherService) {
        this.parisLocalWeatherService = parisLocalWeatherService;
        this.externalWeatherServices = List.of(weatherApiService, meteoConceptApiService);
    }

    /**
     * @param cityname the name of the city
     *
     * @return WeatherResponse with the weather of the next day from the first available service
     *
     * @throws RuntimeException if no service is available
     */
    @Override
    public WeatherResponse getNextDayWeather(String cityname) {
        try {
            for (WeatherService service : externalWeatherServices) {
                WeatherResponse weather = service.getNextDayWeather(cityname);
                if (weather != null) {

                    return weather;
                }
            }

            return parisLocalWeatherService.getNextDayWeather(cityname);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}
