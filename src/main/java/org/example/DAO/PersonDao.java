package org.example.DAO;

import org.example.DatabaseConnector;
import org.example.Models.Domain;
import org.example.Models.Person;
import org.example.Models.PersonDto;

import java.sql.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class PersonDao {
    public Set<PersonDto> getAllPersons() {
        Set<Person> personSet = new HashSet<>();
        try (Connection connection = DatabaseConnector.getConnection();
             Statement statement = connection.createStatement()) {
            String query = "SELECT * FROM person left join domains on person.id = domains.personid";
            ResultSet resultSet = statement.executeQuery(query);

            Set<Domain> domains = new HashSet<>();

            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                Person person;
                person = personSet.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
                if (person == null) {
                    person = new Person();
                    String jobTitle = resultSet.getString(2);
                    String firstNameLastName = resultSet.getString(3);
                    String phone = resultSet.getString(4);
                    String email = resultSet.getString(5);
                    person.setId(id);
                    person.setJobTitle(jobTitle);
                    person.setFirstNameLastName(firstNameLastName);
                    person.setPhone(phone);
                    person.setEmail(email);
                    person.setDomains(new HashSet<>());
                    personSet.add(person);
                }
                int idDomain = resultSet.getInt(6);
                if (idDomain != 0) {
                    String webName = resultSet.getString(7);
                    String domainName = resultSet.getString(8);
                    String ip = resultSet.getString(9);
                    LocalDate dateRegistered = resultSet.getDate(10).toLocalDate();
                    String countryRegistered = resultSet.getString(11);
                    Domain domain = new Domain(idDomain, webName, domainName, ip, dateRegistered, countryRegistered, person);
                    person.getDomains().add(domain);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return personSet.stream().map(PersonDto::new).collect(Collectors.toSet());
    }
}
