def soma_naturais(n: int) -> int:
    if n == 0:
        return 0
    else:
        return n + soma_naturais(n - 1)

print(soma_naturais(5))
