CREATE TABLE tb_contato (
    email VARCHAR2(150) PRIMARY KEY,
    nome VARCHAR2(200) NOT NULL,
    telefone VARCHAR2(50),
    cep VARCHAR2(20) NOT NULL,
    endereco VARCHAR2(400),
    cidade VARCHAR2(150),
    uf VARCHAR2(5),
    data_cadastro TIMESTAMP NOT NULL
);