package org.example.Models;

import lombok.Getter;
import lombok.Setter;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonDto personDto = (PersonDto) o;
        return id == personDto.id && countDomain == personDto.countDomain && Objects.equals(jobTitle, personDto.jobTitle) && Objects.equals(firstNameLastName, personDto.firstNameLastName) && Objects.equals(phone, personDto.phone) && Objects.equals(email, personDto.email) && Objects.equals(domains, personDto.domains);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, jobTitle, firstNameLastName, phone, email, countDomain);
    }
}
