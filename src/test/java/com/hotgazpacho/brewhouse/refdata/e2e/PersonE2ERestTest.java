package com.hotgazpacho.brewhouse.refdata.e2e;


import com.hotgazpacho.brewhouse.refdata.model.Person;
import com.hotgazpacho.brewhouse.refdata.repository.PersonRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.core.Is.is;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PersonE2ERestTest {

  @Autowired
  private PersonRepository personRepository;

  @LocalServerPort
  private int port;

  @After
  public void tearDown() throws Exception {
    personRepository.deleteAll();
  }

  @Test
  public void shouldReturnHelloWorld() throws Exception {
    when().get(String.format("http://localhost:%s/api/hello", port))
        .then()
        .statusCode(is(200))
        .body(containsString("Hello World!"));
  }

  @Test
  public void shouldReturnGreeting() throws Exception {
    Person peter = Person.builder().firstName("Peter").lastName("Pan").build();
    personRepository.save(peter);

    when()
        .get(String.format("http://localhost:%s/api/hello/Pan", port))
        .then()
        .statusCode(is(200))
        .body(containsString("Hello Peter Pan!"));
  }
}
