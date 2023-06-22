package org.example.Models;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Person {
    private int id;
    private String jobTitle;
    private String firstNameLastName;
    private String phone;
    private String email;
    private Set<Domain> domains = new HashSet<>();
}
