-- Active: 1792712798907@@127.0.0.1@3306@vendas
--CREATE SCHEMA vendas;

CREATE TABLE endereco (
    id_endereco INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    cep INT(8) NOT NULL,
    logradouro VARCHAR(200),
    numero VARCHAR(20),
    complemento VARCHAR(200)
);

CREATE TABLE pessoa (
    id_pessoa int PRIMARY KEY NOT NULL AUTO_INCREMENT,
    nome VARCHAR(200) NOT NULL,
    sobrenome VARCHAR(200) NOT NULL,
    data_nascimento DATE
);

CREATE TABLE telefone (
    id_telefone INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    numero BIGINT(12) NOT NULL
);

CREATE TABLE pedido (
    id_pedido INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    desc_produto VARCHAR(50) NOT NULL,
    valor DECIMAL(5,2),
    qtde INT(9),
    total DECIMAL(10,2)
);
