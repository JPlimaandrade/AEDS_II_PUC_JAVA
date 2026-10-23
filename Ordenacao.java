public class Ordenacao {

    // --- QUICKSORT ---
    public static void quicksort(int[] array, int esq, int dir) {
        int i = esq, j = dir;
        int pivo = array[(esq + dir) / 2]; // Escolha do pivô pelo elemento central

        // Particionamento: menores que o pivô à esq, maiores à dir
        while (i <= j) {
            while (array[i] < pivo) i++; // Procura elemento maior/igual ao pivô na esquerda
            while (array[j] > pivo) j--; // Procura elemento menor/igual ao pivô na direita
            if (i <= j) {
                swap(array, i, j); // Troca os elementos fora de ordem
                i++;
                j--;
            }
        }
        // Chamadas recursivas para as duas metades
        if (esq < j) quicksort(array, esq, j);
        if (i < dir) quicksort(array, i, dir);
    }

    // --- MERGESORT ---
    public static void mergesort(int[] array, int esq, int dir) {
        if (esq < dir) {
            int meio = (esq + dir) / 2;
            mergesort(array, esq, meio);    // Divide a metade esquerda
            mergesort(array, meio + 1, dir);// Divide a metade direita
            intercalar(array, esq, meio, dir);// Intercala (combina) as metades ordenadas
        }
    }

    // Função Auxiliar de Intercalação (Merge)
    private static void intercalar(int[] array, int esq, int meio, int dir) {
        int n1 = meio - esq + 1;
        int n2 = dir - meio;
        
        // Vetores temporários para armazenar as metades
        int[] L = new int[n1];
        int[] R = new int[n2];

        // Copia dados para os vetores temporários
        for (int i = 0; i < n1; i++) L[i] = array[esq + i];
        for (int j = 0; j < n2; j++) R[j] = array[meio + 1 + j];

        int i = 0, j = 0, k = esq;
        
        // Compara elemento a elemento e coloca em ordem no vetor principal
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) array[k++] = L[i++];
            else array[k++] = R[j++];
        }
        // Copia os elementos restantes de L (se houver)
        while (i < n1) array[k++] = L[i++];
        // Copia os elementos restantes de R (se houver)
        while (j < n2) array[k++] = R[j++];
    }

    // --- HEAPSORT ---
    public static void heapsort(int[] array, int n) {
        // 1. Constrói o Max-Heap (organiza o vetor do meio para o início)
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(array, n, i);
        }

        // 2. Extrai o maior elemento (raiz) e move para o final do vetor
        for (int i = n - 1; i > 0; i--) {
            swap(array, 0, i);  // Maior elemento vai para a posição 'i'
            heapify(array, i, 0); // Reorganiza a raiz com o tamanho reduzido 'i'
        }
    }

    // Mantém a propriedade de Max-Heap (pai maior que os filhos)
    private static void heapify(int[] array, int n, int i) {
        int maior = i;       // Inicializa o maior como raiz
        int esq = 2 * i + 1; // Índice do filho da esquerda
        int dir = 2 * i + 2; // Índice do filho da direita

        // Verifica se o filho da esquerda existe e é maior que a raiz
        if (esq < n && array[esq] > array[maior]) maior = esq;

        // Verifica se o filho da direita existe e é maior que o maior até agora
        if (dir < n && array[dir] > array[maior]) maior = dir;

        // Se o maior não for a raiz, realiza a troca e ajusta a subárvore afetada
        if (maior != i) {
            swap(array, i, maior);
            heapify(array, n, maior); // Recursão para continuar descendo no Heap
        }
    }

    // Método auxiliar genérico de troca de posições no vetor
    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
