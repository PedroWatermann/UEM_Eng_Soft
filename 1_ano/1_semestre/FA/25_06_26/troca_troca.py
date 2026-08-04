def troca_troca(msg: str) -> str:
    """ 
    Codifica a mensagem *msg* de forma a interverter os caracteres de 2 em 2. Caso a quantidade de caracteres for ímpar, será adicionado '#' ao final da mensagem.
    Exemplos:
    >>> troca_troca('Esta é uma mensagem.')
    'sEaté u amm neaseg.m'
    >>> troca_troca('Quantidade ímpar.')
    'uQnaitadedí pmra#.'
    >>> troca_troca('Quantidade par.')
    'uQnaitadedp ra#.'
    """
    
    qtd_caracter: int = len(msg)
    msg_final: str = ''
    count: int = 1
    
    if qtd_caracter % 2 != 0:
        msg += '#'
        qtd_caracter = len(msg)
        
    while count < qtd_caracter:
        msg_final += msg[count] + msg[count - 1]
        
        count += 2
        
    return msg_final