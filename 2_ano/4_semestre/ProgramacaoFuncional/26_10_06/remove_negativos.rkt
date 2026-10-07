#lang racket

(define (remove-negativos lst) (
    cond
    [(empty? lst) 
        empty
    ]
    [(< (first lst) 0) 
        (remove-negativos rest lst)
    ]
    [else 
        (cons (first lst) (remove-negativos (rest lst)))
    ]
))
