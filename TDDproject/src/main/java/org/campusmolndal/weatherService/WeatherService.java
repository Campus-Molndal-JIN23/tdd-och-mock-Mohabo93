package org.campusmolndal.weatherService;
public class WeatherService {
    private WeatherAPI weatherAPI;
     public WeatherService(WeatherAPI weatherAPI){
         //Konstruktor som tar emot en instans av WeatherAPI och sätter det som en medlemsvariabel.
         this.weatherAPI = weatherAPI;
    }
    public Weather getWeather(String city) {
        //Hämta väderinformationen från WeatherAPI/Mock
        Integer temperature = weatherAPI.getTemperature(city);
        Integer windSpeed = weatherAPI.getWindSpeed(city);
        Integer clouds = weatherAPI.getClouds(city);
        String country = weatherAPI.getCountry(city);


        //Kontrollera om någon av väderna är null
        if (temperature == null || windSpeed == null || clouds == null || city == null || country == null) {
            return null;
        }

        //Skapa ett Weather-objekt
        Weather weather =  new Weather(temperature, windSpeed, clouds, city, country);
            return weather;

        }
}