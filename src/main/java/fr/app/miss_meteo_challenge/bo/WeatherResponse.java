package fr.app.miss_meteo_challenge.bo;

/**
 * Class to represent the weather response
 */
public class WeatherResponse {

    private String weather;
    private Double temperature;
    private String date = null;

    /**
     * Constructor
     */
    private WeatherResponse() {
    }

    /**
     * Constructor for used in external services
     *
     * @param weather the weather
     * @param temperature the temperature
     */
    public WeatherResponse(String weather, Double temperature) {
        this.weather = weather;
        this.temperature = temperature;
    }

    /**
     * Constructor for used in local service
     * The date is needed for the local service to get the weather of the next day compared to the date of the request
     *
     * @param weather the weather
     * @param temperature the temperature
     * @param date the date
     */
    public WeatherResponse(String weather, Double temperature, String date) {
            this.weather = weather;
            this.temperature = temperature;
            this.date = date;
    }

    /**
     * @return the weather as a String
     */
    public String getWeather() {
        return weather;
    }

    /**
     * @return the temperature as a Double
     */
    public Double getTemperature() {
        return temperature;
    }

    /**
     * @return the date as a String in the format "yyyy-MM-dd"
     */
    public String getDate() {
        return date;
    }
}
