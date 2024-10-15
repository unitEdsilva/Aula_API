package aula.apijava.ensino.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import aula.apijava.ensino.entities.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long> {
}
