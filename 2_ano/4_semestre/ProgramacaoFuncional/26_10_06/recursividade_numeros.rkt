#lang racket
(require rackunit)

(define (soma-ate n) (
    cond
    [(zero? n) 0]
    [else (+ n (soma-ate (sub1 n)))]
))

