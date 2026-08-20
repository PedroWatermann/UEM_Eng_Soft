# Contextualização

## Sistema Computacional

* Parte lógica, física e usuários
* Software, hardware, dados, entrada, processamento e saída

## Sistema web

* Sistema computacional que funciona em um ambiente específico (web)
* Para que um sistema computacional seha descrito como um sistema web, é preciso entender características desse tipo de sistema
* Entre as principais delas est;ao funcionamento distribuído e tecnologia cliente-servidor

## Funcionamento Distribuído

* Nesse contexto, diferentes elementos se comunicam (envio e recebimento de dados)
* A execução pode acontecer de modo concorrente, utilizando diferentes estruturas de rede
* Comunicações entre processos são observados
* Dados do sistema podem estar distantes geograficamente

* ### Ilustração (todos estão interligados)

| - | - | - | - |
| - | - | - | - |
| E1 | <- | -> | E2 |
| ^- | -> | E3 | <^ |
| \| | E4 | <- | -v |
| - | -^- | -> | E5 |

* Obs: considerando o funcionamento distribuído, pode-se dizer que um sistema web representa um variação de um sistema distribuído
* Definição de sistema distribuído: hardware, software e pessoas que podem ou não estar no mesmo ambiente geográfico

## Tecnologia Cliente-Servidor

* Durante a definição de um sistema web, a nível de estrutura, estabeleceu-se trÊs elementosprincipais, a saber: cliente, servidor e rede
  * O cliente pode ser definido como uma interface de comunicação
  * O servidor pode ser definido como um elemento responsável pelo gerenciamento das operações
  * A rede pode ser definida como o meio de comunicação

* ### Ilustração

| Cliente | ---- | Servidor |
| ------- | ---- | -------- |
| \|      |      | \|       |
| ------> | Rede | <------- |

* Cliente: Apresentação e interação
* Servidor: Processamentos e retorno de resulatdos
* Rede: canal de comunicação entre clientes e servidores
* Obs: uma vez que se conhece a finalidade de cada elemento (cliente, servidor e rede), é importante compreender detalhes de funcionamento
