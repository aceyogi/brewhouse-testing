package com.hotgazpacho.brewhouse.refdata.resource;

import com.hotgazpacho.brewhouse.refdata.resource.WeatherResource;
import com.hotgazpacho.brewhouse.refdata.weather.WeatherClient;
import com.hotgazpacho.brewhouse.refdata.weather.WeatherResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = WeatherResource.class)
public class WeatherResourceIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WeatherClient weatherClient;

    @Test
    public void shouldReturnCurrentWeather() throws Exception {
        WeatherResponse weatherResponse = new WeatherResponse("Hamburg, 8°C raining");
        given(weatherClient.fetchWeather()).willReturn(Optional.of(weatherResponse));

        mockMvc.perform(get("/api/weather"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().string("Hamburg, 8°C raining"));
    }
}
