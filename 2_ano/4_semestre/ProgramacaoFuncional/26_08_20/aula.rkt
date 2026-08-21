#lang racket

(define 
    (f x) (
        cond
            [(< x 0) "menor que 0"]
            [(> x 0) "maior que 0"]
            [else "caiu no else"]
    )
)

(define (funcao-or a b) (
    cond
    [(equal? a #t) #t]
    [else b]
))

(f 0)
(funcao-or #t #f)