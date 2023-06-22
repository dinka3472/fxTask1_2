package org.example.Models;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
@Getter
@Setter
public class PersonDto {
    private int id;
    private String jobTitle;
    private String firstNameLastName;
    private String phone;
    private String email;
    private int countDomain;
    private Set<Domain> domains;


    public PersonDto (Person person) {
        this.id = person.getId();
        this.jobTitle = person.getJobTitle();
        this.firstNameLastName = person.getFirstNameLastName();
        this.email = person.getEmail();
        this.phone = person.getPhone();
        this.domains = person.getDomains();
        this.countDomain = person.getDomains().size();
    }
}
