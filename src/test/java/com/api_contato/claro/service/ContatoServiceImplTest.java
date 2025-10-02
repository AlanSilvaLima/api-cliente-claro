package com.api_contato.claro.service;

import com.api_contato.claro.entity.Contato;
import com.api_contato.claro.repository.ContatoRepository;
import com.api_contato.claro.service.impl.ContatoServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ContatoServiceImplTest {

    @Mock
    private ContatoRepository repository;

    @InjectMocks
    private ContatoServiceImpl service;

    private Contato contato;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        contato = new Contato("teste@email.com", "Fulano", "1199999999", "01001-000",
                "Praça da Sé", "São Paulo", "SP", new Date());
    }

    @Test
    void deveSalvarContato() {
        when(repository.save(any(Contato.class))).thenReturn(contato);

        Contato salvo = service.salvar(contato);

        assertNotNull(salvo);
        assertEquals("teste@email.com", salvo.getEmail());
        verify(repository, times(1)).save(any(Contato.class));
    }

    @Test
    void deveBuscarPorEmail() {
        when(repository.findById("teste@email.com")).thenReturn(Optional.of(contato));

        Optional<Contato> result = service.buscarPorEmail("teste@email.com");

        assertTrue(result.isPresent());
        assertEquals("Fulano", result.get().getNome());
    }

    @Test
    void deveListarTodos() {
        when(repository.findAll()).thenReturn(Collections.singletonList(contato));

        List<Contato> lista = service.listarTodos();

        assertEquals(1, lista.size());
        assertEquals("Fulano", lista.get(0).getNome());
    }

    @Test
    void deveDeletarContato() {
        doNothing().when(repository).deleteById("teste@email.com");

        service.deletar("teste@email.com");

        verify(repository, times(1)).deleteById("teste@email.com");
    }
}
