package com.example.MakeUp.Person;

import com.example.MakeUp.Order.Order;
import com.example.MakeUp.Product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Optional;

@RestController
public class PersonController {
    @ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="Error")
    @ExceptionHandler(Exception.class)
    public void error() {
    }

     PersonRepository personRepository;

    @Autowired
    public PersonController(PersonRepository personRepository){
        this.personRepository = personRepository;
    }

    //get a list of persons
    @RequestMapping(value="/getPersons", method= RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Person> getAllPersons(){
        return personRepository.findAll();
    }


    //create a new person
    @RequestMapping(value = "/newPerson", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void addPerson(@RequestBody Person person) throws Exception{
        personRepository.save(person);
    }

    //get the feature of a person
    @RequestMapping(value = "/getPerson/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Person getPerson(@PathVariable(value = "id") Long id){
        return personRepository.findById(id).get();
    }

    //delete a person
    @Transactional
    @RequestMapping(value = "/delPerson/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deletePerson(@PathVariable(value = "id") Long id) throws Exception{
        personRepository.deleteById(id);
    }


    //update a person
    @RequestMapping(value = "/updPerson", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void updatePerson(@RequestBody Person person){
            Person p = new Person(person.getId(), person.getName(), person.getAge(),
                    person.getGender(), person.getAdresse(), person.getPhoneNumer(),
                    person.getPassword());
            personRepository.save(p);

    }

}
