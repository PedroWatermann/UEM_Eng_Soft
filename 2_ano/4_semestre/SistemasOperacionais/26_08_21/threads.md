# Threads

* São processos leves

* Quando o SO vai executar um processo ele realiza alguns passos:

1. Aloca memória
2. Todo processo tem pelo menos uma thread (uma thread é um fluxo de execução)
  2.1. Podemos criar n threads para cada processo

* São chamados de processos leves pois ao criar uma thread o SO não aloca tanto recurso quando necessário para um processo

  ## Comunicação

  * Todas as threads que fazem parte do processo com partilham da mesma memória alocada
  * Essa comunicação é chamada de L/E na memória, ou seja, caso dois processos precisem se comunicar eles se comunicam lendo e escrevendo as mensagens na memória

  ## P/C -> Produtor, Consumidor

  * Dois processos distintos que produzem e consomem informações, nessa ordem, e se comunicam através de um buffer de memória

  ### Criação/Alocação

  * Cria um novo processo a partir do fork()
  * Cada processo tem seu BCP para gerenciar os recursos do processo
  * O p2 inicia sempre na instrução seguinte ao fork(), que geralmente é um if que dá função para cada processo através da verificação do seu PID
  <!-- Criação de threads -->
  * O linux possui uma biblioteca para gerenciar threads: pThreads
  * pthreads.create(f) cria uma nova thread, separando o processo em dois fluxos:
    * Um fluxo é a continuação do programa
    * O outro, que é a nova thread, executará a função que está sendo passada em sua criação
  * No problema do P/C, podemos ter as funções p() e c() e criar duas threads passando cada função para sua thread. Dessa forma, teremos 3 fluxos (threads): o do programa principal, o que executará a função p() e o que executará a função c()
    * Nesse caso poderíamos apenas criar uma thread e chamar a função c() para que seja executada no fluxo padrão que também funcionaria
  <!-- Criação de P/C por thread ou por processos -->
  * Caso cada um P/C seja criado com threads, a memória entre eles é compartilhada automaticamente. Porém, caso sejam criados como processos separados, essa memória não é compartilhada automaticamente. Para isso, temos a diretiva shmem que 'habilita' esse compartilhamento
  * Regras do P/C
    * O produtor só pode produzir se houver espaço livre no buffer. Então, quando o bufffer estiver cheio P é bloqueado
    * C apenas pode consumir se houver coisa para ser consumida
    * O acesso ao buffer é exclusivo

  ### Finalização

  * Temos a função pthread.join(), cuja função é aguardar todas as threads terminarem para finalizar o processo pai
  * Por exemplo, no exemplo de criar uma thread para cada um P/C, após criar as duas threads, temos a chamada da função join para aguardar elas terminarem

  ### Sincronização

  * ...

    #### Lock

    * É como uma chave, onde para que outro processo utilize o recurso precisa esperar a liberação da chave
    * Fluxo:
      1. Pega o lock
      2. Acessa o buffer
      3. Devolve o lock

    #### Semáforo
