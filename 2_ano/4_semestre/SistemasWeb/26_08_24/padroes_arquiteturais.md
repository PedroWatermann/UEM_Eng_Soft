# Padrões arquiteturais

* O modelo de organização (padrão) representa um ponto de partida
* Diferentes padrões podem ser combinados entre si
  * Exemplo: client-side construído em camadas e o server-side em MVC. Com isso, a camada de view do client requisita para a view do server e o dado é retornado e apresentado

  ## REST

  * Antes de entender os detalhes desse estilo, é preciso compreender algumas informações de contexto
    * Sistema Web -> Sistema Distribuído (exemplo)
    * Todos esses servicos juntos formam um único sistema web
    * Isso é eficiente devido sua escalabilidade e manutenibilidade
      * Exemplos:
        * Caso um serviço comece a ficar sobrecarregado é possível escalá-lo ou criar uma nova instância para funcionar em paralelo
        * Caso haja um serviço de validação cadastral que use outro sistema, apenas para ele será necessário realizar a integração
        * Caso seja necessário realizar a manutenção em algum servico, os outros serviços não precisam ser parados completamente
  * É baseada no protocolo HTTP
  * Um recurso é definido como um conjunto de dados que é trafegado pelo HTTP (tipo uma pasta)
  * O recurso é considerado um elemento muito importante na aplicação do estilo REST
    * Exemplo: meusite.com/**clientes**
  * A partir de métodos HTTP é possível planejar diferentes ações com os recursos
  * Os principais métodos HTTP a serem utilizados pelo estilo REST são:
    * GET: recuperação de recurso (deve existir)
    * POST: criação de um recurso
    * PUT: atualização de um recurso (se não existir, cria)
    * DELETE: exclusão de um recurso
  
    ### Considerações complementares

    * Quando se trabalha com REST, tem-se que os serviçoes podem ser considerados os elementos mais básicos
    * Para implementar um requisito ou funcionalidade, pode-se utilizar um ou mais serviçoes
      * Requisito/Funcionalidade --(exigir)--> um ou mais serviços (composição de serviços)

    ### Serviçoes REST

    * Estão relacionados com todas as ações que são necessárias para realizar uma tarefa (implementação de um requisito ou funcionalidade)

    ### Representações Distintas REST

    * Um mesmo recurso pode ser representado de maneiras distintas
    * Exemplo: descrições textuais, imagens

    ### Uso dos Status Code HTTP

    * É recomendável utilizar códigos de status HTTP que sejamm adequados à manipulação realizada com o recurso
    * Para isso, é preciso conhecer as várias possibilidades de código
