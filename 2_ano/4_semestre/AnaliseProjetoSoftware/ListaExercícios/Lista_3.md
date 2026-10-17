# Lista 3

## Ex. 1 — Regra de Negócio × Requisito

- **Regra de Negócio:** política ou restrição do negócio; existe mesmo sem software.
- **Requisito de Software:** o que o sistema deve fazer (funcional) ou qualidade que deve ter (não funcional); precisa ser verificável.

| Item | Classificação | Justificativa |
| --- | --- | --- |
| a) Prazo máximo de 30 dias | Regra de Negócio | Política da organização |
| b) Permitir registrar empréstimo | Requisito Funcional | Serviço do sistema |
| c) Autenticação em dois fatores | Requisito Não Funcional (segurança) | Restrição de segurança do sistema |
| d) Cliente com pagamento vencido não compra | Regra de Negócio | Política do negócio |
| e) Responder em até 2 s | Requisito Não Funcional (desempenho) | Qualidade mensurável |

---

## Ex. 2 — RF e RNF (biblioteca)

| Requisito | Tipo | Categoria |
| --- | --- | --- |
| Permitir empréstimo de livros | RF | — |
| Permitir consulta ao catálogo | RF | — |
| Suportar 500 usuários simultâneos | RNF | Desempenho / capacidade |
| Exigir autenticação para operações administrativas | RNF | Segurança |
| Funcionar nos principais navegadores | RNF | Compatibilidade / portabilidade |
| Pesquisa em até 2 s | RNF | Desempenho |

**Por que RNF não são "menos importantes"?** Um sistema com todas as funções, mas lento, inseguro ou incompatível, é inutilizável. Os RNF atravessam vários casos de uso, afetam a arquitetura e são caros de corrigir no fim.

---

## Ex. 3 — Por que Casos de Uso?

A afirmação é problemática porque RFs isolados não dizem quem usa, qual o objetivo, a sequência, as condições, o que acontece se algo falhar nem quais regras se aplicam.

**O UC acrescenta:** ator e objetivo, gatilho, pré e pós-condições, fluxo principal, alternativos e exceções, ligação com regras e requisitos, e base para critérios de aceitação e testes.

---

## Ex. 4 — Caso de Uso não é tela

| Item | Avaliação |
| --- | --- |
| Abrir Tela de Cliente | Ação de interface |
| Clicar em Salvar | Ação de interface |
| **Cadastrar Cliente** | **Bom caso de uso** |
| Digitar CPF | Passo interno |
| **Registrar Pedido** | **Bom caso de uso** |
| Selecionar Produto | Passo interno |
| **Cancelar Pedido** | **Bom caso de uso** |

**Justificativa:** caso de uso é um objetivo do ator com resultado de valor (verbo + objeto). Telas, cliques e campos são detalhes de interface.

---

## Ex. 5 — Atores

a) Estudante, Professor, Coordenador, Funcionário e o serviço externo de autenticação.
b) Sim. Um sistema externo é ator quando interage com o sistema.
c) Sim. Ator é um papel, e a mesma pessoa pode assumir papéis diferentes.
d) Normalmente não. O banco de dados é parte interna do sistema (persistência), não algo externo que interage em nome de um objetivo.

---

## Ex. 6 — Estacionamento

### Regras de negócio

- RN01: registrar placa e horário na entrada.
- RN02: o valor depende do período de permanência.
- RN03: conveniados têm 20% de desconto.
- RN04: a saída só é liberada após o pagamento.
- RN05: manter o histórico das operações.

### Requisitos funcionais

- RF01: registrar entrada (placa e horário).
- RF02: calcular o valor pelo tempo de permanência.
- RF03: aplicar 20% a conveniados.
- RF04: registrar pagamento.
- RF05: liberar a saída após o pagamento.
- RF06: manter e consultar o histórico.

### Requisitos não funcionais

- RNF01: cálculo da saída em até 2 s.
- RNF02: só atendente autenticado registra pagamento e libera saída.
- RNF03: histórico íntegro e preservado.

**Atores:** Atendente, Cliente, Cliente conveniado.

**Casos de uso:** UC01 Registrar Entrada · UC02 Registrar Saída e Calcular Valor · UC03 Registrar Pagamento e Liberar Saída · UC04 Consultar Histórico.

---

## Ex. 7 — Sistema acadêmico

| RF derivado | Regra |
| --- | --- |
| RF01: o professor registra/altera notas da sua turma | RN02 |
| RF02: validar nota entre 0 e 100 | RN01 |
| RF03: restringir alteração ao professor responsável | RN02 |
| RF04: fechar o período letivo | RN03 |
| RF05: bloquear alteração de nota em período fechado | RN03 |
| RF06: calcular a frequência do aluno | RN04 |
| RF07: reprovar por frequência quem tiver menos de 75% | RN04 |

**Casos de uso afetados:** Registrar/Alterar Nota (RN01, RN02, RN03) · Registrar Frequência (RN04) · Fechar Período Letivo (RN03, RN04) · Consultar Notas/Situação (RN03, RN04).

---

