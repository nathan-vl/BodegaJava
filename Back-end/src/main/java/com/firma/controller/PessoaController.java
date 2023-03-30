package com.firma.controller;


import com.firma.entidade.Pessoa;
import com.firma.repository.PessoaRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/pessoa")
@SuppressWarnings("unused")
public class PessoaController {
    private final PessoaRepository repository;

    public PessoaController(PessoaRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Pessoa> all() {
        return repository.findAll();
    }

    @PostMapping
    public Pessoa novo(@RequestBody Pessoa pessoa) {
        return repository.save(pessoa);
    }

    @GetMapping("/{id}")
    public Pessoa find(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @PutMapping("/{id}")
    public Pessoa substituirOuSalvar(@RequestBody Pessoa novaPessoa, @PathVariable Long id) {
        return repository.findById(id).map(pessoa -> {
            novaPessoa.setId(id);
            return repository.save(novaPessoa);
        }).orElseGet(() -> repository.save(novaPessoa));
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
