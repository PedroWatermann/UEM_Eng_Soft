Pesquisa(V, n, x);
    se n > 0;
    então | se V[n] = x
          | então | Pesquisa <- 1;
          | senão | Pesquisa <- Pesquisa(V, n - 1, x);
    senão | Pesquisa <- 0;
