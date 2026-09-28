Complexidade dos algoritmos de ordenação

Bubble Sort

- Melhor caso: O(n)
- Caso médio: O(n²)
- Pior caso: O(n²)
- Espaço: O(1)

Compara elementos vizinhos e troca quando estão fora de ordem.

Selection Sort

- Melhor caso: O(n²)
- Caso médio: O(n²)
- Pior caso: O(n²)
- Espaço: O(1)

Procura o menor elemento e coloca na posição correta.

Insertion Sort

- Melhor caso: O(n)
- Caso médio: O(n²)
- Pior caso: O(n²)
- Espaço: O(1)

Insere cada elemento na posição correta da parte já ordenada.

Merge Sort

- Melhor caso: O(n log n)
- Caso médio: O(n log n)
- Pior caso: O(n log n)
- Espaço: O(n)

Divide o vetor em partes menores e depois intercala as partes ordenadas.

Quick Sort

- Melhor caso: O(n log n)
- Caso médio: O(n log n)
- Pior caso: O(n²)
- Espaço: O(log n) em média

Escolhe um pivô e divide os elementos entre menores e maiores que ele.

Heap Sort

- Melhor caso: O(n log n)
- Caso médio: O(n log n)
- Pior caso: O(n log n)
- Espaço: O(1)

Constrói uma Heap e remove repetidamente o maior ou menor elemento.

---

Bubble → O(n²)

Selection → O(n²)

Insertion → O(n²)

Merge → O(n log n)

Quick → O(n log n) médio / O(n²) pior

Heap → O(n log n) em todos os casos

---

Ideia de cada um

Bubble → compara vizinhos

Selection → procura o menor

Insertion → insere na posição

Merge → divide e intercala

Quick → pivô + partição

Heap → Heap + remoção do topo