package org.example.Models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Objects;
import java.util.Set;

public class PersonDto {
    private Integer id;
    private String jobTitle;
    private String firstNameLastName;
    private String phone;
    private String email;
    private Integer countDomain;
    private Set<Domain> domains;

    public PersonDto() {
    }

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

    public void setId(Integer id) {
        this.id = id;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getFirstNameLastName() {
        return firstNameLastName;
    }

    public void setFirstNameLastName(String firstNameLastName) {
        this.firstNameLastName = firstNameLastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getCountDomain() {
        return countDomain;
    }

    public void setCountDomain(Integer countDomain) {
        this.countDomain = countDomain;
    }

    public Set<Domain> getDomains() {
        return domains;
    }

    public void setDomains(Set<Domain> domains) {
        this.domains = domains;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, jobTitle, firstNameLastName, phone, email, countDomain);
    }
}
