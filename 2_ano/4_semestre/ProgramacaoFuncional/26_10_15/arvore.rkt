#lang racket

(struct node (valor left right) #:transparent)

(define (create-node value) (
    node value empty empty
))

(define (insert-node tree node-to-insert side) (
    cond
        [(equal? side "right") (
            struct-copy node tree [right node-to-insert]
        )]
        [(equal? side "left") (
            struct-copy node tree [left node-to-insert]
        )]
        [else tree]
))

(define t0 (create-node 10))
(define t1-aux (create-node 9))
(define t1 (insert-node t1-aux t0 "left"))
(define t2 (node 7 (create-node 8) t1))
(define t3 (node 4 (create-node 3) empty))
(define root (node 3 t3 t2))

(displayln root)

(define (height tree) (
    cond    
        [(empty? tree) 0]
        [else (
            + 1 (
                max 
                    (height (node-left tree)) 
                    (height (node-right tree))
            )
        )]
))

(height root)
(height t0)
(height t1)
(height t2)
(height t3)