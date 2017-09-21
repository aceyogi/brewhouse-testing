package com.hotgazpacho.brewhouse.refdata.resource;

import com.hotgazpacho.brewhouse.refdata.repository.PersonRepository;
import com.hotgazpacho.brewhouse.refdata.weather.WeatherClient;
import com.hotgazpacho.brewhouse.refdata.weather.WeatherResponse;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.MockitoAnnotations.initMocks;

public class WeatherResourceTest {

    private WeatherResource subject;

    @Mock
    private WeatherClient weatherClient;


    @Before
    public void setUp() throws Exception {
        initMocks(this);
        subject = new WeatherResource(weatherClient);
    }

    @Test
    public void shouldReturnWeatherClientResult() throws Exception {
        WeatherResponse weatherResponse = new WeatherResponse("Hamburg, 8°C raining");
        given(weatherClient.fetchWeather()).willReturn(Optional.of(weatherResponse));

        String weather = subject.weather();

        assertThat(weather, is("Hamburg, 8°C raining"));
    }

    @Test
    public void shouldReturnErrorMessageIfWeatherClientIsUnavailable() throws Exception {
        given(weatherClient.fetchWeather()).willReturn(Optional.empty());
        String weather = subject.weather();
        assertThat(weather, is("Sorry, I couldn't fetch the weather for you :("));
    }
}