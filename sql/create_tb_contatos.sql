CREATE TABLE tb_contato (
  email VARCHAR2(150) PRIMARY KEY,
  nome VARCHAR2(200),
  telefone VARCHAR2(50),
  cep VARCHAR2(20),
  endereco VARCHAR2(400),
  cidade VARCHAR2(150),
  uf VARCHAR2(5),
  data_cadastro TIMESTAMP
);