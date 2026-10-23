public class Celula<T> {
    private T item;           // Armazena o valor do nó (Generics: flexível para qualquer tipo)
    private Celula<T> proximo;// Endereço/ponteiro para a próxima célula na memória

    // Construtor padrão (cria célula vazia)
    public Celula() {
        this(null);
    }

    // Construtor principal
    public Celula(T item) {
        this.item = item;
        this.proximo = null; // Toda nova célula nasce apontando para null
    }

    // Getters e Setters para manipular os atributos privados
    public T getItem() { return item; }
    public void setItem(T item) { this.item = item; }
    public Celula<T> getProximo() { return proximo; }
    public void setProximo(Celula<T> proximo) { this.proximo = proximo; }
}
