#lang racket

(define (positive-list list) (
    cond
        [(empty? list) list]
        [(positive? (first list)) (
            cons (first list) (positive-list (rest list))
        )]
        [else (positive-list (rest list))]
))

(define (even-list list) (
    cond
        [(empty? list) list]
        [(even? (first list)) (
            cons (first list) (even-list (rest list))
        )]
        [else (even-list (rest list))]
))

(positive-list (list -4 3 -2 8 -1 -0))
(even-list (list 1 2 3 4 5 6 7 8 9))

(define (filter predicate? list) (
    cond
        [(empty? list) list]
        [(predicate? (first list)) (
            cons (first list) (filter predicate? (rest list))
        )]
        [else (filter predicate? (rest list))]
))

