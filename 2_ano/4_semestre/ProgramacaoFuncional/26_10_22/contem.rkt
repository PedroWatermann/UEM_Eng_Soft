#lang racket

(define (contains-3 list) (
    cond
        [(empty? list) #f]
        [(= (first list) 3) #t]
        [else (contains-3 (rest list))]
))

(define (contains-5 list) (
    cond
        [(empty? list) #f]
        [(= (first list) 5) #t]
        [else (contains-5 (rest list))]
))

(define (contains-x? list x) (
    cond
        [(empty? list) #f]
        [(= (first list) x) #t]
        [else (contains-x? (rest list) x)]
))

(contains-3 (list 3 1 2))
(contains-3 (list 5 1 2))
(contains-5 (list 3 5 2))
(contains-5 (list 3 1 2))
(contains-x? (list 3 1 2) 3)
(contains-x? (list 3 5 2) 5)
(contains-x? (list 3 5 2) 1)
