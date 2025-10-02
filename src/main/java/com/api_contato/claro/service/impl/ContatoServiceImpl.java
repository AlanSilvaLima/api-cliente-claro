package com.api_contato.claro.service.impl;

import com.api_contato.claro.entity.Contato;
import com.api_contato.claro.repository.ContatoRepository;
import com.api_contato.claro.service.ContatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class ContatoServiceImpl implements ContatoService {

    @Autowired
    private ContatoRepository repository;

    private static final String API_CEP = "https://cdn.apicep.com/file/apicep/{cep}.json";

    @Override
    public Contato salvar(Contato contato) {
        // Preenche endereço a partir do CEP
        preencherEnderecoPorCep(contato);

        contato.setDataCadastro(new Date()); // Data atual
        return repository.save(contato);
    }

    @Override
    public Optional<Contato> buscarPorEmail(String email) {
        return repository.findById(email);
    }

    @Override
    public List<Contato> listarTodos() {
        return repository.findAll();
    }

    @Override
    public Contato atualizar(String email, Contato contato) {
        Optional<Contato> existente = repository.findById(email);
        if (!existente.isPresent()) {
            throw new RuntimeException("Contato não encontrado: " + email);
        }

        Contato atualizado = existente.get();
        atualizado.setNome(contato.getNome());
        atualizado.setTelefone(contato.getTelefone());
        atualizado.setCep(contato.getCep());

        // Atualiza dados de endereço caso o CEP tenha mudado
        preencherEnderecoPorCep(atualizado);

        return repository.save(atualizado);
    }

    @Override
    public void deletar(String email) {
        repository.deleteById(email);
    }

    // --- Método auxiliar para consumir API de CEP ---
    private void preencherEnderecoPorCep(Contato contato) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            Map<String, String> uriVars = new HashMap<>();
            uriVars.put("cep", contato.getCep());

            @SuppressWarnings("unchecked")
            Map<String, Object> response = restTemplate.getForObject(API_CEP, Map.class, uriVars);

            if (response != null && response.get("status") != null && ((Integer) response.get("status")) == 200) {
                contato.setEndereco((String) response.get("address"));
                contato.setCidade((String) response.get("city"));
                contato.setUf((String) response.get("state"));
            } else {
                throw new RuntimeException("CEP não encontrado ou inválido: " + contato.getCep());
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao consultar CEP: " + contato.getCep(), e);
        }
    }
}
