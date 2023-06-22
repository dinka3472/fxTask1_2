package org.example.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Domain {
    private int id;
    private String webName;
    private String domainName;
    private String ip;
    private LocalDate dateRegistered;
    private String countryRegistered;
    private Person person;
}
