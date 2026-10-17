# Lista 4

## Ex. 16 — Caso de Uso × Diagrama de Sequência

- **Caso de Uso:** como o ator alcança seu objetivo usando o sistema (visão de uso, sistema como caixa-preta).
- **Diagrama de Sequência:** como os participantes colaboram ao longo do tempo para realizar um cenário.

**Não substitui o UC porque:** mostra um cenário só; não traz objetivo, pré/pós-condições, regras nem requisitos; e já traz decisões de projeto (quem faz o quê). Sem o UC, perde-se a rastreabilidade.

---

## Ex. 17 — Transferência bancária: participantes

| Categoria | Participante | Responsabilidade |
| --- | --- | --- |
| Ator | Cliente | Pede a transferência e recebe o resultado |
| Interface | TelaTransferencia | Capta dados, envia e mostra o resultado |
| Controle | TransferenciaController | Orquestra o caso de uso |
| Domínio | Conta / Transferencia | Regras: saldo suficiente, conta ativa, valor positivo |
| Persistência | ContaRepository, MovimentacaoRepository | Busca a conta/saldo e grava a movimentação |

---

## Ex. 18 — UC03 Reservar Sala

```text
Professor        -> TelaReserva        : solicitarReserva()
TelaReserva      --> Professor         : pede data e horário
Professor        -> TelaReserva        : informarDataHorario(data, horario)
TelaReserva      -> ReservaController  : buscarSalaDisponivel(data, horario)
ReservaController-> ReservaService     : verificarDisponibilidade(data, horario)
ReservaService   -> ReservaRepository  : buscarReservas(data, horario)
ReservaRepository--> ReservaService    : reservas existentes
ReservaService   --> ReservaController : sala disponível
ReservaController--> TelaReserva       : sala disponível
TelaReserva      --> Professor         : apresenta a sala
Professor        -> TelaReserva        : confirmarReserva()
TelaReserva      -> ReservaController  : confirmarReserva(sala, data, horario)
ReservaController-> ReservaService     : registrarReserva(...)
ReservaService   -> ReservaRepository  : salvar(reserva)
ReservaRepository--> ReservaService    : ok
ReservaService   --> ReservaController : reserva registrada
ReservaController--> TelaReserva       : confirmação
TelaReserva      --> Professor         : exibe confirmação
```

---

## Ex. 19 — Decisão com alt (RN05)

Depois de `buscarReservas(...)` no ReservaService, usar:

```text
alt [sala disponível — sem reserva conflitante]
    ReservaService -> ReservaRepository : salvar(reserva)
    ... confirmação ao Professor
else [sala indisponível — conflito de período]
    ReservaService --> ReservaController --> TelaReserva --> Professor : "sala indisponível"
    (nada é salvo)
end
```

**Como a RN05 aparece:** ela é a condição de guarda do `alt`. A consulta de reservas conflitantes decide o ramo: havendo conflito, nada é gravado; sem conflito, a reserva é salva.

---

## Ex. 20 — Loop

```text
loop [para cada produto]
    PedidoService -> EstoqueRepository : verificarDisponibilidade(produto)
    PedidoService -> Pedido            : adicionarItem(produto)
    PedidoService -> Pedido            : calcularSubtotal(item)
end
```

**Por que loop:** o número de produtos é variável; repetir mensagens fixaria uma quantidade e poluiria o diagrama. O `loop` mostra a repetição e sua condição de forma explícita.

---

## Ex. 21 — Quem deveria fazer isso?

1. **Problema:** a TelaPedido faz tudo (regras, persistência e e-mail); alto acoplamento e baixa coesão.
2. **Concentrado indevidamente:** validarCliente, verificarEstoque, calcularDesconto e calcularTotal (regras de negócio); salvarPedido (persistência); enviarEmail (infraestrutura); a orquestração também.
3. **Distribuição:**

