package com.nerzon.course.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nerzon.course.DTO.CatDTO;
import com.nerzon.course.entity.Cat;
import com.nerzon.course.repository.CatRepo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@Tag(name="main methods")
@Slf4j
@RestController
@RequiredArgsConstructor
public class CatController {
    private final ObjectMapper objectMapper;

    private final CatRepo catRepo;
@Operation(summary="кладет нового котика в базу",
description = "Получает DTO кота и билдером собирает и сохраняет сущность в базу")
    @PostMapping("/api/add")
    public void addCat(@RequestBody CatDTO catDTO) {
        log.info(
                "New row:" + catRepo.save(
                        Cat.builder()
                                .age(catDTO.getAge())
                                .name(catDTO.getName())
                                .weight(catDTO.getWeight())
                                .build())
        );
    }

    @PutMapping("/api/add")
    public String changeCat(@RequestBody Cat cat) {
        if (!catRepo.existsById(cat.getId())) {
            return "No such row";
        }
        return catRepo.save(cat).toString();
    }

    @SneakyThrows
    @GetMapping("/api/all")
    public List<Cat> getAll() {
        return catRepo.findAll();
    }

    @SneakyThrows
    @GetMapping("/api")
    public String getCat(@RequestParam Long id) {
        Optional<Cat> cat = catRepo.findById(id);
        if (cat.isPresent()) {
            return cat.get().toString();
        }
        return String.format("No such cat with id=%s", id);
    }

    @DeleteMapping("/api")
    public void deleteCat(@RequestParam Long id) {
        catRepo.deleteById(id);
    }


}
