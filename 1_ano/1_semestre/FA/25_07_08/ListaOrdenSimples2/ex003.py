def merge_sort(lst1: list[int], lst2: list[int]):
    """
    >>> b1 = [2, 4, 6, 8]
    >>> b2 = [1, 3, 5, 7, 9]
    >>> merge_sort(b1, b2)
    [1, 2, 3, 4, 5, 6, 7, 8, 9]
    """
    
    result: list[int] = []
    i: int = 0
    j: int = 0
    
    while i < len(lst1) and j < len(lst2):
        if lst1[i] < lst2[j]:
            result.append(lst1[i])
            i += 1
        else:
            result.append(lst2[j])
            j += 1
    
    result.extend(lst1[i:])
    result.extend(lst2[j:])
    
    return result