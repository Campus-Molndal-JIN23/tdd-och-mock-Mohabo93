package org.campusmolndal.weatherService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

public class WeatherServiceTest {
    private WeatherService weatherService;

    @Mock
    private WeatherService.Mock mock;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        weatherService = new WeatherService(mock);
    }
    @Test
    public void testGetWeather_ValidCity() {
        //Arrange
        String city = "Stockholm";
        //Mocka returvärden för olika metodanrop
        when(mock.getTemperature(city)).thenReturn(25);
        when(mock.getWindSpeed(city)).thenReturn(10);
        when(mock.getClouds(city)).thenReturn(50);
        when(mock.getCountry(city)).thenReturn("Sweden");

        //Act
        Weather weather = weatherService.getWeather(city);

        //Assert
        Assert.assertEquals(25, weather.getTemperature());
        Assert.assertEquals(10, weather.getWindSpeed());
        Assert.assertEquals(50, weather.getClouds());
        Assert.assertEquals("Stockholm", weather.getCity());
        Assert.assertEquals("Sweden", weather.getCountry());
    }
    @Test
    public void testGetWeather_InvalidCity(){
        //Arrange
        String invalidCity = "InvalidCity";
        //Mocka null-värden för en ogiltig stad
        when(mock.getTemperature(invalidCity)).thenReturn(null);
        when(mock.getWindSpeed(invalidCity)).thenReturn(null);
        when(mock.getClouds(invalidCity)).thenReturn(null);
        when(mock.getCountry(invalidCity)).thenReturn(null);

        //Act
        Weather weather = weatherService.getWeather(invalidCity);

        //Assert
        Assert.assertNull(weather);
    }
    @Test
    public void testGetWeather_NetworkError() {
        //Arrange
        String city = "Stockholm";

        //Mocka nätverksfel genom att returnera null för alla metodanrop
        when(mock.getTemperature(city)).thenReturn(null);
        when(mock.getWindSpeed(city)).thenReturn(null);
        when(mock.getClouds(city)).thenReturn(null);
        when(mock.getCountry(city)).thenReturn(null);

        //Act
        Weather weather = weatherService.getWeather(city);

        //Assert
        Assert.assertNull(weather);
    }
}