SomaRecursiva(V, n)
    se n = 0
    entao | SomaRecursiva <- 0;
    senao | SomaRecursiva <- V[n - 1] + SomaRecursiva(V, n - 1);