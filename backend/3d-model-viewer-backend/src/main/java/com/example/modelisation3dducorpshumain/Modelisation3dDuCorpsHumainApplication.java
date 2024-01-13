package com.example.modelisation3dducorpshumain;

import com.example.modelisation3dducorpshumain.beans.User;
import com.example.modelisation3dducorpshumain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import static com.example.modelisation3dducorpshumain.beans.Role.ADMIN;

@SpringBootApplication
public class Modelisation3dDuCorpsHumainApplication {

    @Autowired
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private final UserRepository repo;

    public Modelisation3dDuCorpsHumainApplication(PasswordEncoder passwordEncoder,UserRepository repo) {
        this.passwordEncoder = passwordEncoder;
        this.repo=repo;
    }

    public static void main(String[] args) {
        SpringApplication.run(Modelisation3dDuCorpsHumainApplication.class, args);
    }


}
