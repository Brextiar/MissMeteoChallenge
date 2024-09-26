package fr.app.miss_meteo_challenge.services.impl.ParisLocalService;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.app.miss_meteo_challenge.bo.WeatherResponse;
import fr.app.miss_meteo_challenge.services.WeatherService;
import fr.app.miss_meteo_challenge.services.impl.weather_api.WeatherApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;

/**
 * Service to add and get the weather of Paris for the three next day from a local file
 */
@Service
@Qualifier("parisLocalService")
public class ParisLocalWeatherService implements WeatherService {

    private final Path PARIS_WEATHER_FILE = Paths.get("src/main/java/fr/app/miss_meteo_challenge/data/paris-weather.json");

    @Autowired
    private WeatherApiService weatherApiService;

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * Write the weather of Paris for the three next day in a local Json file
     */
    @Scheduled(cron = "0 0 0 * * ?")
    public void updateParisWeather() {
        List<WeatherResponse> parisWeather = weatherApiService.getParisWeatherForThreeDay();

        try {
            objectMapper.writeValue(PARIS_WEATHER_FILE.toFile(), parisWeather);
        } catch (IOException e) {
            throw new RuntimeException("Erreur lors de l'écriture du fichier météo de Paris", e);
        }
    }

    /**
     * Get the weather of Paris for the next day
     *
     * @param cityname the name of the city (not used)
     * @return WeatherResponse with the weather of Paris for the next day
     * @throws RuntimeException if an error occurs during the reading of the file or if there is no weather for the next day
     */
    @Override
    public WeatherResponse getNextDayWeather(String cityname) {

        LocalDate today = LocalDate.now();
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            WeatherResponse[] weatherResponse = new WeatherResponse[]{objectMapper.readValue(PARIS_WEATHER_FILE.toFile(), WeatherResponse.class)};

            for (WeatherResponse weather : weatherResponse) {
                if (weather.getDate().equals(today.toString())) {

                    return weather;
                }
            }
            throw new RuntimeException("ParisLocal Service indisponible");
        } catch (IOException e) {
            throw new RuntimeException("Erreur lors de la lecture du fichier météo de Paris", e);
        }
    }
}
