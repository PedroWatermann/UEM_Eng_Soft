#lang racket

; 1) Faça uma função que receba dois número e gere a média aritmética.
(define (media-aritmetica a b)
  (/ (+ a b) 2.0))

; 2) Escreva uma função que receba um número e retorne seu quadrado.
(define (ao-quadrado n)
  (* n n))

; 3) Escreva uma função que retorne o diâmetro de um circulo.
(define (diametro-circulo r)
  (* 2 r))

; 4) Escreva uma função que retorne o valor da circunferência de um criculo
; - Circunferência= 2 * Pi * Raio
(define (circunferencia-circulo r)
  (* pi 2 r))

; 5) Escreva uma função que receba o raio r e retorne a área do criculo de raio r.
; - Área = Pi * Raio * Raio
(define (area-circulo r)
  (* pi (* r r)))

; 6) Criar uma função que receba os valores da diagonal maior, diagonal menor e calcule e retorne a área de um losango.
; A = (D * d) / 2
(define (area-losango d-menor d-maior)
  (/ (* d-menor d-maior) 2.0))

; 7) Elabore uma função para calcular e retornar o volume de um cilindro de raio r e altura A utilizando a fórmula: V = 3.14159 * (r * r) * A.
(define (volume-cilindro raio altura)
  (* pi raio raio altura))

; 8) Faça uma função que receba 4 númerose retorne a média ponderada, sabendo-se que os pesos são respectivamente: 1, 2, 3 e 4.
(define (media-ponderada n1 n2 n3 n4)
  (/ (+ n1 (* n2 2) (* n3 3) (* n4 4)) (+ 1.0 2 3 4)))

; 9) Faça uma função que receba o valor de um depósito e o valor da taxa de juros (em porcentagem), calcule e retorne o valor total depois do rendimento.
(define (rendimento valor juros)
  (+ valor (* valor (/ juros 100.0))))

; 10) Faça uma função que receba os valores dos catetos de um triângulo retângulo e retorne a hipotenusa. Use a função (sqrt x) para obter a raiz quadrada de um número.
(define (hipotenusa c1 c2)
  (sqrt (+ (* c1 c1) (* c2 c2))))

; 11) Faça uma função que receba uma temperatura em graus centígrados e retorne o valor convertido em graus Fahrenheit. A fórmula de conversão é:
; F = (9 * c + 160) / 5
(define (c-para-f c)
  (/ (+ (* 9 c) 160) 5.0))

; 12) Faça uma função que receba a base menor (b), a base maior (B) e a altura de um trapezio e calcule e retorne sua área.
; A = ((B + b) * h) / 2
(define (area-trapezio b-menor b-maior altura)
  (/ (* (+ b-menor b-maior) altura) 2.0))

; 13) Faça uma função que receba 3 notas e seus respectivos pesos, calcule e retorne a média ponderada dessas notas.
(define (media-ponderada-notas-pesos n1 n2 n3 p1 p2 p3)
  (/ (+ (* n1 p1) (* n2 p2) (* n3 p3)) (+ p1 p2 p3 0.0)))

(media-aritmetica 10 20) ;15
(ao-quadrado 5) ;25
(diametro-circulo 7) ;14
(circunferencia-circulo 5) ;31,4...
(area-circulo 10) ;314,1...
(area-losango 3 6) ;9
(volume-cilindro 6 14) ;1583,36...
(media-ponderada 4 3 2 1) ;2
(rendimento 3427 3) ;3529,81
(hipotenusa 3 4) ;5
(c-para-f 27) ;80,6
(area-trapezio 35 74 53) ;2888,5
(media-ponderada-notas-pesos 5 6 7 1 2 3) ;6,33...
