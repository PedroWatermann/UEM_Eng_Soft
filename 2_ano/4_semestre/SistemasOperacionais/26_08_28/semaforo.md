# Semáforo

* O semáforo já possui um if interno que bloqueia o decremento caso seja igual a 0

```C
sem_t sem;

int main() {
  sem_wait(sem); // sem = sem - 1 -> se sem > 0 // se sem == 0 -> bloqueado
  sem_signal(sem); // s++; se tiver T/P bloqueado, acorde

  return 0;
}
```

* Um semáforo deve ser inicializado com um valor. Se for inicializado com 0 ou 1, é chamado de semáforo binário
* Exercício: faça o código do produtor e do consumidor com semáforo

```C
sem_t s_mutuo = 1;
sem_t s_consu = 1;
sem_t s_produ = 1;
int contador = 0;
var buffer;
int in;

void * produtor (void * a | {
  while true {
    sem_wait(sem_mutuo);
    sem_wait(sem_consumidor);

    if (contador == TB)
      sem_wait(sem_produtor);

    buffer[in] = ?;

    in = (in + 1) % TB;
    contador++;

    sem_signal(sem_consumidor);
    sem_signal(sem_mutuo);
  }
})

void * consumidor (void * a | {
  while true {
    sem_wait(sem_mutuo);
    sem_wait(sem_produtor);

    if (contador == 0)
      sem_wait(sem_consumidor);

    var conteudo = buffer[in];
    
    in = (in - 1) % TB;
    contador--;

    sem_signal(sem_produtor);
    sem_signal(sem_mutuo);
  }
})
```

* Código do professor:

```C
int in;
var buffer;
int contador = 0;

sem_t s_mutuo = 1; //s3
sem_t s_consumidor = 0; //s2
sem_t s_produdor = TB; //s1

void * produtor (void * a | {
  while true {
    sem_wait(s_produtor);
    sem_wait(sem_mutuo);

    buffer[in] = ?;

    in = (in + 1) % TB;
    contador++;

    sem_signal(sem_mutuo);
    sem_signal(sem_consumidor);
  }
})

void * consumidor (void * a | {
  while true {
    sem_wait(s_consumidor);
    sem_wait(sem_mutuo);

    var conteudo = buffer[in];
    
    in = (in - 1) % TB;
    contador--;

    sem_signal(sem_mutuo);
    sem_signal(sem_produtor);
  }
})
```

* Podemos ter *n* processos/threads, porém a quantidade máxima de produtores e consumidores **simultâneos** é igual à quantidade de posições no buffer
  * Simultâneo significa que vai conseguir ler a primeira instrução, já que a segunda, de exclusão mútua, é binária, então só pode haver um acesso simultâneo
