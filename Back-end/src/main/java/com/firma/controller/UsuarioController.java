package com.firma.controller;


import com.firma.entidade.Usuario;
import com.firma.repository.UsuarioRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/usuario")
@SuppressWarnings("unused")
public class UsuarioController {
    private final UsuarioRepository repository;

    public UsuarioController(UsuarioRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Usuario> all() {
        return repository.findAll();
    }

    @PostMapping
    public Usuario novo(@RequestBody Usuario usuario) {
        return repository.save(usuario);
    }

    @GetMapping("/{id}")
    public Usuario find(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @PutMapping("/{id}")
    public Usuario substituirOuSalvar(@RequestBody Usuario novaUsuario, @PathVariable Long id) {
        return repository.findById(id).map(usuario -> {
            novaUsuario.setId(id);
            return repository.save(novaUsuario);
        }).orElseGet(() -> repository.save(novaUsuario));
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
