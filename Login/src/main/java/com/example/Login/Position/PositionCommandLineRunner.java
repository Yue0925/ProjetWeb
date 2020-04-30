package com.example.Login.Position;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class PositionCommandLineRunner implements CommandLineRunner {

    PositionRepository positionRepository;

    public PositionCommandLineRunner(PositionRepository positionRepository){this.positionRepository = positionRepository;}

    @Override
    public void run(String... strings) throws Exception {
    Position Jeanne = new Position((long) 1, "Jeanne", "Jeanne", "customer");
    positionRepository.save(Jeanne);

    Position Aurélie = new Position((long) 2, "Aurélie", "Aurélie", "customer");
    positionRepository.save(Aurélie);

    Position Emma = new Position((long) 3, "Emma", "Emma", "customer");
    positionRepository.save(Emma);

    Position Amy = new Position((long) 4, "Amy", "Amy", "customer");
    positionRepository.save(Amy);

    Position June = new Position((long) 5, "June", "June", "customer");
    positionRepository.save(June);

    Position employee = new Position((long) 6, "employee", "employee", "employee");
    positionRepository.save(employee);

    Position admin = new Position((long) 7, "admin", "admin", "admin");
    positionRepository.save(admin);

    }
}
