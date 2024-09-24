package fr.app.miss_meteo_challenge.services.impl.meteo_concept;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.app.miss_meteo_challenge.bo.WeatherResponse;
import fr.app.miss_meteo_challenge.services.WeatherService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * Service to get the weather from MeteoConcept API
 */
@Service
@Qualifier("meteoConceptApiService")
public class MeteoConceptApiService implements WeatherService {

    private final String BASE_URL = "https://api.meteo-concept.com/api/forecast/daily/1?token=3702317879ac7c3d3339807d7886ace9efc04126cf60762be39c93a9c494ff00&insee=";
    private final HttpClient client = HttpClient.newHttpClient();

    /**
     * Get the weather of the next day from MeteoConcept API
     *
     * @param cityname the name of the city
     *
     * @return WeatherResponse with the weather of the next day
     *
     * @throws RuntimeException if an error occurs during the call to MeteoConcept API
     */
    @Override
    public WeatherResponse getNextDayWeather(String cityname) {
        String url = BASE_URL + cityname;
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).GET().build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonNode = objectMapper.readTree(response.body());
                String weather = MeteoConceptWeatherCode.getWeatherByCode(jsonNode.at("/forecast/0/weather").asInt()).getDescription();
                Double temperature = jsonNode.at("/forecast/0/tmax").asDouble();

                return new WeatherResponse(weather, temperature);
            } else {
                throw new RuntimeException("Erreur lors de l'appel à MeteoConceptApi: " + response.statusCode());
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Erreur lors de l'appel à MeteoConceptApi", e);
        }
    }
}
