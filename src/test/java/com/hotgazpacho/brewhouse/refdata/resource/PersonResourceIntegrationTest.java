package com.hotgazpacho.brewhouse.refdata.resource;

import com.hotgazpacho.brewhouse.refdata.model.Person;
import com.hotgazpacho.brewhouse.refdata.repository.PersonRepository;
import com.hotgazpacho.brewhouse.refdata.resource.PersonResource;
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
@WebMvcTest(controllers = PersonResource.class)
public class PersonResourceIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonRepository personRepository;

    @Test
    public void shouldReturnHelloWorld() throws Exception {
        mockMvc.perform(get("/api/hello"))
                .andExpect(content().string("Hello World!"))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void shouldReturnFullName() throws Exception {
        Person peter = ObjectMother.People.PeterPan.get();
        given(personRepository.findByLastName(peter.getLastName())).willReturn(Optional.of(peter));

        mockMvc.perform(get("/api/hello/Pan"))
                .andExpect(content().string("Hello Peter Pan!"))
                .andExpect(status().is2xxSuccessful());
    }
}
