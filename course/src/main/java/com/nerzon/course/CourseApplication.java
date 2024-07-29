package com.nerzon.course;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class CourseApplication {

    public static void main(String[] args) {
        SpringApplication.run(CourseApplication.class, args);
        System.out.println(System.getenv("DB_PASSWORD"));
        System.out.println(System.getenv("DB_USER"));
        System.out.println(System.getenv("DB_URL"));
    }

}
