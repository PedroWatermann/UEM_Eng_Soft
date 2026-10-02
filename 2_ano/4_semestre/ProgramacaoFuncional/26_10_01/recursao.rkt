#lang racket
(require rackunit)

(struct lista (primeiro resto) #:transparent)
(struct vazia() #:transparent)

(define lv (vazia))
(define l1 (lista 6 (lista 2 (lista 3 (vazia)))))
(define l2 (lista 9 (vazia)))

(equal? lv (lista-resto l2))

; ===========================================================================

(define (soma-lista lst) (
    cond
    [(vazia? lst) 0]
    [else (
        + (lista-primeiro lst) (soma-lista (lista-resto lst))
    )]
))

(check-equal? (soma-lista lv) 0)
(check-equal? (soma-lista l1) 11)
(check-equal? (soma-lista l2) 9)

; ===========================================================================

(define (soma-lista-2 lst) {
    cond
    [(empty? lst) 0]
    [else (
        + (first lst) (soma-lista-2 (rest lst))
    )]
})

(check-equal? (soma-lista-2 (list 1 2 3 4)) 10)
(check-equal? (soma-lista-2 empty) 0)
(check-equal? (soma-lista-2 '()) 0)
(check-equal? (soma-lista-2 (list)) 0)
