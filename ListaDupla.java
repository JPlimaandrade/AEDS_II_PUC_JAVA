// Lista Duplamente Encadeada Flexível com Célula Sentinela
public class ListaDupla<E> {
    private CelulaDupla<E> primeiro; // Aponta SEMPRE para a Célula Sentinela
    private CelulaDupla<E> ultimo;   // Aponta para o último nó real
    private int tamanho;

    public ListaDupla() {
        primeiro = new CelulaDupla<E>(); // Célula Sentinela (não armazena dado)
        ultimo = primeiro;
        tamanho = 0;
    }

    public boolean vazia() {
        return primeiro == ultimo;
    }

    // --- INSERÇÃO ---

    // Insere no Início (logo após a sentinela): O(1)
    public void inserirInicio(E item) {
        CelulaDupla<E> tmp = new CelulaDupla<E>(item);
        
        tmp.setProximo(primeiro.getProximo()); // 1. Novo nó aponta para o antigo 1º elemento
        tmp.setAnterior(primeiro);            // 2. Novo nó aponta para trás (sentinela)
        
        // Se a lista já tinha elementos, ajusta o 'anterior' do antigo 1º elemento
        if (primeiro.getProximo() != null) {
            primeiro.getProximo().setAnterior(tmp);
        } else {
            ultimo = tmp; // Se estava vazia, o 'ultimo' passa a ser o novo nó
        }
        
        primeiro.setProximo(tmp); // 3. Sentinela aponta para o novo nó
        tamanho++;
    }

    // Insere no Fim: O(1)
    public void inserirFim(E item) {
        CelulaDupla<E> tmp = new CelulaDupla<E>(item);
        
        ultimo.setProximo(tmp);   // 1. Antigo último aponta para frente (novo nó)
        tmp.setAnterior(ultimo);  // 2. Novo nó aponta para trás (antigo último)
        ultimo = tmp;             // 3. 'ultimo' avança para o novo nó
        tamanho++;
    }

    // Insere em qualquer posição k: O(n)
    public void inserir(E item, int pos) throws Exception {
        if (pos < 0 || pos > tamanho) throw new Exception("Erro ao inserir: Posição inválida!");
        if (pos == 0) { inserirInicio(item); return; }
        if (pos == tamanho) { inserirFim(item); return; }

        // Caminha até o nó da posição pos - 1
        CelulaDupla<E> p = primeiro;
        for (int i = 0; i < pos; i++) p = p.getProximo();

        CelulaDupla<E> tmp = new CelulaDupla<E>(item);
        
        // Conecta o nó novo entre 'p' e 'p.proximo'
        tmp.setProximo(p.getProximo());
        tmp.setAnterior(p);
        p.getProximo().setAnterior(tmp);
        p.setProximo(tmp);
        
        tamanho++;
    }

    // --- REMOÇÃO ---

    // Remove do Início: O(1)
    public E removerInicio() throws Exception {
        if (vazia()) throw new Exception("Erro ao remover: Lista vazia!");

        CelulaDupla<E> tmp = primeiro.getProximo(); // Nó a ser removido
        E item = tmp.getItem();

        primeiro.setProximo(tmp.getProximo()); // Sentinela pula o nó removido

        if (tmp.getProximo() != null) {
            tmp.getProximo().setAnterior(primeiro); // Ajusta o ponteiro para trás do novo 1º nó
        } else {
            ultimo = primeiro; // Se a lista ficou vazia, 'ultimo' volta para a sentinela
        }

        tmp.setProximo(null);
        tmp.setAnterior(null);
        tamanho--;
        return item;
    }

    // Remove do Fim: O(1) — A grande vantagem da Lista Dupla!
    public E removerFim() throws Exception {
        if (vazia()) throw new Exception("Erro ao remover: Lista vazia!");

        E item = ultimo.getItem();
        CelulaDupla<E> tmp = ultimo;

        ultimo = ultimo.getAnterior(); // Graças ao 'anterior', voltar para a penúltima célula é O(1)
        ultimo.setProximo(null);       // Desconecta o último nó antigo

        tmp.setAnterior(null);
        tamanho--;
        return item;
    }

    // Inverte a lista trocando os ponteiros 'proximo' e 'anterior' de cada nó
    public void inverter() {
        if (vazia() || primeiro.getProximo().getProximo() == null) return;

        CelulaDupla<E> atual = primeiro.getProximo();
        CelulaDupla<E> temp = null;

        this.ultimo = atual; // O 1º nó real vai se tornar o último

        while (atual != null) {
            // Inverte as referências de ponteiro do nó atual
            temp = atual.getAnterior();
            atual.setAnterior(atual.getProximo());
            atual.setProximo(temp);

            // Avança para o próximo nó (que agora está guardado no 'anterior')
            atual = atual.getAnterior();
        }

        // Ajusta a sentinela para apontar para o novo primeiro nó
        if (temp != null) {
            primeiro.setProximo(temp.getAnterior());
            temp.getAnterior().setAnterior(primeiro);
        }
    }
}
