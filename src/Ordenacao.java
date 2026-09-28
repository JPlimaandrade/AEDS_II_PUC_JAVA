import java.util.Scanner;

public class Ordenacao {

    // =========================
    // BUBBLE SORT
    // =========================
    public static void bubbleSort(int[] vetor) {
        int n = vetor.length;

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {

                if (vetor[j] > vetor[j + 1]) {
                    int temp = vetor[j];
                    vetor[j] = vetor[j + 1];
                    vetor[j + 1] = temp;
                }
            }
        }
    }

    // =========================
    // SELECTION SORT
    // =========================
    public static void selectionSort(int[] vetor) {
        int n = vetor.length;

        for (int i = 0; i < n - 1; i++) {
            int menor = i;

            for (int j = i + 1; j < n; j++) {

                if (vetor[j] < vetor[menor]) {
                    menor = j;
                }
            }

            int temp = vetor[i];
            vetor[i] = vetor[menor];
            vetor[menor] = temp;
        }
    }

    // =========================
    // INSERTION SORT
    // =========================
    public static void insertionSort(int[] vetor) {
        int n = vetor.length;

        for (int i = 1; i < n; i++) {
            int chave = vetor[i];
            int j = i - 1;

            while (j >= 0 && vetor[j] > chave) {
                vetor[j + 1] = vetor[j];
                j--;
            }

            vetor[j + 1] = chave;
        }
    }

    // =========================
    // MERGE SORT
    // =========================
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

    public static void merge(int[] vetor, int[] esquerda, int[] direita) {

        int i = 0;
        int j = 0;
        int k = 0;

        while (i < esquerda.length && j < direita.length) {

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

    // =========================
    // MOSTRAR VETOR
    // =========================
    public static void mostrarVetor(int[] vetor) {

        for (int i = 0; i < vetor.length; i++) {
            System.out.print(vetor[i] + " ");
        }

        System.out.println();
    }

    // =========================
    // MAIN
    // =========================
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o tamanho do vetor: ");
        int tamanho = scanner.nextInt();

        int[] vetor = new int[tamanho];

        System.out.println("Digite os elementos:");

        for (int i = 0; i < tamanho; i++) {
            vetor[i] = scanner.nextInt();
        }

        System.out.println("\nVetor original:");
        mostrarVetor(vetor);

        System.out.println("\n===== MENU =====");
        System.out.println("1 - Bubble Sort");
        System.out.println("2 - Selection Sort");
        System.out.println("3 - Insertion Sort");
        System.out.println("4 - Merge Sort");
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

            default:
                System.out.println("Opção inválida!");
                scanner.close();
                return;
        }

        System.out.println("\nVetor ordenado:");
        mostrarVetor(vetor);

        scanner.close();
    }
}