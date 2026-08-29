# Ambiente Web

## Hipertexto e Hipermídia

* Hipertexto/hipermídia pode ser definido como um documento que conecta elementos
  * Elementos: recursos/conjuntos de dados
* Os elementos podem ser entendidos como recursos que possuem uma ERL
* Os elementos estabelecem conexões a partir de links
* Hipertexto pode ser definido como um documento que possui somente elementos de tipo texto
* Hipermídia, por sua vez, pode ser definida como um documento que possui diferentes tipos de recursos (áudio, vídeo, imagem, texto, número e combinações variadas)
* Obs: quando um hipertexto/hipermídia é armazenado em um servidor web, tem-se uma página web

## Navegador

* Pode ser entendido como o programa que possibilita a interação de um cliente (usuário) com um ou mais servidores, bem como com outros clientes
* Em termos de estrutura, um navegador possui o seguinte funcionamento:
  * *imagem*
* Uma vez que hipertexto/hipermídia e navegador foram compreendidos, é importante detalhar possíveis características de um documento (página web)

## Documentos

* Nos dias atuais existem os seguintes tipos de documento:
  * Estático:
    * É armazenado no servidor
    * A cada requisição, uma cópia é gerada como resposta
  * Dinâmico:
    * É criado no servidor, após a requisição
    * O documento criado é retornado como resposta, podendo ou não ser armazenado
* Obs: o tipo de documento é utilizado para classificar o tipo de página
* Obs: foi no contexto de hipertexto/hipermídia, navegador e documento que surgiu o HTML

## HTML

* Significa Hipertext MArcup Language
* Possibilita a construção de páginas, com a possibilidade de combinar diferentes recursos
* Entre as principais caractesísticas da linguagem está o uso de marcadores, ou tags, como `<html></html>`
* Uma página HTML possui a seguinte estrutura:

```HTML
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    
</body>
</html>
```

* O conjunto de tags do HTML é reconhecido pelo interpretador HTML no navegador
* Obs: a literatura apresenta outras linguagens de marcação além do HTML, como o XML

## XML

* Significa eXtensible Markup Language
* Pode ser utilizado para envio e recebimento de dados
* Pode se criar tags com pastante facilidade