## Ex. 8 — UC01 Cadastro

### Problemas

1. Nome genérico, sem objetivo.
2. Ator vago ("Usuário").
3. Sem gatilho, pré e pós-condições.
4. Passos de interface (abrir tela, clicar no botão).
5. "Digitar os dados" sem dizer quais.
6. "Sistema verifica" sem dizer o quê.
7. Sem fluxos alternativos nem exceções.
8. Sem regras e requisitos relacionados.
9. "Finaliza" não informa o resultado ao ator.

### UC reescrito: UC01 — Cadastrar Cliente

- Ator: Atendente | Pré: atendente autenticado | Pós: cliente cadastrado.
- Regras: RN01 CPF válido e único, RN02 campos obrigatórios.

Fluxo principal

1. Atendente solicita cadastrar um cliente.
2. Sistema solicita nome, CPF, telefone e e-mail.
3. Atendente informa os dados.
4. Sistema valida campos obrigatórios, formato e CPF único.
5. Sistema registra o cliente.
6. Sistema informa o sucesso.

Alternativo: A1 — atendente cancela o cadastro.
Exceções: E1 dados inválidos ou ausentes (volta ao passo 3) · E2 CPF já cadastrado · E3 falha ao salvar.

---

## Ex. 9 — Realizar Empréstimo

### Respeita as regras? Não. Nenhuma regra é verificada

- RN01: não checa empréstimos vencidos.
- RN02: não checa o limite de 5 livros.
- RN03: não checa se o livro está disponível.
- RN04: não calcula/informa o prazo de 14 dias.

**Falta:** objetivo, gatilho, pré e pós-condições, confirmação, fluxos alternativos, exceções, regras relacionadas.

**UC05 — Realizar Empréstimo (corrigido)**
Ator: Bibliotecário | Pré: bibliotecário autenticado; aluno e livro cadastrados | Pós: empréstimo registrado com devolução em 14 dias; livro indisponível.

Fluxo principal

1. Bibliotecário informa o aluno.
2. Sistema localiza o aluno.
3. Sistema verifica: sem empréstimos vencidos (RN01) e menos de 5 livros emprestados (RN02).
4. Bibliotecário informa o livro.
5. Sistema verifica se o livro está disponível (RN03).
6. Sistema calcula a devolução = hoje + 14 dias (RN04).
7. Bibliotecário confirma.
8. Sistema registra o empréstimo e marca o livro como indisponível.
9. Sistema informa o sucesso e a data de devolução.

Alternativo: A1 — bibliotecário cancela antes de confirmar (passo 7).
Exceções: E1 aluno não encontrado (2) · E2 empréstimo vencido (3) · E3 limite de 5 livros (3) · E4 livro não encontrado (5) · E5 livro indisponível (5) · E6 falha ao registrar (8).

---

## Ex. 10 — Realizar Compra

### Alternativos

- Pagar com outra forma (PIX/boleto) — passo 4.
- Informar cupom de desconto — entre os passos 3 e 4.
- Adicionar/remover produto ou mudar o endereço — passos 1 a 4.

### Exceções

- Produto sem estoque — passo 1 (ou antes do 6).
- Pagamento recusado — passo 5.
- Falha de comunicação com o pagamento ou ao registrar o pedido — passos 5 e 6.

---

## Ex. 11 — Alternativo ou exceção?

| Item | Tipo | Justificativa |
| --- | --- | --- |
| a) PIX em vez de cartão | Alternativo | Caminho válido que também dá certo. |
| b) Cartão recusado | Exceção | Impede o objetivo. |
| c) Adiciona outro produto | Alternativo | Variação normal. |
| d) Produto sem estoque | Exceção | Impede ou altera o objetivo. |
| e) Informa cupom | Alternativo | Variação opcional que muda o cálculo. |
| f) Cupom vencido | Exceção | Dado inválido que impede aplicar o cupom. |

**Precisam estar no mesmo UC?** Não. Variações pequenas ficam no UC principal. Comportamentos com objetivo próprio (ex.: aplicar cupom, processar pagamento) podem virar UC separado com `<<include>>` ou `<<extend>>`, para o UC não ficar enorme.

---

## Ex. 12 — Checklist (projeto VanTrack, UC-S04 Cancelar Utilização do Transporte)

Itens que faltam no UC-S04: fluxos alternativos, exceções (só tem uma), regras e requisitos relacionados (incompletos).

| Problema | Por que é problema | Correção |
| --- | --- | --- |
| Sem fluxo alternativo | Usuário pode desistir ao confirmar | A1: usuário desiste, nada muda |
| Sem exceção de conectividade | Cancelar depende de rede (RN-015, RNF-003) | E2: informar falha e permitir nova tentativa |
| Sem exceção de usuário sem vínculo | RN-002 restringe acesso ao aluno | E3: bloquear a operação |
| Sem exceção de presença já cancelada | Pré-condição exige presença confirmada | E4: informar o status atual |
| Falha na notificação / sem motorista ativo não tratadas | RN-007 e RN-012 | E5: manter cancelamento e deixar notificação pendente |
| Regras incompletas | Faltam RN-002, RN-005, RN-012, RN-015 | Acrescentar e indicar em qual passo cada uma atua |
| Requisitos incompletos | Faltam RF-006, RF-008, RNF-003 | Acrescentar |

