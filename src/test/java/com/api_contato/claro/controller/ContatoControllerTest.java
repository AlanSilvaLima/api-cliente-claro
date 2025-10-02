package com.api_contato.claro.controller;

import com.api_contato.claro.entity.Contato;
import com.api_contato.claro.service.ContatoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class ContatoControllerTest {

    @Mock
    private ContatoService service;

    @InjectMocks
    private ContatoController controller;

    private Contato contato;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        contato = new Contato("teste@email.com", "Fulano", "1199999999", "01001-000",
                "Praça da Sé", "São Paulo", "SP", new Date());
    }

    @Test
    void deveCriarContato() {
        when(service.salvar(any(Contato.class))).thenReturn(contato);

        ResponseEntity<Contato> response = controller.criar(contato);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Fulano", response.getBody().getNome());
    }

    @Test
    void deveBuscarPorEmail() {
        when(service.buscarPorEmail("teste@email.com")).thenReturn(Optional.of(contato));

        ResponseEntity<Contato> response = controller.buscarPorEmail("teste@email.com");

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Fulano", response.getBody().getNome());
    }

    @Test
    void deveRetornarNotFoundQuandoNaoExiste() {
        when(service.buscarPorEmail("naoexiste@email.com")).thenReturn(Optional.empty());

        ResponseEntity<Contato> response = controller.buscarPorEmail("naoexiste@email.com");

        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    void deveListarTodos() {
        when(service.listarTodos()).thenReturn(Collections.singletonList(contato));

        ResponseEntity<List<Contato>> response = controller.listarTodos();

        assertEquals(200, response.getStatusCodeValue());
        assertFalse(response.getBody().isEmpty());
    }
}