| Camada | Fica com |
| --- | --- |
| Interface | captar dados, chamar o controller, exibir resultado |
| Controller/Aplicação | orquestrar o registro do pedido |
| Domínio | validar cliente, verificar estoque, calcular desconto e total |
| Persistência | salvarPedido |
| Infraestrutura | enviarEmail |

**Solução:**

```text
TelaPedido        -> PedidoController : registrarPedido(dados)
PedidoController  -> PedidoService    : registrarPedido(dados)
PedidoService     -> Cliente          : validar()
PedidoService     -> Pedido           : verificarEstoque() / calcularDesconto() / calcularTotal()
PedidoService     -> PedidoRepository : salvar(pedido)
PedidoService     -> EmailService     : enviarConfirmacao(pedido)
PedidoService     --> PedidoController --> TelaPedido : resultado
```

---

## Ex. 22 — Mensagem `bloquearCliente()` sem origem

Qualquer das quatro opções pode ser a certa. Decide-se rastreando a origem da mensagem:

1. Existe passo de UC, regra ou requisito que a justifique? Se sim, o diagrama ou o UC estão desalinhados.
2. O negócio quer isso? Se sim, é regra não documentada (política do negócio) ou requisito incompleto: documentar RN, RF e atualizar o UC.
3. Ninguém do negócio reconhece e não altera nada visível? Pode ser decisão interna legítima de projeto; manter e justificar.
4. Altera o comportamento visível (bloquear alguém é visível)? Então não é decisão interna: formalizar ou remover.

Como bloquear um cliente muda o estado do negócio, o mais provável é regra não documentada ou requisito incompleto, e deve-se validar com o negócio.

---

## Ex. 23 — Clínica: agendamento de consultas

### 1. Regras de negócio

- RN01: um profissional não pode ter duas consultas no mesmo horário.
- RN02: só se agenda em horários disponíveis.
- RN03: o paciente pode cancelar até 24 h antes.
- RN04: consultas e cancelamentos devem ser registrados.

### 2. Requisitos funcionais

- RF01: listar especialidades e profissionais.
- RF02: exibir horários disponíveis (RN02).
- RF03: agendar consulta (RN01, RN02).
- RF04: impedir agendamento em horário ocupado (RN01, RN02).
- RF05: cancelar consulta (RN03).
- RF06: recusar cancelamento com menos de 24 h (RN03).
- RF07: registrar agendamentos e cancelamentos (RN04).

### 3. Requisitos não funcionais

- RNF01 (desempenho): consulta de horários em até 2 s.
- RNF02 (consistência): dois pacientes não podem agendar o mesmo horário ao mesmo tempo.
- RNF03 (segurança): só pacientes autenticados agendam e cancelam suas consultas.
- RNF04 (auditoria): registros preservados e não alteráveis.

**4. Atores:** Paciente (principal); Profissional de saúde (mantém sua agenda).

**5. Casos de uso:** UC01 Consultar Horários Disponíveis · UC02 Agendar Consulta · UC03 Cancelar Consulta · UC04 Consultar Minhas Consultas.

### 6–7. UC02 — Agendar Consulta

- Objetivo: reservar consulta com um profissional em horário disponível.
- Ator: Paciente.
- Pré: paciente autenticado; profissional com agenda cadastrada.
- Pós (sucesso): consulta registrada, horário ocupado e agendamento gravado no histórico.
- Pós (falha): nada é criado.

Fluxo principal

1. Paciente solicita agendar consulta.
2. Sistema apresenta as especialidades.
3. Paciente escolhe a especialidade.
4. Sistema apresenta os profissionais.
5. Paciente escolhe o profissional.
6. Paciente informa a data.
7. Sistema apresenta os horários disponíveis (RN02).
8. Paciente escolhe o horário.
9. Sistema verifica se o horário está livre e sem outra consulta do profissional (RN01, RN02).
10. Paciente confirma.
11. Sistema registra a consulta e o histórico (RN04).
12. Sistema informa a confirmação.

