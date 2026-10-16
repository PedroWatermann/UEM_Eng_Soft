#lang racket

(define (nested-sum list) (
    cond
        [(empty? list) 0]
        [(list? (first list)) (
            + (nested-sum (first list)) (nested-sum (rest list))
        )]
        [else (
            + (first list) (nested-sum (rest list))
        )]
))

(define (flatten-list list) (
    cond
        [(empty? list) list]
        [(list? (first list)) (
            append (flatten-list (first list)) (flatten-list (rest list))
        )]
        [else (
            cons (first list) (flatten-list (rest list))
        )]
))

(nested-sum (list (list (list 5) 1 2 empty 3) (list 1 (list 3 4)) 4))

(flatten-list (list 1 2 3 4))
(flatten-list empty)
(flatten-list (list 1 2 (list 3 4 (list 5 6) 7) 8 9 (list 10 11 12 13)))
