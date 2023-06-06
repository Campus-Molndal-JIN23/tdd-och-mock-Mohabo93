package org.campusmolndal.weatherService;
public class WeatherService {
    public interface Mock{
        Integer getTemperature(String city);
        Integer getWindSpeed(String city);
        Integer getClouds(String city);
        String getCountry(String city);
}
    private Mock mock;
     public WeatherService(Mock mock){
         this.mock = mock;
    }
    public Weather getWeather(String city) {
        Integer temperature = mock.getTemperature(city);
        Integer windSpeed = mock.getWindSpeed(city);
        Integer clouds = mock.getClouds(city);
        String country = mock.getCountry(city);

        //Kontrollera om någon av väderna är null
        if (temperature == null || windSpeed == null || clouds == null || city == null || country == null) {
            return null;
        }

        //Skapa ett Weather-objekt
        Weather weather =  new Weather(temperature, windSpeed, clouds, city, country);
            return weather;
        }
}