Alternativos: A1 trocar profissional (retorna ao passo 4) · A2 sem horários na data (sugere outra data) · A3 paciente desiste antes de confirmar.
Exceções: E1 horário ocupado entre a escolha e a confirmação (passo 9) · E2 falha ao gravar (passo 11) · E3 sessão expirada.

### 8. Rastreabilidade

| RN | RF/RNF | UC |
| --- | --- | --- |
| RN01 | RF03, RF04, RNF02 | UC02 |
| RN02 | RF02, RF03, RF04 | UC01, UC02 |
| RN03 | RF05, RF06 | UC03 |
| RN04 | RF07, RNF04 | UC02, UC03 |

**9–10. Checklist e correções:** o rascunho inicial não tinha verificação de disponibilidade na confirmação, fluxos alternativos, exceções, pós-condição de falha, nem o registro do histórico (RN04). Foram incluídos no UC acima.

**11. Participantes:** Paciente, TelaAgendamento, AgendamentoController, AgendamentoService, Horario (domínio), AgendaRepository, ConsultaRepository, RegistroRepository.

### 12–13. Diagrama (com alt)

```text
Paciente              -> TelaAgendamento      : solicitar agendamento, escolher especialidade, profissional, data
TelaAgendamento       -> AgendamentoController: buscarHorarios(profissional, data)
AgendamentoController -> AgendamentoService   : buscarHorariosDisponiveis(...)
AgendamentoService    -> AgendaRepository     : horariosDo(profissional, data)
AgendaRepository      --> AgendamentoService  : horários
AgendamentoService    --> TelaAgendamento     : horários disponíveis (exibidos ao Paciente)
Paciente              -> TelaAgendamento      : escolhe horário e confirma
TelaAgendamento       -> AgendamentoController: agendarConsulta(...)
AgendamentoController -> AgendamentoService   : agendarConsulta(...)
AgendamentoService    -> Horario              : estaDisponivel()   [RN01, RN02]
Horario               -> ConsultaRepository   : existeConsulta(profissional, data, horario)

alt [horário disponível]
    AgendamentoService -> ConsultaRepository  : salvar(consulta)
    AgendamentoService -> RegistroRepository  : registrarAgendamento(consulta)   [RN04]
    AgendamentoService --> AgendamentoController --> TelaAgendamento --> Paciente : confirmação
else [horário indisponível]
    AgendamentoService --> AgendamentoController --> TelaAgendamento --> Paciente : horário indisponível
end
```

### 14. Responsabilidades

- Paciente: dispara e confirma o UC.
- TelaAgendamento: exibe opções e resultados, sem regras.
- Controller: recebe a requisição e delega.
- AgendamentoService: coordena verificação, gravação e registro.
- Horario (domínio): aplica RN01 e RN02.
- Repositories: buscar e gravar dados e histórico.

---

## Ex. 24 — Projeto da equipe (VanTrack, UC-S04 Cancelar Utilização do Transporte)

### Regra de negócio

- RN-006: cancelar a qualquer momento antes do embarque; depois o aluno fica ausente.
- RN-007: notificar o motorista do cancelamento.
- RN-008: remover o ponto do aluno e recalcular a rota.

**Requisito:** RF-005 (cancelar), RF-006 (notificar motorista), RF-008 (recalcular rota); RNF-002, RNF-003.

**Caso de uso:** UC-S04, com `<<include>>` UC-I01 e `<<extend>>` UC-X02.

### Fluxo principal (original)

1. Usuário seleciona aluno e trajeto com presença confirmada.
2. Sistema verifica que o embarque não foi registrado.
3. Sistema pede confirmação.
4. Usuário confirma.
5. Sistema altera para ausente e registra data e horário.
6. Sistema executa UC-I01 (notifica o motorista).
7. Sistema exibe "Presença cancelada".
(Após o passo 5, se a rota já foi gerada, executa UC-X02.)

**Alternativo / Exceção (originais):** nenhum alternativo; exceção E1 — embarque já registrado.

**Checklist / revisão:** ver Ex. 12.

