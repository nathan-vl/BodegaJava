package com.firma.controller;


import com.firma.entidade.Produto;
import com.firma.repository.ProdutoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/produto")
@SuppressWarnings("unused")
public class ProdutoController {
    private final ProdutoRepository repository;

    public ProdutoController(ProdutoRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Produto> all() {
        return repository.findAll();
    }

    @PostMapping
    public Produto novo(@RequestBody Produto produto) {
        return repository.save(produto);
    }

    @GetMapping("/{id}")
    public Produto find(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @PutMapping("/{id}")
    public Produto substituirOuSalvar(@RequestBody Produto novaProduto, @PathVariable Long id) {
        return repository.findById(id).map(produto -> {
            novaProduto.setId(id);
            return repository.save(novaProduto);
        }).orElseGet(() -> repository.save(novaProduto));
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
