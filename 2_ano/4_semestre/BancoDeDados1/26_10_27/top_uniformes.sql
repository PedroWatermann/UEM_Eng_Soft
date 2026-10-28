-- Active: 1792712798907@@127.0.0.1@3306@top_uniformes
--CREATE SCHEMA top_uniformes;

CREATE TABLE vendas (
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    id_cliente INT NOT NULL,
    id_vendedor INT NOT NULL,
    id_produto INT NOT NULL,
    qtd_venda INT NOT NULL,
    valor_total DECIMAL(10,2) NOT NULL
);

CREATE TABLE produtos (
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    nome VARCHAR(200) NOT NULL,
    qtd_estoque INT NOT NULL
);

CREATE TABLE vendedor (
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    nome VARCHAR(200) NOT NULL,
    comissao FLOAT NOT NULL
);

CREATE TABLE cliente (
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    nome VARCHAR(50) NOT NULL,
    rg INT NOT NULL,
    telefone VARCHAR(50) NOT NULL
);
