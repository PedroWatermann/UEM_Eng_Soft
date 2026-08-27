# Processos e Threads

## Criação de Processos

* A diretiva wait() faz com que o processo filho aguarde o pai terminar para que este seja finalizado

## Comunicação

* Memória compartilhada
  * Threads compartilham memória nativamente; para processos ela precisa ser alocada manualmente com shmem(tam_bytes)
* Pipe (|)
  * Forma de realizar a comunicação entre dois processos, onde a saída de um é a entrada do outro
  * Mais utilizado na linha de comando
  * A execução não ocorre em paralelo
  * Exemplo: ls | grep teste | x | y | z
* Passagem de mensagem
  * É aberto um canal entre dois processos e um envia uma mensagem ao outro para a troca de informações

## Produtor/Consumidor (continuação...)

### Lock

```C
#include <stdio.h>

pthread_mutex_t l;
int cont = 0;

int main() {
  pthread_mutex_lock(l);

  cont++; //R.C.
  
  pthread_mutex_unlock(l);
  
  return 0;
}
```

* R.C.: Região crítica: todo código que, se acessado simultânemaente por mais de um fluxo de execução, pode causar erro
* Na linha do incremento do contador temos três instruções:
  * leia(cont)
  * incremente(cont)
  * escreva(cont)
* O motivo pelo qual pode gerar erro se acessado simultanemanete, é porque o SO escalona a execução dos processos/threads, então há a chance de não terem sido executados os três comandos antes de outro processo acessá-lo

* Exemplo do código do produtor:

```C
int contador = 0;
var buffer;
int in;
pthread_mutex_t mutex;
pthread_cond_t condp; //sinal que o consumidor envia quando retira algo do buffer
pthread_cond_t condc; //sinal que o produtor envia quando retira algo do buffer

// * -> ponteiro
void * p (void * a | {
  while true {
    pthread_mutex_lock(mutex); //mutex realiza a exclusão mútua, j´q eu o buffer nao pode ser acessado simultaneamente

    while(contador == TB) //TB -> tamanho do buffer
      pthread_cond_wait(condp, mutex);

    buffer[in] = ?;
    in = (in + 1) % TB; //é um buffer circular
    
    contador++;
    
    pthread_cond_signal(condc);
    pthread_mutex_unlock(mutex);
  }
})
```
