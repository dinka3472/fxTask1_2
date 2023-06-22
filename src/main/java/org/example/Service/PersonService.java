package org.example.Service;

import org.example.DAO.PersonDao;
import org.example.Models.PersonDto;

import java.util.Set;

public class PersonService {
    private final PersonDao personDao;

    public PersonService() {
        this.personDao = new PersonDao();
    }

    public Set<PersonDto> getAllPersons() {
        return personDao.getAllPersons();
    }
}
