package aula.apijava.ensino.services;

import java.util.*;
import aula.apijava.ensino.entities.*;
import aula.apijava.ensino.repositories.*;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class AlocacaoService {

    @Autowired
    private AlocacaoRepository alocacaoRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    public List<Alocacao> getAllAlocacoes() {
        return alocacaoRepository.findAll();
    }

    public Optional<Alocacao> getAlocacaoById(Long id) {
        return alocacaoRepository.findById(id);
    }

    public Alocacao createAlocacao(Alocacao alocacao, Long professorId) {
        Professor professor = professorRepository.findById(professorId)
                .orElseThrow();

        if (existsConflitoHorario(professorId, alocacao.getHoraIncio(), alocacao.getHoraFim())) {
            throw new IllegalArgumentException("O professor já está alocado em outro curso nesse horário.");
        }

        alocacao.setProfessor(professor);
        return alocacaoRepository.save(alocacao);
    }

    public Alocacao updateAlocacao(Long id, Alocacao alocacaoDetails, Long professorId) {
        Alocacao alocacao = alocacaoRepository.findById(id)
                .orElseThrow();

        Professor professor = professorRepository.findById(professorId)
                .orElseThrow();

        if (existsConflitoHorario(professorId, alocacaoDetails.getHoraIncio(), alocacaoDetails.getHoraFim())) {
            throw new IllegalArgumentException("O professor já está alocado em outro curso nesse horário.");
        }

        alocacao.setHoraIncio(alocacaoDetails.getHoraIncio());
        alocacao.setHoraFim(alocacaoDetails.getHoraFim());
        alocacao.setProfessor(professor);

        return alocacaoRepository.save(alocacao);
    }

    public void deleteAlocacao(Long id) {
        Alocacao alocacao = alocacaoRepository.findById(id)
                .orElseThrow();
        alocacaoRepository.delete(alocacao);
    }

    // Método para verificar conflitos de horário
    private boolean existsConflitoHorario(Long professorId, Date horaInicio, Date horaFim) {
        List<Alocacao> alocacoes = alocacaoRepository.findByProfessorId(professorId);

        for (Alocacao alocacao : alocacoes) {
            if (horaInicio.before(alocacao.getHoraFim()) && horaFim.after(alocacao.getHoraIncio())) {
                return true; // Há conflito de horário
            }
        }

        return false; // Sem conflito
    }
}