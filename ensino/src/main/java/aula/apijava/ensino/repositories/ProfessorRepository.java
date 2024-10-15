package aula.apijava.ensino.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import aula.apijava.ensino.entities.Professor;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {
}
