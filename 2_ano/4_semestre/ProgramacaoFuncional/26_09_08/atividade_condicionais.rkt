#lang racket

(newline)

; 1. Faça uma função que leia um número inteiro e diga se este número é menor que zero, igual a zero ou maior que zero.
(define (compare-com-zero numero) (
    cond
        [(> numero 0) "Maior que zero"]
        [(< numero 0) "Menor que zero"]
        [else "Igual a zero"]
))
(compare-com-zero -1)
(compare-com-zero 1)
(compare-com-zero 0)
(newline)

; 2. Faça uma função para ler um número e, se ele for maior do que 20, então retornar a metade do número, caso contrario, retonar o próprio número.
(define (compare-com-vinte numero) (
    if (> numero 20)
        (/ 20 2.0)
        numero
))
(compare-com-vinte 35)
(compare-com-vinte 19)
(newline)

; 3. Faça uma função que receba um número e informe se ele é par ou impar
(define (verifica-polaridade numero) (
    if (equal? (modulo numero 2) 0)
        "O número é par"
        "O número é ímpar"
))
(verifica-polaridade 22)
(verifica-polaridade 11)
(newline)

; 4. Faça um função para receber a idade de uma pessoa e informar: -Se ela é maior de idade; -Se ela é menor de idade; -Se ela tem mais de 65 anos;
(define (verifica-idade idade) (
    cond
        [(< idade 18) "Você é menor de idade"]
        [(> idade 65) "Você é velho(a)"]
        [else "Você é maior de idade"]
))
(verifica-idade 19)
(verifica-idade 66)
(verifica-idade 17)
(newline)

; 5. Faça uma função que receba uma temperatura em graus celsios e uma string. Se a string informada for ‘Kelvin’ seu algoritmo deverá converter a temperatura para Kelvin. Se a string for ‘Fahrenheit’ a temperatura deverá ser convertida para Fahrenheit. K = C + 273,15; F = (9 * c + 160) / 5
(define (converte-temperatura temperatura-em-celsios unidade) (
    cond
        [(equal? unidade "Kelvin") (+ temperatura-em-celsios 273.15)]
        [(equal? unidade "Fahrenheit") (/ (+ (* 9 temperatura-em-celsios) 160) 5.0)]
        [else "Esta unidade de medida não é conhecida"]
))
(converte-temperatura 26 "Kelvin")
(converte-temperatura 26 "Fahrenheit")
(newline)

; 6. Faça um função que receba o salário de um funcionário e calcule e retorne seu imposto de renda com base na tabela a seguir: 
; Intervalo do Salário Desconto do imposto de renda
; Até 1500,00 Isento
; Entre 1500,01 e 3000,00 10%
; Entre 3000,01 e 4500,00 20%
; Acima de 4500,01 30%
(define (calcula-ir salario) (
    cond
        [(> salario 4500) (* salario 0.3)]
        [(> salario 3000) (* salario 0.2)]
        [(> salario 1500) (* salario 0.1)]
        [else 0.0]
))
(calcula-ir 6000)
(calcula-ir 3543)
(calcula-ir 2395)
(calcula-ir 907)
(newline)

; 7. Faça uma função que receba um valor para x e retorne o valor de f(x):
; 1, se x <= 1 
; 2, se 1 < x <= 2 
; x^2, se 2 < x <= 3 
; x^3, se x > 3
(define (retorna-f-x x) (
    cond
        [(<= x 1) 1]
        [(<= x 2) 2]
        [(<= x 3) (expt x 2)]
        [else (expt x 3)]
))
(retorna-f-x 0.3)
(retorna-f-x 1.3)
(retorna-f-x 3)
(retorna-f-x 4.5)
(newline)

; 8. A Chevrolet abriu uma linha de crédito para compra de carros aos seus clientes. O valor máximo da prestação não poderá ultrapassar 40% do salário bruto. Faça uma função que receba o salário bruto e o valor da prestação e retorne se o emprestimo pode ou não ser concedido.
(define (validar-prestacao salario-bruto valor-prestacao) (
    if (<= valor-prestacao (* salario-bruto 0.4))
        "Empréstimo concedido"
        "Empréstimo não concedido"
))
(validar-prestacao 100 50)
(validar-prestacao 100 39)
(newline)

