package org.example.Models;

import lombok.*;
import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Domain {
    private int id;
    private String webName;
    private String domainName;
    private String ip;
    private LocalDate dateRegistered;
    private String countryRegistered;
    private Person person;
}
