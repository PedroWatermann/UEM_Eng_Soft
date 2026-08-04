from enum import Enum, auto

class Candidato(Enum):
    CANDIDATO_1 = 1
    CANDIDATO_2 = 2
    VOTO_BRANCO = 3
    NOVAS_ELEICOES = auto()

def verifica_eleicao(votos: list[int]) -> Candidato:
    """
    Calcula qual o vencedor da eleição com base na contagem da lista de *votos* fornecida.
    >>> verifica_eleicao([1, 3, 2, 2, 1, 1, 3, 3, 2, 1, 3]).name
    'NOVAS_ELEICOES'
    >>> verifica_eleicao([1, 3, 2, 2, 3, 3, 1, 1, 2, 2, 2]).name
    'CANDIDATO_2'
    >>> verifica_eleicao([1, 3, 2, 3, 2, 1, 3, 3, 3, 1, 3]).name
    'NOVAS_ELEICOES'
    >>> verifica_eleicao([2, 2, 3, 1, 1, 1, 3, 1, 1, 2, 1]).name
    'CANDIDATO_1'
    """
    
    candidato_1: int = calcula_votos(votos, Candidato.CANDIDATO_1)
    candidato_2: int = calcula_votos(votos, Candidato.CANDIDATO_2)
    voto_branco: int = calcula_votos(votos, Candidato.VOTO_BRANCO)
    
    if voto_branco > len(votos) / 2:
        return Candidato.NOVAS_ELEICOES
    else:
        if candidato_1 > candidato_2:
            if candidato_1 > voto_branco:
                return Candidato.CANDIDATO_1
            else:
                return Candidato.NOVAS_ELEICOES
        elif candidato_2 > candidato_1:
            if candidato_2 > voto_branco:
                return Candidato.CANDIDATO_2
            else:
                return Candidato.NOVAS_ELEICOES
        else:
            return Candidato.NOVAS_ELEICOES
            
    
def calcula_votos(votos: list[int], candidato: Candidato) -> int:
    """
    Calcula a quantidade de *votos* de um *candidato* especificado.
    >>> calcula_votos([1, 3, 2, 2, 1, 1, 3, 3, 2, 1, 3], Candidato.CANDIDATO_2)
    3
    >>> calcula_votos([1, 3, 2, 2, 3, 3, 1, 1, 2, 2, 2], Candidato.VOTO_BRANCO)
    3
    >>> calcula_votos([2, 2, 3, 1, 1, 1, 3, 1, 1, 2, 1], Candidato.CANDIDATO_1)
    6
    """
    
    contador: int = 0
    for voto in votos:
        if voto == candidato.value:
            contador += 1
    
    return contador