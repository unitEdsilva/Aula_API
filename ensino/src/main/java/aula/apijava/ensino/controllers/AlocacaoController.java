package aula.apijava.ensino.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import aula.apijava.ensino.entities.Alocacao;
import aula.apijava.ensino.services.AlocacaoService;

@RestController
@RequestMapping("/api/alocacoes")
public class AlocacaoController {

    @Autowired
    private AlocacaoService alocacaoService;

    @GetMapping
    public List<Alocacao> getAllAlocacoes() {
        return alocacaoService.getAllAlocacoes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Alocacao> getAlocacaoById(@PathVariable Long id) {
        return alocacaoService.getAlocacaoById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Alocacao> createAlocacao(@RequestBody Alocacao alocacao, @RequestParam Long professorId) {
        Alocacao createdAlocacao = alocacaoService.createAlocacao(alocacao, professorId);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAlocacao);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Alocacao> updateAlocacao(@PathVariable Long id, @RequestBody Alocacao alocacaoDetails,
            @RequestParam Long professorId) {
        return ResponseEntity.ok(alocacaoService.updateAlocacao(id, alocacaoDetails, professorId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAlocacao(@PathVariable Long id) {
        alocacaoService.deleteAlocacao(id);
        return ResponseEntity.noContent().build();
    }
}