#lang racket

;! Variables definitions
(define sample-list '(1 2 3))
(define sample-list-two '(4 5 6))

(car sample-list) ;Contents of Address part of Register
(cdr sample-list) ;Contents of Decrement part of Register

;! Operations with lists
(car (cdr sample-list))
(cdr (cdr sample-list))
(cdr (cdr (cdr sample-list)))

(cons 4 '(5 6)) ;CONStruct a list

;! Comparation operator
(equal? (cons (car sample-list) (cdr sample-list)) sample-list) ;equal? is the equals operator ==

;! Funcions definitions
(define (compare-list list-one list-two) 
    (equal? list-one list-two))

(compare-list sample-list sample-list-two)

;! Conditional operator and recursive function
(define (increments-list-elements l)
    (cond
        [(empty? l) '()]
        [else (cons (+ 1 (car l)) (increments-list-elements (cdr l)))]
    )
)

(increments-list-elements sample-list)

;! Receiving a function as a parameter
(define (custom-list-function f l)
    (cond
        [(empty? l) '()]
        [else (cons (f (car l)) (custom-list-function f (cdr l)))]
    )
)

(custom-list-function (lambda (e) (+ 1 e)) sample-list)

;! Definition of a recursive function with a helper function inside
(define (process-list f l) 
    (define (process-list-rec l) 
        (cond 
            [(empty? l) '()]
            [else (cons (f (car l)) (process-list-rec (cdr l)))]
        )
    ) (process-list-rec l)
)

(process-list (lambda (e) (* 2 e)) sample-list)

;! The map function (produces the same result as the fuction above)
(map (lambda (e) (* 3 e)) sample-list)