---

## Ex. 13 — Teste das perguntas (UC-S04)

| Passo do fluxo | Pergunta que revelou algo ausente |
| --- | --- |
| 1. Usuário seleciona aluno e trajeto | **E se não der certo?** usuário sem vínculo. **Qual regra?** RN-002 não citada. |
| 2. Sistema verifica embarque | **E se não der certo?** falha de rede. |
| 3–4. Pede e recebe confirmação | **E se não der certo?** usuário desiste; presença cancelada por outro responsável. |
| 5. Altera para ausente e registra | **Qual regra?** RN-005 não citada. **E se não der certo?** falha ao gravar. |
| 6. Notifica o motorista (UC-I01) | **E se não der certo?** falha no push; sem motorista ativo (RN-012). |
| 7. Exibe "Presença cancelada" | **O que acontece depois?** não diz se pode confirmar de novo. |

As perguntas que mais revelaram lacunas foram **E SE NÃO DER CERTO?**, **QUAL REGRA SE APLICA?** e **O QUE ACONTECE DEPOIS?**. QUEM, O QUÊ e SOBRE O QUÊ já estavam claros.

---

## Ex. 14 — Cadeia da RN07

```text
RN07  Descontos acima de 15% devem ser autorizados pelo gerente.
 ↓
RF    O sistema deve exigir autorização do gerente para desconto acima de 15%
      e só aplicá-lo depois de autorizado.
 ↓
UC    Registrar Venda (Vendedor; ator secundário: Gerente)
 ↓
Fluxo alternativo — Desconto acima de 15%
      1. Vendedor informa desconto acima de 15%.
      2. Sistema identifica que excede 15% (RN07).
      3. Sistema solicita autorização ao gerente.
      4. Gerente autoriza.
      5. Sistema registra a autorização.
      6. Sistema aplica o desconto e recalcula o total.
      (Exceção: gerente nega → desconto não é aplicado.)
 ↓
Critério de aceitação
      Desconto acima de 15% só é aplicado com autorização do gerente;
      até 15% é aplicado sem autorização.
 ↓
Cenário de teste
      Desconto de 20% sem autorização → não aplica.
      Desconto de 20% autorizado pelo gerente → aplica e recalcula.
      Desconto de 15% → aplica sem pedir autorização.
```

---

## Ex. 15 — Matriz de rastreabilidade (VanTrack)

| Regra | Requisito | Caso de Uso | Fluxo/Cenário |
| --- | --- | --- | --- |
| RN-001 | RF-001, RF-013 | UC-S01 | passo 3; E1 código inválido; E2 expirado |
| RN-002 | RF-001, 002, 003, 011, 012, 013 | UC-S01, UC-S02 | UC-S02 passo 2 e E2 |
| RN-003 | RF-002, RF-003 | UC-S02, UC-S10 | pré-condição do UC-S02 |
| RN-004 | RF-004, RF-007 | UC-S03 | passo 3; E1 prazo encerrado |
| RN-005 | RF-004, 007, 008 | UC-S09, UC-X02 | geração de rota; sem alunos |
| RN-006 | RF-005, 006, 008 | UC-S04 | passo 2; E1 embarque já registrado |
| RN-007 | RF-006 | UC-I01 | notificação ao motorista |
| RN-008 | RF-008 | UC-X02 | extensão do UC-S04 |
| RN-009 | RF-001, RF-011 | UC-B02, UC-S01 | vínculo aluno–responsável |
| RN-010 | RF-009, 010, 011 | UC-S05, UC-S06 | passo 2 de cada UC |
| RN-011 | RF-010 | UC-S06 | passo 3; E1 sem embarque |
| RN-012 | RF-002, 007, 012, 014 | UC-ADM01, UC-I01 | UC-I01 passo 2 |
| RN-013 | RF-013 | UC-S08 | vínculo no chat |
| RN-014 | RF-012, RF-015 | UC-S07, UC-S11, UC-I03 | aviso a todos os responsáveis |
| RN-015 | RF-002, 003, 009, 010, 012, 013 | UC-S02, UC-S05, UC-S06 | UC-S02 E1; S05/S06 A1 (offline) |
| RN-016 | RF-016 | UC-C01, UC-X01 | UC-X01 (extend do UC-C01) |

- **Regras sem requisitos?** Não, todas têm ao menos um RF.
- **Requisitos sem Casos de Uso?** Não, todo RF aparece em algum UC (o RNF-004 vale para todos).
- **Comportamento sem regra/requisito?** UC-A01 (Manter Cadastro Base) não tem RF; é um caso abstrato, e os UCs de negócio (B01, B02) agrupam vários requisitos.
- **Isso é erro?** Não necessariamente. É legítimo quando é abstrato, de negócio ou decisão técnica sem efeito visível. É erro quando um comportamento visível ao usuário não tem regra ou requisito.
