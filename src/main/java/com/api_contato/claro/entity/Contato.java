package com.api_contato.claro.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "tb_contato")
public class Contato {

    @Id
    @Column(name = "email", nullable = false, length = 150)
    private String email;

    @Column(name = "nome", nullable = false, length = 200)
    private String nome;

    @Column(name = "telefone", length = 50)
    private String telefone;

    @Column(name = "cep", length = 20, nullable = false)
    private String cep;

    @Column(name = "endereco", length = 400)
    private String endereco;

    @Column(name = "cidade", length = 150)
    private String cidade;

    @Column(name = "uf", length = 5)
    private String uf;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_cadastro", nullable = false)
    private Date dataCadastro;

    public Contato() {}

    public Contato(String email, String nome, String telefone, String cep,
                   String endereco, String cidade, String uf, Date dataCadastro) {
        this.email = email;
        this.nome = nome;
        this.telefone = telefone;
        this.cep = cep;
        this.endereco = endereco;
        this.cidade = cidade;
        this.uf = uf;
        this.dataCadastro = dataCadastro;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCep() {
        return cep;
    }
    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getEndereco() {
        return endereco;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCidade() {
        return cidade;
    }
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }
    public void setUf(String uf) {
        this.uf = uf;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }
    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
}
