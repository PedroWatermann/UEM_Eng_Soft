#lang racket
(require rackunit)

(struct sucesso (tempo msg) #:transparent)
(struct erro (codigo msg) #:transparent)

;EstadoTarefa é somente um dos 3:
; - "em execução"
; - (sucesso Numero String)
; - (erro Numero String)

; consulta-estado é uma função que gera uma mensagem a partir do estado da tarefa
; EstadoTarefa -> String
(define (consulta-estado estado-tarefa) (
    cond
        [(sucesso? estado-tarefa) 
            (string-append "mensagem: " (sucesso-msg estado-tarefa) " tempo: " (number->string (sucesso-tempo estado-tarefa)) "s")]
        [(erro? estado-tarefa)
            (string-append "erro: " (number->string (erro-codigo estado-tarefa)) " - " (erro-msg estado-tarefa))]
        [(equal? estado-tarefa "Em execução")
            ("Tarefa em execução, aguarde a conclusão!")]
))

(check-equal? (consulta-estado (sucesso 10 "Deu certo")) "mensagem: Deu certo tempo: 10s")
(check-equal? (consulta-estado (erro 123 "Erro desconhecido")) "erro: 123 - Erro desconhecido")
(check-equal? (consulta-estado "Em execução") "Tarefa em execução, aguarde a conclusão!")