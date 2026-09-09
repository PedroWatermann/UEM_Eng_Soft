#lang racket

(require rackunit)
(require rackunit/example-check)

(define (massa-tubo dia-ext dia-int altura)
    (* (sqrt (/ (- dia-ext dia-int) 2)) 
        pi
        altura
        7874))

(example-check (massa-tubo 0.05 0.03 0.1) 0.2472436 0.00000001)