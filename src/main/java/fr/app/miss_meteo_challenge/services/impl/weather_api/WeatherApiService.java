package fr.app.miss_meteo_challenge.services.impl.weather_api;

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
import java.util.List;

/**
 * Service to get the weather from WeatherAPI
 */
@Service
@Qualifier("weatherApiService")
public class WeatherApiService implements WeatherService {

    private final String API_KEY = "108148b0b7de489594f120502242409";
    private final String BASE_URL = "https://api.weatherapi.com/v1/forecast.json?q=";
    private final String BASE_URL_END = "&days=1&lang=fr&key=";
    private final HttpClient client = HttpClient.newHttpClient();

    /**
     * Get the weather of the next day from WeatherAPI
     *
     * @param cityname the name of the city
     *
     * @return WeatherResponse with the weather of the next day
     *
     * @throws RuntimeException if an error occurs during the call to WeatherAPI
     */
    @Override
    public WeatherResponse getNextDayWeather(String cityname) {

        String url = BASE_URL + cityname + BASE_URL_END + API_KEY;
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).GET().build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonNode = objectMapper.readTree(response.body());

                String weather = jsonNode.at("/forecast/forecastday/0/day/condition/text").asText();
                Double temperature = jsonNode.at("/forecast/forecastday/0/day/avgtemp_c").asDouble();

                return new WeatherResponse(weather, temperature);
            } else {
                throw new RuntimeException("Erreur lors de l'appel à WeatherAPI: " + response.statusCode());
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Erreur lors de l'appel à WeatherAPI", e);
        }
    }

    /**
     * Get the weather of Paris for the three next day from WeatherAPI to store it in a local file
     *
     * @return List of WeatherResponse with the weather of Paris for the three next day
     *
     * @throws RuntimeException if an error occurs during the call to WeatherAPI
     */
    public List<WeatherResponse> getParisWeatherForThreeDay() {

        String url = "https://api.weatherapi.com/v1/forecast.json?q=Paris&days=3&lang=fr&key=" + API_KEY;
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).GET().build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonNode = objectMapper.readTree(response.body());

                return List.of(new WeatherResponse(jsonNode.at("/forecast/forecastday/0/day/condition/text").asText(), jsonNode.at("/forecast/forecastday/0/day/avgtemp_c").asDouble(), jsonNode.at("/forecast/forecastday/0/date").asText()), new WeatherResponse(jsonNode.at("/forecast/forecastday/1/day/condition/text").asText(), jsonNode.at("/forecast/forecastday/1/day/avgtemp_c").asDouble(), jsonNode.at("/forecast/forecastday/1/date").asText()), new WeatherResponse(jsonNode.at("/forecast/forecastday/2/day/condition/text").asText(), jsonNode.at("/forecast/forecastday/2/day/avgtemp_c").asDouble(), jsonNode.at("/forecast/forecastday/2/date").asText()));
            } else {
                throw new RuntimeException("Erreur lors de l'appel à WeatherAPI: " + response.statusCode());
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Erreur lors de l'appel à WeatherAPI", e);
        }
    }
}
