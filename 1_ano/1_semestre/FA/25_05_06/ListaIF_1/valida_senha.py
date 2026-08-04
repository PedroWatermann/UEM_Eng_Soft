def valida_senha(senha: str) -> bool:
    '''Verifica se a senha informada está correta, ou seja, verifica se o valor informado corresponde à palavra "senha"
    >>> valida_senha("senha")
    True
    >>> valida_senha("outraSenha")
    False'''

    return True if senha == "senha" else False