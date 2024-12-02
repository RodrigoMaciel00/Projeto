package com.projeto.financeiro.controller;

import com.projeto.financeiro.model.Lancamento;
import com.projeto.financeiro.repository.LancamentoRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoController {

    private final LancamentoRepository lancamentoRepository;

    public LancamentoController(LancamentoRepository lancamentoRepository) {
        this.lancamentoRepository = lancamentoRepository;
    }

    @GetMapping
    public ResponseEntity<List<Lancamento>> listar() {
        List<Lancamento> lancamentos = lancamentoRepository.findAll();
        return ResponseEntity.ok(lancamentos);
    }


    @PostMapping
    public ResponseEntity<Lancamento> criar(@Valid @RequestBody Lancamento lancamento) {
        Lancamento novoLancamento = lancamentoRepository.save(lancamento);
        return ResponseEntity.status(201).body(novoLancamento);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Lancamento> buscarPorId(@PathVariable Long id) {
        Optional<Lancamento> lancamento = lancamentoRepository.findById(id);
        return lancamento.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
