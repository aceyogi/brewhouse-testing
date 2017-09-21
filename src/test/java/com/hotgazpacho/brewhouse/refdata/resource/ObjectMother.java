package com.hotgazpacho.brewhouse.refdata.resource;

import com.hotgazpacho.brewhouse.refdata.model.Person;

public class ObjectMother {

  public static class People {

    public static class PeterPan {

      public static Person get() {
        return Person.builder().firstName("Peter").lastName("Pan").build();
      }

      public static Person.PersonBuilder builder() {
        return Person.builder().firstName("Peter").lastName("Pan");
      }

    }

  }

}

