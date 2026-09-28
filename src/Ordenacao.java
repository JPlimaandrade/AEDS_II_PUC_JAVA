import java.util.Scanner;

public class Ordenacao {

    // =====================================================
    // BUBBLE SORT
    // =====================================================

    public static void bubbleSort(int[] vetor) {

        for (int i = 0; i < vetor.length - 1; i++) {

            for (int j = 0; j < vetor.length - 1 - i; j++) {

                if (vetor[j] > vetor[j + 1]) {

                    int temp = vetor[j];
                    vetor[j] = vetor[j + 1];
                    vetor[j + 1] = temp;
                }
            }
        }
    }


    // =====================================================
    // SELECTION SORT
    // =====================================================

    public static void selectionSort(int[] vetor) {

        for (int i = 0; i < vetor.length - 1; i++) {

            int menor = i;

            for (int j = i + 1; j < vetor.length; j++) {

                if (vetor[j] < vetor[menor]) {
                    menor = j;
                }
            }

            int temp = vetor[i];
            vetor[i] = vetor[menor];
            vetor[menor] = temp;
        }
    }


    // =====================================================
    // INSERTION SORT
    // =====================================================

    public static void insertionSort(int[] vetor) {

        for (int i = 1; i < vetor.length; i++) {

            int chave = vetor[i];
            int j = i - 1;

            while (j >= 0 && vetor[j] > chave) {

                vetor[j + 1] = vetor[j];
                j--;
            }

            vetor[j + 1] = chave;
        }
    }


    // =====================================================
    // MERGE SORT
    // =====================================================

    public static void mergeSort(int[] vetor) {

        if (vetor.length < 2) {
            return;
        }

        int meio = vetor.length / 2;

        int[] esquerda = new int[meio];
        int[] direita = new int[vetor.length - meio];

        for (int i = 0; i < meio; i++) {
            esquerda[i] = vetor[i];
        }

        for (int i = meio; i < vetor.length; i++) {
            direita[i - meio] = vetor[i];
        }

        mergeSort(esquerda);
        mergeSort(direita);

        merge(vetor, esquerda, direita);
    }


    public static void merge(
            int[] vetor,
            int[] esquerda,
            int[] direita) {

        int i = 0;
        int j = 0;
        int k = 0;

        while (i < esquerda.length &&
               j < direita.length) {

            if (esquerda[i] <= direita[j]) {

                vetor[k] = esquerda[i];
                i++;

            } else {

                vetor[k] = direita[j];
                j++;
            }

            k++;
        }

        while (i < esquerda.length) {

            vetor[k] = esquerda[i];
            i++;
            k++;
        }

        while (j < direita.length) {

            vetor[k] = direita[j];
            j++;
            k++;
        }
    }


    // =====================================================
    // QUICK SORT
    // =====================================================

    public static void quickSort(int[] vetor, int inicio, int fim) {

        if (inicio < fim) {

            int pivo = particionar(vetor, inicio, fim);

            quickSort(vetor, inicio, pivo - 1);
            quickSort(vetor, pivo + 1, fim);
        }
    }


    public static int particionar(
            int[] vetor,
            int inicio,
            int fim) {

        int pivo = vetor[fim];

        int i = inicio - 1;

        for (int j = inicio; j < fim; j++) {

            if (vetor[j] <= pivo) {

                i++;

                int temp = vetor[i];
                vetor[i] = vetor[j];
                vetor[j] = temp;
            }
        }

        int temp = vetor[i + 1];
        vetor[i + 1] = vetor[fim];
        vetor[fim] = temp;

        return i + 1;
    }


    // =====================================================
    // HEAP SORT
    // =====================================================

    public static void heapSort(int[] vetor) {

        int n = vetor.length;

        // Construir a Heap
        for (int i = n / 2 - 1; i >= 0; i--) {

            heapify(vetor, n, i);
        }

        // Extrair elementos da Heap
        for (int i = n - 1; i > 0; i--) {

            int temp = vetor[0];
            vetor[0] = vetor[i];
            vetor[i] = temp;

            heapify(vetor, i, 0);
        }
    }


    public static void heapify(
            int[] vetor,
            int tamanho,
            int raiz) {

        int maior = raiz;

        int esquerda = 2 * raiz + 1;
        int direita = 2 * raiz + 2;

        if (esquerda < tamanho &&
            vetor[esquerda] > vetor[maior]) {

            maior = esquerda;
        }

        if (direita < tamanho &&
            vetor[direita] > vetor[maior]) {

            maior = direita;
        }

        if (maior != raiz) {

            int temp = vetor[raiz];
            vetor[raiz] = vetor[maior];
            vetor[maior] = temp;

            heapify(vetor, tamanho, maior);
        }
    }


    // =====================================================
    // MOSTRAR VETOR
    // =====================================================

    public static void mostrarVetor(int[] vetor) {

        for (int i = 0; i < vetor.length; i++) {

            System.out.print(vetor[i] + " ");
        }

        System.out.println();
    }


    // =====================================================
    // MAIN / MENU
    // =====================================================

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Tamanho do vetor: ");
        int tamanho = scanner.nextInt();

        int[] vetor = new int[tamanho];

        System.out.println("Digite os elementos:");

        for (int i = 0; i < tamanho; i++) {

            vetor[i] = scanner.nextInt();
        }

        System.out.println("\nVetor original:");
        mostrarVetor(vetor);

        System.out.println("\n===== ORDENACAO =====");
        System.out.println("1 - Bubble Sort");
        System.out.println("2 - Selection Sort");
        System.out.println("3 - Insertion Sort");
        System.out.println("4 - Merge Sort");
        System.out.println("5 - Quick Sort");
        System.out.println("6 - Heap Sort");

        System.out.print("Escolha: ");
        int opcao = scanner.nextInt();

        switch (opcao) {

            case 1:
                bubbleSort(vetor);
                break;

            case 2:
                selectionSort(vetor);
                break;

            case 3:
                insertionSort(vetor);
                break;

            case 4:
                mergeSort(vetor);
                break;

            case 5:
                quickSort(vetor, 0, vetor.length - 1);
                break;

            case 6:
                heapSort(vetor);
                break;

            default:
                System.out.println("Opcao invalida!");
                scanner.close();
                return;
        }

        System.out.println("\nVetor ordenado:");
        mostrarVetor(vetor);

        scanner.close();
    }
}