package org.campusmolndal.weatherService;
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

    @Test
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

    @Test
    public void testGetWeather_InvalidCity() {
        //Arrange
        String invalidCity = "InvalidCity";
        when(mockAPI.getTemperature(invalidCity)).thenReturn(null);

        //Act
        Weather weather = weatherService.getWeather(invalidCity);

        //Assert
        assertNull(weather);
    }

    @Test
    public void testGetWeather_NetworkError() {
        //Arrange
        String city = "Stockholm";
        when(mockAPI.getTemperature(city)).thenReturn(null);

        //Act
        Weather weather = weatherService.getWeather(city);

        //Assert
        assertNull(weather);
    }
}
