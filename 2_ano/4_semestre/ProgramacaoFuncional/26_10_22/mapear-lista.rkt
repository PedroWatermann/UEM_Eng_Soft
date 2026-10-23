#lang racket

(define (square-list list) (
    cond
        [(empty? list) empty]
        [else (
            cons (sqr (first list)) (square-list (rest list))
        )]
))

(define (add1-list list) (
    cond
        [(empty? list) empty]
        [else (
            cons (add1 (first list)) (add1-list (rest list))
        )]
))

(define (map-list function list) (
    cond
        [(empty? list) empty]
        [else (
            cons (function (first list)) (map-list function (rest list))
        )]
))

(square-list (list 1 2 3 4))
(add1-list (list 1 2 3 4))
(map-list sqr (list 1 2 3 4))
(map-list add1 (list 1 2 3 4))
