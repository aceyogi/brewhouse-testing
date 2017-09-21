package com.hotgazpacho.brewhouse.refdata.resource;

import com.hotgazpacho.brewhouse.refdata.model.Person;
import com.hotgazpacho.brewhouse.refdata.repository.PersonRepository;
import org.junit.Before;
import org.junit.Test;

import org.mockito.ArgumentMatchers;
import org.mockito.Mock;

import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.MockitoAnnotations.initMocks;

public class PersonResourceTest {

    private PersonResource subject;

    @Mock
    private PersonRepository personRepository;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        subject = new PersonResource(personRepository);
    }

    @Test
    public void shouldReturnHelloWorld() throws Exception {
        assertThat(subject.hello(), is("Hello World!"));
    }

    @Test
    public void shouldReturnFullNameOfAPerson() throws Exception {
        Person peter = ObjectMother.People.PeterPan.get();
        given(personRepository.findByLastName(peter.getLastName())).willReturn(Optional.of(peter));

        String greeting = subject.hello("Pan");

        assertThat(greeting, is("Hello Peter Pan!"));
    }

    @Test
    public void shouldTellIfPersonIsUnknown() throws Exception {
        given(personRepository.findByLastName(ArgumentMatchers.anyString())).willReturn(Optional.empty());

        String greeting = subject.hello("Pan");

        assertThat(greeting, is("Who is this 'Pan' you're talking about?"));
    }
}