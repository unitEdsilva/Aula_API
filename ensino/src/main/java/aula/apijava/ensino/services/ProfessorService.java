package aula.apijava.ensino.services;

import java.util.List;
import aula.apijava.ensino.entities.Professor;
import org.springframework.stereotype.Service;
import aula.apijava.ensino.repositories.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class ProfessorService {

    @Autowired
    private final ProfessorRepository professorRepository;

    public ProfessorService(ProfessorRepository professorRepository) {
        super();
        this.professorRepository = professorRepository;
    }

    public Professor getProfessorById(Long id) {
        return professorRepository.findById(id).orElse(null);
    }

    public List<Professor> getAllProfessores() {
        return professorRepository.findAll();
    }

    public Professor createProfessor(Professor professor) {
        return professorRepository.save(professor);
    }

    public Professor updateProfessor(Long id, Professor professorDetails) {
        Professor professor = professorRepository.findById(id)
                .orElseThrow();

        professor.setNome(professorDetails.getNome());
        professor.setCurso(professorDetails.getCurso());
        professor.setAlocacoes(professorDetails.getAlocacoes());

        return professorRepository.save(professor);
    }

    public void deleteProfessor(Long id) {
        professorRepository.deleteById(id);
    }
}
