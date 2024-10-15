package aula.apijava.ensino.repositories;

import java.util.List;
import aula.apijava.ensino.entities.Alocacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlocacaoRepository extends JpaRepository<Alocacao, Long> {
    List<Alocacao> findByProfessorId(Long professorId);
}
