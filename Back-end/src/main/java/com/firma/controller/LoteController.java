package com.firma.controller;


import com.firma.entidade.Lote;
import com.firma.repository.LoteRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/lote")
@SuppressWarnings("unused")
public class LoteController {
    private final LoteRepository repository;

    public LoteController(LoteRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Lote> all() {
        return repository.findAll();
    }

    @PostMapping
    public Lote novo(@RequestBody Lote lote) {
        return repository.save(lote);
    }

    @GetMapping("/{id}")
    public Lote find(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @PutMapping("/{id}")
    public Lote substituirOuSalvar(@RequestBody Lote novaLote, @PathVariable Long id) {
        return repository.findById(id).map(lote -> {
            novaLote.setId(id);
            return repository.save(novaLote);
        }).orElseGet(() -> repository.save(novaLote));
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
