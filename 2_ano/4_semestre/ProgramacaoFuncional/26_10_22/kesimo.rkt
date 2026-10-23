#lang racket
(require rackunit)

(define (ref-list list k) (
    cond
        [(empty? list) (error "Invalid position")]
        [(zero? k) (first list)]
        [else (
            ref-list (rest list) (sub1 k)
        )]
))

(ref-list (list 3 2 8) 2)
(ref-list (list 3 2 8 6 7) 3)
(ref-list (list 3 2 8 9 0 2) 4)
(ref-list (list 3 2 8 0 1 3 5) 1)
(check-exn exn:fail? (ref-list empty 0)) ;Estoura o erro no console, mas de forma formatada
(check-exn exn:fail? (thunk (ref-list empty 2))) ;thunk adia a execução da função para a validação do check-exn, por isso não estoura erro no console
(check-exn exn:fail? (thunk (ref-list (list 0 1 2) 2))) ;Se for passada uma função válida, será exibido falha na validação do resultado da função com o resultado esperado
(ref-list empty 0) ;Estoura o erro completo no console, com o caminho do arquivo, e encerra a execução do programa
