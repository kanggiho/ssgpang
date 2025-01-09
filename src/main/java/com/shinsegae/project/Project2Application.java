package com.shinsegae.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class Project2Application {
    @Autowired
    private Environment environment;

    public static void main(String[] args) {
        SpringApplication.run(Project2Application.class, args);
    }

}
