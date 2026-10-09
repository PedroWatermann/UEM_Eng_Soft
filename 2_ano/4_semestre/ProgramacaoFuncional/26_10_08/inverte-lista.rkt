#lang racket
(require rackunit)

(define (inverte lst) (
    cond
        [(empty? lst) empty]
        [(append (inverte (rest lst)) (list (first lst)))]
))

(check-equal? (inverte (list 1 2 3 4)) (list 4 3 2 1))
(check-equal? (inverte (list 1 2 2 1)) (list 1 2 2 1))
(check-equal? (inverte empty) '())
