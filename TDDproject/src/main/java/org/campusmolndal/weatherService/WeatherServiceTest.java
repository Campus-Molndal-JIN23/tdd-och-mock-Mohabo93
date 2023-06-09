package org.campusmolndal.weatherService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class WeatherServiceTest {
    private WeatherService weatherService;
    @Mock
    private WeatherAPI mockAPI;

    @Before
    public void setUp() {
        mockAPI = mock(WeatherAPI.class);
        weatherService = new WeatherService(mockAPI);
    }

    @Test //Testar att rätt väderinformation returneras för en giltig stad
    public void testGetWeather_ValidCity() {
        //Arrange
        String city = "Stockholm";
        //Mocka returvärden för olika metodanrop
        when(mockAPI.getTemperature(city)).thenReturn(25);
        when(mockAPI.getWindSpeed(city)).thenReturn(10);
        when(mockAPI.getClouds(city)).thenReturn(50);
        when(mockAPI.getCountry(city)).thenReturn("Sweden");

        //Act
        Weather weather = weatherService.getWeather(city);

        //Assert
        assertEquals(25, weather.getTemperature());
        assertEquals(10, weather.getWindSpeed());
        assertEquals(50, weather.getClouds());
        assertEquals("Stockholm", weather.getCity());
        assertEquals("Sweden", weather.getCountry());
    }

    @Test //Testar att ingen väderinformation returneras för en ogiltig stad
    public void testGetWeather_InvalidCity() {
        //Arrange
        String invalidCity = "InvalidCity";
        when(mockAPI.getTemperature(invalidCity)).thenReturn(null);

        //Act
        Weather weather = weatherService.getWeather(invalidCity);

        //Assert
        assertNull(weather);
    }

    @Test //Testar att ingen väderinformation returneras vid nätverksfel
    public void testGetWeather_NetworkError() {
        //Arrange
        String city = "Stockholm";
        when(mockAPI.getTemperature(city)).thenReturn(null);

        //Act
        Weather weather = weatherService.getWeather(city);

        //Assert
        assertNull(weather);
    }

    @Test(timeout = 2000) //Ange tidsgränsen i millisekunder
    //Testar att hämtningen av väderinformationen utförs inom en given tidsgräns(2sekunder)
    public void testGetWeather_Speed() {
        //Arrange
        String city = "Stockholm";
        int temperature = 25;
        int windSpeed = 10;
        int clouds = 50;
        String country = "Sweden";
        when(mockAPI.getTemperature(city)).thenReturn(temperature);
        when(mockAPI.getWindSpeed(city)).thenReturn(windSpeed);
        when(mockAPI.getClouds(city)).thenReturn(clouds);
        when(mockAPI.getCountry(city)).thenReturn(country);

        //Act
        Weather weather = weatherService.getWeather(city);

        //Assert
        assertNotNull(weather);
        assertEquals(temperature, weather.getTemperature());
        assertEquals(windSpeed, weather.getWindSpeed());
        assertEquals(clouds, weather.getClouds());
        assertEquals(city, weather.getCity());
        assertEquals(country, weather.getCountry());
    }

    @Test //Testar att all väderdata är NotNull
    public void testGetWeather_AllDataNotNull() {
        //Arrange
        String city = "Stockholm";
        int temperature = 25;
        int windSpeed = 10;
        int clouds = 50;
        String country = "Sweden";
        when(mockAPI.getTemperature(city)).thenReturn(temperature);
        when(mockAPI.getWindSpeed(city)).thenReturn(windSpeed);
        when(mockAPI.getClouds(city)).thenReturn(clouds);
        when(mockAPI.getCountry(city)).thenReturn(country);

        //Act
        Weather weather = weatherService.getWeather(city);

        //Assert
        assertNotNull(weather);
        assertEquals(temperature, weather.getTemperature());
        assertEquals(windSpeed, weather.getWindSpeed());
        assertEquals(clouds,weather.getClouds());
        assertEquals(city, weather.getCity());
        assertEquals(country, weather.getCountry());
    }
    @Test //Testar att korrekt vindhastighet returneras
    public void testGetWeather_WindSpeed() {
        //Arrange
        String city = "Stockholm";
        int expectedWindSpeed = 10;


        //Mocka returvärden för olika metodanrop
        when(mockAPI.getTemperature(city)).thenReturn(25);
        when(mockAPI.getWindSpeed(city)).thenReturn(expectedWindSpeed);
        when(mockAPI.getClouds(city)).thenReturn(50);
        when(mockAPI.getCountry(city)).thenReturn("Sweden");

        //Act
        Weather weather = weatherService.getWeather(city);

        //Assert
        assertNotNull(weather);
        assertEquals(expectedWindSpeed, weather.getWindSpeed());
    }
    @Test //Testar att korrekt temperatur returneras
    public void testGetWeather_Temperature() {
        //Arrange
        String city = "Stockholm";
        int expectedTemperature = 25;

        //Mocka returvärden för olika metodanrop
        when(mockAPI.getTemperature(city)).thenReturn(expectedTemperature);
        when(mockAPI.getWindSpeed(city)).thenReturn(10);
        when(mockAPI.getClouds(city)).thenReturn(50);
        when(mockAPI.getCountry(city)).thenReturn("Sweden");

        //Act
        Weather weather =  weatherService.getWeather(city);

        //Assert
        assertNotNull(weather);
        assertEquals(expectedTemperature,weather.getTemperature());
    }
}

