package com.nerzon.course.controller;

import com.nerzon.course.entity.Cat;
import com.nerzon.course.repository.CatRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CatControllerTest {
    @Mock
    private CatRepo catRepo;
    @InjectMocks
    private CatController controller;

    @Test
    void changeCatFailedTest() {
        Long id = 1L;
        Cat cat = new Cat();
        cat.setId(1L);
        when(catRepo.existsById(id)).thenReturn(false);
        String expected = "No such row";
        assertEquals(expected, controller.changeCat(cat));
    }
    @Test
    void changeCatSuccessTest(){
        long id = 1;
        Cat cat = new Cat();
        cat.setId(1L);
        cat.setName("Barsik");
        cat.setAge(12);
        cat.setWeight(7);
        when(catRepo.existsById(id)).thenReturn(true);
        when(catRepo.save(cat)).thenReturn(cat);
        assertEquals(cat.toString(),controller.changeCat(cat));
    }


}