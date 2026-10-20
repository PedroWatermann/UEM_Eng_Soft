#lang racket

(define (weighted-sum first-list second-list) (
    cond
        [(and (empty? first-list) (empty? second-list)) 0]
        [else (
            + 
                (* (first first-list) (first second-list)) 
                (weighted-sum (rest first-list) (rest second-list))
        )]
))

(weighted-sum empty empty) ;0
(weighted-sum (list 4) (list 2)) ;8
(weighted-sum (list 3 4) (list 5 2)) ;23
(weighted-sum (list 5 3 4) (list 1 5 2)) ;28
