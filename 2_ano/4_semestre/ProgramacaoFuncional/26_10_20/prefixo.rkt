#lang racket

(define (prefix? first-list second-list) (
    cond
        [(empty? first-list) #t]
        [(empty? second-list) #f]
        [else (
            and 
            (= (first first-list) (first second-list))
            (prefix? (rest first-list) (rest second-list))
        )]
))

(prefix? empty empty) ;#t
(prefix? empty (list 3 2 1)) ;#t
(prefix? (list 3 2 1) empty) ;#f
(prefix? (list 3 4) (list 3 4)) ;#t
(prefix? (list 3 4) (list 3 5)) ;#f

(prefix? (list 3 4 5) (list 3 4 5 6 7)) ;#t
(prefix? (list 4 5) (list 4 5 6 7)) ;#t
(prefix? (list 5) (list 5 6 7)) ;#t
(prefix? (list) (list 6 7)) ;#t
