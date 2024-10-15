package aula.apijava.ensino.controllers;

import java.util.*;
import org.springframework.http.HttpStatus;
import aula.apijava.ensino.entities.Professor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import aula.apijava.ensino.services.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api/professores")
public class ProfessorController {

    @Autowired
    private ProfessorService professorService;

    @GetMapping
    public List<Professor> getAllProfessores() {
        return professorService.getAllProfessores();
    }

    @GetMapping("/{id}")
    public Optional<Professor> getProfessorById(Long id) {
        return Optional.ofNullable(professorService.getProfessorById(id));
    }

    @PostMapping
    public ResponseEntity<Professor> createProfessor(@RequestBody Professor professor) {
        Professor createdProfessor = professorService.createProfessor(professor);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProfessor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Professor> updateProfessor(@PathVariable Long id, @RequestBody Professor professorDetails) {
        return ResponseEntity.ok(professorService.updateProfessor(id, professorDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfessor(@PathVariable Long id) {
        professorService.deleteProfessor(id);
        return ResponseEntity.noContent().build();
    }
}