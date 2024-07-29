package com.nerzon.course;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class CourseApplicationTests {

    @Test
    void contextLoads() {
    }
    @Test
    void concatTest(){
        String stringOne = "Hello ";
        String stringTwo = "world";

        Assertions.assertEquals("Hello world", String.format("%s%s",stringOne, stringTwo));

    }

}
