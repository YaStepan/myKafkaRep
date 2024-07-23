package com.nerzon.course.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nerzon.course.entity.Cat;
import com.nerzon.course.kafka.KafkaProducer;
import com.nerzon.course.repository.CatRepo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class Controller {
    private final KafkaProducer kafkaProducer;
    private final CatRepo catRepo;
    private final ObjectMapper objectMapper;

    public Controller(KafkaProducer kafkaProducer, CatRepo catRepo, ObjectMapper objectMapper) {
        this.kafkaProducer = kafkaProducer;
        this.catRepo = catRepo;
        this.objectMapper = objectMapper;
    }

    @PostMapping("/kafka/send")
    public String send(@RequestParam Long id) {
        Optional<Cat> optionalCat = catRepo.findById(id);
        if (optionalCat.isPresent()) {
            try {
                kafkaProducer.sendMessage(objectMapper.writeValueAsString(optionalCat.get()));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            return "success";
        }
        return "failure, there is no cat by this id";
    }

}
