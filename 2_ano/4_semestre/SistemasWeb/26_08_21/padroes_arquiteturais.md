# Padrões Arquiteturais

* Revisão: cliente (ativa, interface, processo, navegador); servidor (reativa, processo); requisição e resposta - http (get, post, put, delete); processos são programas em execução

  ## Definições

  * Um padrão pode ser descrito como um modelo de organização que auxilia no desenvolvimento de uma solução
  
    ### Modelo de organização

    * Descrevendo os subsistemas, suas responsabilidades e relacionamentos
  
  * A literatura apresenta diferentes grupos de padrões, tais como padrões de projeto e padrões arquiteturais
  * No caso dos padrões arquiteturais, existem padrões que estão entre os principais, destaque para: camadas, MVC e REST

  ## Camadas

  * Este padrão organiza os elementos do software (classes, interface e outros) em unidades conhecidas como camadas
  * O padrão o considera o seguinte funcionamento:
    * C1 <--> C2 <--> C3
  * Observação: um número variável de camadas pode ser considerado. Na disciplina, será considerado um modelo de três camadas

  ## Modelo de 3 Camadas
  
  * Nesse modelo, as camadas podem ser organizadas em:
    * Apresentação
      * Interação com o usuário
    * Regra de negócio
      * Processamento dos dados
    * Acesso a dados
      * Consulta e armazenamento dos dados (persistência)
  * Facilita o reúso de código, manutenção e evolução do software

  ## MVC

  * Este modelo organiza o software a partir de três elementos principais:
    * Modelo
      * Associado com persistência
    * Visão
      * Interação
    * Controller
      * Processamento
  * O funcionamento é parecido com o de camadas: V <--> C <--> M
    * Podem haver variações no MVC onde a camada de visão obtém dados diretamente da camada de modelo, mas para entendimento da disciplina não obterá
