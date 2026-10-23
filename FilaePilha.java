// PILHA FLEXÍVEL (LIFO - Sem Sentinela)
public class Pilha<E> {
    private Celula<E> topo; // Aponta diretamente para o último inserido

    public Pilha() { topo = null; }
    public boolean vazia() { return topo == null; }

    // Empilhar no topo: O(1)
    public void empilhar(E item) {
        Celula<E> tmp = new Celula<E>(item);
        tmp.setProximo(topo); // Novo nó aponta para o antigo topo
        topo = tmp;           // Topo passa a ser o novo nó
    }

    // Desempilhar do topo: O(1)
    public E desempilhar() throws Exception {
        if (vazia()) throw new Exception("Pilha Vazia!");
        E item = topo.getItem();
        topo = topo.getProximo(); // Avança o topo para o nó de baixo
        return item;
    }
}

// FILA FLEXÍVEL (FIFO - Com Célula Sentinela)
public class Fila<E> {
    private Celula<E> primeiro; // Aponta para a Sentinela
    private Celula<E> ultimo;   // Aponta para o fim da fila

    public Fila() {
        primeiro = new Celula<E>(); // Célula Sentinela
        ultimo = primeiro;
    }
    public boolean vazia() { return primeiro == ultimo; }

    // Enfileirar no fim: O(1)
    public void enfileirar(E item) {
        ultimo.setProximo(new Celula<E>(item));
        ultimo = ultimo.getProximo();
    }

    // Desenfileirar do início (após a sentinela): O(1)
    public E desenfileirar() throws Exception {
        if (vazia()) throw new Exception("Fila Vazia!");
        Celula<E> tmp = primeiro.getProximo();
        primeiro.setProximo(tmp.getProximo()); // Sentinela pula o removido
        E item = tmp.getItem();
        if (tmp == ultimo) ultimo = primeiro; // Se esvaziou a fila
        return item;
    }
}
