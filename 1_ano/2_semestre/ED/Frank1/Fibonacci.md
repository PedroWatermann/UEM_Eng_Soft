Fibonacci(n);
    a = 0;
    b = 1;
    para i = 1 até n + 1
    faça | escreva(a);
         | c = b;
         | b = a + b;
         | a = b;

Fibonacci(n);
    se n <= 1
    então | Fibonacci <- 1;
    senão | Fibonacci <- Fibonacci(n - 1) + Fibonacci(n - 2);