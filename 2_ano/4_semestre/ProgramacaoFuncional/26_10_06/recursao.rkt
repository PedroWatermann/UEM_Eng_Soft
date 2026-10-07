#lang racket
(require rackunit)

(define (contem lst val) (
    cond
    [(empty? lst) #f]
    [(equal? (first lst) val)]
    [else (contem (rest lst) val)]
))

(define l (list 4 8 3 6 5 0 2))

(check-equal? (contem l 6) #t)
(check-equal? (contem l 9) #f)
