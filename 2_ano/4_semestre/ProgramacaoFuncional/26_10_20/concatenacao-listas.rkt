#lang racket

(define (concat first-list second-list) (
    cond
        [(empty? first-list) second-list]
        [else (cons (first first-list) (concat (rest first-list) second-list))]
))

(concat (list 1 2 3) (list 4 5 6)) ;'(1 2 3 4 5 6)