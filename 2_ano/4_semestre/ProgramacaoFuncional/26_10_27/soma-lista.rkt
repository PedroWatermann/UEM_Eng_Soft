#lang racket

(define (list-sum list) (
    cond
        [(empty? list) 0]
        [else (
            + (first list) (list-sum (rest list))
        )]
))

(define (list-mult list) (
    cond
        [(empty? list) 1]
        [else (
            * (first list) (list-mult (rest list))
        )]
))

(list-sum (list 1 2 3 4 5))
(list-mult (list 1 2 3 4 5))

(define (reduct function base list) (
    cond
        [(empty? list) base]
        [else (
            function (first list) (reduct function base (rest list))
        )]
))

(reduct + 0 (list 1 2 3 4 5))
(reduct * 1 (list 1 2 3 4 5))
