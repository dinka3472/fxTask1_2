package org.example.Models;

import lombok.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    private int id;
    private String jobTitle;
    private String firstNameLastName;
    private String phone;
    private String email;
    private Set<Domain> domains = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id == person.id && Objects.equals(jobTitle, person.jobTitle) && Objects.equals(firstNameLastName, person.firstNameLastName) && Objects.equals(phone, person.phone) && Objects.equals(email, person.email) && Objects.equals(domains, person.domains);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, jobTitle, firstNameLastName, phone, email);
    }
}
