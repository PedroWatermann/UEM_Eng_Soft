-- Active: 1792712798907@@127.0.0.1@3306@banco_uem
--CREATE SCHEMA banco_uem;

CREATE TABLE alunos (
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    ra INT(6) NOT NULL UNIQUE,
    cpf BIGINT(11) UNIQUE,
    telefone BIGINT(13)
);

CREATE TABLE turmas (
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    id_curso INT NOT NULL,
    nome VARCHAR(100) NOT NULL
);

CREATE TABLE turma_aluno (
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    id_turma INT NOT NULL,
    id_aluno INT NOT NULL,
    sala VARCHAR(15),
    bloco VARCHAR(10)
);

CREATE TABLE cursos (
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    periodo VARCHAR(20) NOT NULL,
    tipo_curso VARCHAR(50),
    duracao INT NOT NULL
);

CREATE TABLE grade (
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    id_turma_aluno INT NOT NULL,
    id_disciplina INT NOT NULL,
    id_professor INT NOT NULL
);

CREATE TABLE disciplina (
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    categoria VARCHAR(50),
    semestre INT NOT NULL
);

CREATE TABLE professores (
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    nome VARCHAR(100),
    email VARCHAR(100),
    telefone BIGINT(13)
);

CREATE TABLE matriculas (
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    id_aluno INT NOT NULL,
    id_curso INT NOT NULL,
    data_matricula DATE NOT NULL,
    status VARCHAR(20) NOT NULL
);

CREATE TABLE notas (
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    id_grade INT NOT NULL,
    nota DECIMAL(4,2) NOT NULL,
    data_avaliacao DATE NOT NULL,
    descricao VARCHAR(100)
);
