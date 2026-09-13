#lang racket

(struct contagem(verde vermelho azul branco) #:transparent)

(define (atualiza cont resp) (
    cond
        [(equal? resp "branco") (struct-copy contagem cont [branco (+ 1 (contagem-branco cont))])]
        [(equal? resp "azul") (struct-copy contagem cont [azul (+ 1 (contagem-azul cont))])]
        [(equal? resp "vermelho") (struct-copy contagem cont [vermelho (+ 1 (contagem-vermelho cont))])]
        [(equal? resp "verde") (struct-copy contagem cont [verde (+ 1 (contagem-verde cont))])]
        [else cont]
))

(atualiza (contagem 1 1 1 1) "branco")
(atualiza (contagem 1 1 1 1) "azul")
(atualiza (contagem 1 1 1 1) "vermelho")
(atualiza (contagem 1 1 1 1) "verde")