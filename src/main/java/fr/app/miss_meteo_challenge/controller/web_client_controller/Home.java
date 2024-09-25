package fr.app.miss_meteo_challenge.controller.web_client_controller;

import fr.app.miss_meteo_challenge.bo.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

/**
 * Home controller
 */
@Controller
public class Home {
    @Autowired
    private RestTemplate restTemplate;

    /**
     * By default, get the weather of Paris
     * @param model contains the data to display
     * @return the index page
     */
    @GetMapping("/")
    public String index(Model model) {
        try {
            String url = "http://localhost:8080/weather/Paris";
            ResponseEntity<WeatherResponse> response = restTemplate.getForEntity(url, WeatherResponse.class);

            model.addAttribute("weatherResponse", response.getBody());
            model.addAttribute("cityName", "Paris");
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
        }
        return "index";
    }

    /**
     * Get the weather of a city
     *
     * @param cityName the name of the city
     * @param model contains the data to display
     *
     * @return the index page
     */
    @GetMapping("/weather")
    public String getWeather(@RequestParam String cityName, Model model) {
        try {
            String url = "http://localhost:8080/weather/" + cityName;
            ResponseEntity<WeatherResponse> response = restTemplate.getForEntity(url, WeatherResponse.class);

            model.addAttribute("weatherResponse", response.getBody());
            model.addAttribute("cityName", cityName);
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
        }
        return "index";
    }
}
