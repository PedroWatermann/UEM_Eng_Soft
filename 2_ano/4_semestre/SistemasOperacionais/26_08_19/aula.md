# Processos e Threads

## Estrutura

* Em cima do hardware há vários processos rodando, alguns deles compõem o sistema operacional, e os outros pertencem ao usuário
* Um programa compilado para um SO não consegue ser executado em outro SO sem a ajuda de um software mediador (WSL no Windows e Wine no Linux)

## Máquina de Estado

* Escalonamento:
  * O sistema operacional pega um processo pronto (**P**) e o executa (**E**), passando pela CPU durante um período de tempo (quantum: x unidades de tempo). Quando este tempo acaba, o SO devolve este processo para pronto e pega outro para ser executado
  * Há também a possibilidade de uma instrução esperar um tempo maior por alguma razão. Para isso, existe um outro estado bloqueado (**B**). Para que este estado ocorra, existem duas possibilidades:
    * Quando uma instrução precisa realizar uma leitura de arquivo, o processo fica bloqueado até que está instrução finalize, então a instrução é finalizada
    * Quando uma instrução necessita de um sinal para finalizar sua execução
  * Após um processo bloqueado ser liberado, ele sempre volta para pronto e então segue o fluxo novamente
  * Após todas as instruções do processo serem executadas, ele chega ao último estado terminado (**T**)

## Bloco de Controle de Processos (BCP)

* É necessário salvar alguns valores sobre os processos:
  * **ID** do processo
  * **Estado** atual do processo
  * **Contador de Programa**
    * Para guardar a instrução atual para que não seja perdida essa referência no fluxo de execução (máquina de estado)
  * **Registradores**
    * Exemplo: add %eax, %ebx | sub %ebx, %ecx, porém entre essas duas execuções acabou o tempo. Então, o processo é "guardado" para ser executado posteriormente. Porém, quando o mesmo é retomado para a instrução de subtração (que foi armazenada por PC), é necessário do valor do registrador ebx, que pode ter sido alterado por outro processo. Por isso precisamos guardar o valor dos registradores também
  * **Informações de memória de E/S**
* Ilustação da máquina de estado:
* P <---> E ---> T
* ^-- B --^
* Em P, podemos ter k processos. Em E, podemos ter x processos. Em B, podemos ter h processos
  * k é a quantidade de processos requeridos pelo usuário, somado com os processos do sistema
  * x depende do hardware (núcleos)
  * h depende da necessidade dos processos

## Algoritmos de Escalonamento

* Um algoritmo de escalonamento deve atender aos seguintes requisitos:
  * **Justiça**: todos os processos têm direito de serem executados, não necessáriamente a mesma chance de ser escolhido
  * **Eficiência**: maximixar o uso da CPU
  * **Tmepo de resposta**: sistemas que têm uma interação com o usuário devem ser rápidos
  * **Vazão**: visa aumentar a quantidade de processos que estão finalizando
  * **Tempo de espera**: deve ser o menor possível para que o algorítimo espere para ser executado
* Exemplos de algoritmos:
  * PCPS (primeiro a chegar é o primeiro a ser servido): lógica de uma fila
  * MP (menor primeiro): quando um programa é curto, é passado para ser executado primeiro
  * RR (round robin): é uma tecnica de balanceamento onde os processos vão enrtando em uma fila para que sejam executados nessa ordem. Ao ser executado, volta para o final da fila
  * Prioridades: fornecer prioridade aos processos, divididos em dois grupos: processos que pertencem ao usuário (que têm maior prioridade) e processos que pertencem ao SO
* Também podemos criar filas de prioridades
  * Há processos CPU bound, que são os processos que ficam a maior parte do tempo na CPU e processos memory bound, que necessitam de muitos acessos à memória. Logo, podemos definir as filas de acordo com estes tipos: E/S -> CPU -> Memory
  * Para gerenciar os processos de uma mesma fila, podemos apenas aplicar outro algoritmo para cada fila, por exemplo, AE prioridade para criar as filas -> AE PCPS para cada fila

## Criação de Processos

* Quando executamos um programa um processo é criado. Porém, os programas podem criar outros porcessos durante sua execução. Para isso é utilizada a função fork()
* P1 -> BCP - P1
  * Em dado momento, P1 chama fork() criando P2 -> BCP - P2 que será a cópia do P1
  * Independentemente das instruções antes do fork em P1, quando P2 entrar em execução a instrução a ser executada será a próxima após o fork()
  * Útil para diminuir o tempo de resposta entre execuções do mesmo processo, como em multiplicação de matrizes
