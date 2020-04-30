package com.example.MakeUp.Person;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class PersonCommandLineRunner implements CommandLineRunner {

     PersonRepository personRepository;

    public PersonCommandLineRunner(PersonRepository personRepository){
        this.personRepository = personRepository;
    }

    @Override
    public void run(String... strings) throws Exception {
        System.out.println("CREATE PERSONS");
        Person p1 = new Person();
        p1.setName("Jeanne");
        p1.setAge(16);
        p1.setGender("Female");
        p1.setAdresse("xxxParis");
        p1.setPhoneNumer("0976543426");
        p1.setPassword("Jeanne");
        personRepository.save(p1);

        Person p2 = new Person();
        p2.setName("Aurélie");
        p2.setAge(23);
        p2.setGender("Female");
        p2.setAdresse("xxxLyon");
        p2.setPhoneNumer("0256543426");
        p2.setPassword("Aurélie");
        personRepository.save(p2);

        Person p3 = new Person();
        p3.setName("Emma");
        p3.setAge(35);
        p3.setGender("Female");
        p3.setAdresse("xxxLille");
        p3.setPhoneNumer("0976943426");
        p3.setPassword("Emma");
        personRepository.save(p3);

        Person p4 = new Person();
        p4.setName("Amy");
        p4.setAge(30);
        p4.setGender("Female");
        p4.setAdresse("xxxBordeaux");
        p4.setPhoneNumer("0176353426");
        p4.setPassword("Amy");
        personRepository.save(p4);

        Person p5 = new Person();
        p5.setName("June");
        p5.setAge(40);
        p5.setGender("Female");
        p5.setAdresse("xxxNancy");
        p5.setPhoneNumer("0770253426");
        p5.setPassword("June");
        personRepository.save(p5);
    }
}
