package org.campusmolndal.weatherService;
public class Weather {

    private int temperature;
    private int windSpeed;
    private int clouds;
    private String city;
    private String country;
    public Weather(int temperature, int windSpeed, int clouds, String city, String country) {
        this.temperature = temperature;
        this.windSpeed = windSpeed;
        this.clouds = clouds;
        this.city = city;
        this.country = country;
    }

    public int getTemperature() {
        return temperature;
    }

    public int getWindSpeed() {
        return windSpeed;
    }

    public int getClouds() {
        return clouds;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }
}
