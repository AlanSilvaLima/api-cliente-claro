package com.api_contato.claro.controller;

import com.api_contato.claro.entity.Contato;
import com.api_contato.claro.service.ContatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/contatos")
public class ContatoController {

    @Autowired
    private ContatoService contatoService;

    // Criar novo contato (POST)
    @PostMapping
    public ResponseEntity<Contato> criar(@RequestBody Contato contato) {
        Contato salvo = contatoService.salvar(contato);
        return ResponseEntity.ok(salvo);
    }

    // Buscar por email (GET)
    @GetMapping("/{email}")
    public ResponseEntity<Contato> buscarPorEmail(@PathVariable String email) {
        Optional<Contato> contato = contatoService.buscarPorEmail(email);
        return contato.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Listar todos (GET)
    @GetMapping
    public ResponseEntity<List<Contato>> listarTodos() {
        return ResponseEntity.ok(contatoService.listarTodos());
    }

    // Atualizar contato (PUT)
    @PutMapping("/{email}")
    public ResponseEntity<Contato> atualizar(@PathVariable String email, @RequestBody Contato contato) {
        try {
            Contato atualizado = contatoService.atualizar(email, contato);
            return ResponseEntity.ok(atualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Excluir contato (DELETE)
    @DeleteMapping("/{email}")
    public ResponseEntity<Void> deletar(@PathVariable String email) {
        contatoService.deletar(email);
        return ResponseEntity.noContent().build();
    }
}
