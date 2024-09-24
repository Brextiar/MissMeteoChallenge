package fr.app.miss_meteo_challenge.services;

import fr.app.miss_meteo_challenge.bo.WeatherResponse;

/**
 * Service to get the weather of the next day for a city
 */
public interface WeatherService {

    /**
     *
     * @param cityname the name of the city
     * @return WeatherResponse with the weather of the next day
     */
    WeatherResponse getNextDayWeather(String cityname);
}
