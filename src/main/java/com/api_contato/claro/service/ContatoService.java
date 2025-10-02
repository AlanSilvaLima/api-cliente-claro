package com.api_contato.claro.service;

import com.api_contato.claro.entity.Contato;

import java.util.List;
import java.util.Optional;

public interface ContatoService {

    Contato salvar(Contato contato);
    Optional<Contato> buscarPorEmail(String email);
    List<Contato> listarTodos();
    Contato atualizar(String email, Contato contato);
    void deletar(String email);
}