### UC corrigido

- Pré: usuário autenticado e vinculado ao aluno; presença confirmada; embarque não registrado.
- Regras: RN-002, RN-005, RN-006, RN-007, RN-008, RN-012, RN-015.

Fluxo principal

1. Usuário seleciona aluno e trajeto.
2. Sistema verifica vínculo (RN-002) e presença confirmada.
3. Sistema verifica que o embarque não foi registrado (RN-006).
4. Sistema pede confirmação.
5. Usuário confirma.
6. Sistema altera para ausente e registra data e horário.
7. Sistema executa UC-I01 (RN-007, RN-012).
8. Se a rota já foi gerada, executa UC-X02 (RN-008).
9. Sistema exibe "Presença cancelada".

Alternativo: A1 usuário desiste (passo 5).
Exceções: E1 embarque já registrado · E2 sem conectividade · E3 usuário sem vínculo · E4 presença já cancelada · E5 falha na notificação ou sem motorista ativo.

### Diagrama de sequência

```text
Usuário            -> TelaCancelamento     : escolhe "Cancelar presença", seleciona aluno e trajeto
TelaCancelamento   -> PresencaController   : solicitarCancelamento(aluno, trajeto)
PresencaController -> PresencaService      : cancelar(usuario, aluno, trajeto)
PresencaService    -> PresencaRepository   : buscarPresenca(aluno, trajeto)
PresencaRepository --> PresencaService     : presença + situação do embarque
PresencaService    -> Presenca             : podeCancelar(usuario)   [RN-002, RN-006]

alt [cancelamento permitido]
    PresencaService --> TelaCancelamento --> Usuário : pede confirmação
    Usuário         -> TelaCancelamento --> PresencaService : confirma
    PresencaService -> Presenca             : cancelar(dataHora)   [status = ausente]
    PresencaService -> PresencaRepository   : salvar(presenca)
    PresencaService -> NotificacaoService   : notificarMotorista(aluno, rota)   [UC-I01, RN-007]
    opt [rota do dia já gerada]
        PresencaService -> RotaService      : recalcularRota(rota, aluno)   [UC-X02, RN-008]
    end
    PresencaService --> TelaCancelamento --> Usuário : "Presença cancelada"
else [embarque já registrado / sem vínculo / já cancelada]
    PresencaService --> TelaCancelamento --> Usuário : informa o motivo
end
```

### Responsabilidades e justificativa

| Elemento | Responsabilidade | Origem |
| --- | --- | --- |
| Usuário | inicia e confirma | ator principal; passos 1 e 5 |
| TelaCancelamento | exibe e coleta | passos 1, 4, 9 |
| PresencaController | recebe e delega | orquestração do UC |
| PresencaService | coordena o fluxo | fluxo principal |
| Presenca (domínio) | regras de cancelar e virar ausente | RN-006 |
| PresencaRepository | busca e grava | RF-005 |
| NotificacaoService | avisa o motorista | RN-007, RN-012, RF-006 |
| RotaService | recalcula rota | RN-008, RF-008 |
| alt | pode ou não cancelar | RN-006; E1, E3, E4 |
| opt | recálculo só se a rota já existe | `<<extend>>` UC-X02 |

---

## Questão final

O diagrama **também ajuda a descobrir problemas e decisões de projeto**. Exemplos:

1. **Ex. 19 (reserva de sala):** o UC dizia só "sistema verifica disponibilidade". Ao desenhar, foi preciso definir o que acontece se a sala estiver ocupada (surgiu o `alt`) e que a disponibilidade deve ser checada de novo na hora de gravar.
2. **Ex. 21 (TelaPedido):** colocar tudo na tela mostrou responsabilidades na camada errada, forçando a distribuir entre controller, domínio, persistência e infraestrutura.
3. **Ex. 24 (VanTrack):** o diagrama mostrou que faltava verificar o vínculo do usuário (RN-002), tratar falha na notificação e definir a ordem entre notificar e recalcular a rota.
