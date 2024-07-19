package com.nerzon.course.controller;

import com.nerzon.course.entity.Dog;
import com.nerzon.course.repository.DogRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/dogs")
public class DogController {
    private final DogRepo dogRepo;

    public DogController(DogRepo dogRepo) {
        this.dogRepo = dogRepo;
    }

    @GetMapping("/all")
    public List<Dog> getAllDogsFromDB() {
        return dogRepo.findAll();
    }

    @GetMapping
    public ResponseEntity<Dog> getDogByName(@RequestParam String name) {
        Dog dog = dogRepo.findByName(name);
        if (dog == null) {
            return ResponseEntity
                    .status(HttpStatus.valueOf(404))
                    .build();
        }
        return new ResponseEntity<>(dog, HttpStatus.OK);
    }
    @PostMapping
    public Dog putDogIntoDB(@RequestBody Dog dog){
        dog.setId(UUID.randomUUID());
        return dogRepo.save(dog);
    }

}
