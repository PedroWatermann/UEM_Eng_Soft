SomaRecursiva(L);
    se L = nill
    então | SomaRecursiva <- 0;
    senão | SomaRecursiva <- L^.info + SomaRecursiva(L^.prox);
