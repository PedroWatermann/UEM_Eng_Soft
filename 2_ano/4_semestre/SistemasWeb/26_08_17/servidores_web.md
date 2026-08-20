# Servidores Web

## HTTP

* O uso do HTTP em requisições e respostas acontece a partir de métodos
* Os métodos repersentam operações que foram implementadas pelo protocolo
* No caso do HTTP, os seguintes métodos podem ser apresentados:
  * **GET, POST, PUT, DELETE**, Trace e outros
  * Os métodos destacados acima são suficientes para compor a arquitetura REST
* Para entender as diferenças entre os métodos http é importante considerar as seguintes características
  * Idempotência: modificações realizadas no lado do servidor
  * Segurança: métodos que não provocam alterações nos dados

## Parâmetros

* Os parâmetros representam valores que podem ser considerados em uma requisição
* Nesse contexto, existem duas possibilidades principais:
  * Query parameter: parâmetro é passado como parte da URL
  * Body parameter: parâmetro é passado como parte do "corpo" da mensagem da requisição
* Observação: as possibilidades query e body estão associadas com os métodos GET e POST, respectivamente

### GET

* Utilizado para recuperar informações (representações de recurso)

### POST

* Utilizado para envio de informações sigilosas

## Comunicação

* Cliente realiza uma **requisição** HTTP ao servidor e este devolve uma **resposta** HTTP ao cliente

* Observação: neste contexto, comunicação e interação podem ser entendidas como sinônimos

* A literatura descreve dois tipos de comunicação: **síncrona** e **assíncrona**

### Comunicação Síncrona

* Necessidade de ordem de envio
* As chamadas são executadas na ordem de envio

### Comunicação Assíncrona

* Envios fora de ordem podem acontecer
