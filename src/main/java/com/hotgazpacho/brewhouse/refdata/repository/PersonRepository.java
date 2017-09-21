package com.hotgazpacho.brewhouse.refdata.repository;

import com.hotgazpacho.brewhouse.refdata.model.Person;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PersonRepository extends CrudRepository<Person, String> {

    Optional<Person> findByLastName(String lastName);

}
