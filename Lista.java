public class Lista<E> {
    private Celula<E> primeiro; // Aponta SEMPRE para a Célula Sentinela (início)
    private Celula<E> ultimo;   // Aponta para a última célula real com dado
    private int tamanho;        // Mantém o controle de quantos elementos existem na lista

    // Construtor: Aloca a Célula Sentinela ("fantasma")
    public Lista() {
        primeiro = new Celula<E>(); // Sentinela (não guarda dado útil)
        ultimo = primeiro;          // Quando vazia, primeiro e último apontam para a sentinela
        tamanho = 0;
    }

    // A lista está vazia quando o ponteiro 'primeiro' é igual ao 'ultimo'
    public boolean vazia() {
        return primeiro == ultimo;
    }

    // --- INSERÇÃO ---

    // Insere logo após a Célula Sentinela (no começo dos elementos reais)
    public void inserirInicio(E item) {
        Celula<E> tmp = new Celula<E>(item);
        tmp.setProximo(primeiro.getProximo()); // 1º: Nova célula aponta para o antigo 1º elemento
        primeiro.setProximo(tmp);               // 2º: Sentinela aponta para a nova célula
        
        // Caso especial: se a lista estava vazia, o 'ultimo' também deve apontar para o novo nó
        if (primeiro == ultimo) {
            ultimo = tmp;
        }
        tamanho++;
    }

    // Insere no final da lista usando o ponteiro 'ultimo': O(1)
    public void inserirFim(E item) {
        ultimo.setProximo(new Celula<E>(item)); // Conecta o novo nó após o último
        ultimo = ultimo.getProximo();          // Atualiza o ponteiro 'ultimo' para o nó recém-criado
        tamanho++;
    }

    // Insere em qualquer posição k (entre 0 e tamanho)
    public void inserir(E item, int pos) throws Exception {
        if (pos < 0 || pos > tamanho) throw new Exception("Erro ao inserir: Posição inválida!");
        if (pos == 0) { inserirInicio(item); return; }
        if (pos == tamanho) { inserirFim(item); return; }

        // Caminha até o nó anterior à posição desejada (pos - 1)
        Celula<E> p = primeiro;
        for (int i = 0; i < pos; i++) p = p.getProximo();

        // Encadeia a nova célula no meio da cadeia
        Celula<E> tmp = new Celula<E>(item);
        tmp.setProximo(p.getProximo()); // Novo nó aponta para o nó da frente
        p.setProximo(tmp);               // Nó anterior aponta para o novo nó
        tamanho++;
    }

    // --- REMOÇÃO ---

    // Remove o primeiro elemento real (logo após a sentinela): O(1)
    public E removerInicio() throws Exception {
        if (vazia()) throw new Exception("Erro ao remover: Lista vazia!");
        
        Celula<E> tmp = primeiro.getProximo();  // 'tmp' guarda o nó que será removido
        primeiro.setProximo(tmp.getProximo()); // Sentinela pula o nó removido e aponta para o próximo
        E item = tmp.getItem();               // Salva o valor para retornar
        
        // Se removeu o único elemento existente, o 'ultimo' volta para a sentinela
        if (tmp == ultimo) ultimo = primeiro;
        
        tmp.setProximo(null); // Desconecta o nó removido da memória
        tamanho--;
        return item;
    }

    // Remove do final: Obriga a percorrer até o penúltimo nó — O(n)
    public E removerFim() throws Exception {
        if (vazia()) throw new Exception("Erro ao remover: Lista vazia!");
        
        // Caminha até achar o penúltimo elemento (cujo próximo é o 'ultimo')
        Celula<E> p = primeiro;
        while (p.getProximo() != ultimo) p = p.getProximo();

        E item = ultimo.getItem();
        ultimo = p;             // O penúltimo passa a ser o novo 'ultimo'
        ultimo.setProximo(null);// Desconecta o elemento removido
        tamanho--;
        return item;
    }

    // --- MÉTODOS RECURSIVOS ---

    // Método público (interface simples para o usuário)
    public boolean contem(E item) {
        return contem(item, primeiro.getProximo()); // Inicia a busca após a sentinela
    }

    // Método privado/recursivo (navega nó a nó)
    private boolean contem(E item, Celula<E> aux) {
        if (aux == null) return false;                 // Caso base 1: Fim da lista (não achou)
        if (aux.getItem().equals(item)) return true;   // Caso base 2: Achou o item
        return contem(item, aux.getProximo());         // Passo recursivo: vai para o próximo nó
    }

    public int contarQtos(E item) {
        return contarQtos(item, primeiro.getProximo());
    }

    private int contarQtos(E item, Celula<E> aux) {
        if (aux == null) return 0; // Caso base: chegou ao fim
        
        // Se o nó atual for igual ao item, soma 1, senão soma 0
        int cont = aux.getItem().equals(item) ? 1 : 0;
        
        // Soma o resultado atual com o retorno dos próximos nós
        return cont + contarQtos(item, aux.getProximo());
    }

    // --- OPERAÇÕES ESPECIAIS ---

    // Inverte os ponteiros da própria lista sem criar novas células
    public void inverter() {
        if (vazia() || primeiro.getProximo().getProximo() == null) return; // 0 ou 1 elemento

        Celula<E> ant = null;
        Celula<E> atual = primeiro.getProximo();
        Celula<E> prox = null;
        
        this.ultimo = atual; // O 1º nó real se tornará o último nó após a inversão

        // Inverte o sentido das setas (ponteiros) nó a nó
        while (atual != null) {
            prox = atual.getProximo(); // 1. Salva o próximo
            atual.setProximo(ant);     // 2. Inverte o ponteiro para trás
            ant = atual;               // 3. Avança o ponteiro 'ant'
            atual = prox;              // 4. Avança o ponteiro 'atual'
        }
        
        primeiro.setProximo(ant); // A sentinela passa a apontar para o novo primeiro nó
    }

    // Cria uma cópia independente (deep copy) da lista
    public Lista<E> clone() {
        Lista<E> copia = new Lista<E>();
        for (Celula<E> i = primeiro.getProximo(); i != null; i = i.getProximo()) {
            copia.inserirFim(i.getItem());
        }
        return copia;
    }
}
