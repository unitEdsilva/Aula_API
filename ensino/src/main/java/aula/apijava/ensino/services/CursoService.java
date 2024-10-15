package aula.apijava.ensino.services;

import java.util.*;
import aula.apijava.ensino.entities.Curso;
import org.springframework.stereotype.Service;
import aula.apijava.ensino.repositories.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    public List<Curso> getAllCursos() {
        return cursoRepository.findAll();
    }

    public Optional<Curso> getCursoById(Long id) {
        return cursoRepository.findById(id);
    }

    public Curso createCurso(Curso curso) {
        return cursoRepository.save(curso);
    }

    public Curso updateCurso(Long id, Curso cursoDetails) {
        Curso curso = cursoRepository.findById(id)
                .orElseThrow();

        curso.setNome(cursoDetails.getNome());
        curso.setDescricao(cursoDetails.getDescricao());

        return cursoRepository.save(curso);
    }

    public void deleteCurso(Long id) {
        Curso curso = cursoRepository.findById(id)
                .orElseThrow();
        cursoRepository.delete(curso);
    }
}
