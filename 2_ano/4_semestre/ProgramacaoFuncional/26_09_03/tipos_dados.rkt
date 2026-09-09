#lang racket

(struct ponto (x y) #:transparent)

(define origem (ponto 0 0))

; ===================================================================================================
origem

(define x 100)

; ===================================================================================================
(ponto? x)
(ponto? origem)

; ===================================================================================================
(struct aluno (ra nome curso) #:transparent)
(define eu (aluno 145121 "Pedro" "Engenharia de Sotware"))
eu
(aluno-nome eu) ; Acesar um atributo