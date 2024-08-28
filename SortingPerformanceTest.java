import java.util.Random;

public class SortingPerformanceTest {

    public static void main(String[] args) {
        // Tamanho do array
        int arraySize = 100000000;
        int[] array = generateRandomArray(arraySize);

        // Clonar o array para garantir que ambos os algoritmos ordenem o mesmo conjunto de dados
        int[] arrayForQuickSort = array.clone();
        int[] arrayForMergeSort = array.clone();

        // Testar o desempenho do QuickSort
        long startTime = System.nanoTime();
        quickSort(arrayForQuickSort, 0, arrayForQuickSort.length - 1);
        long endTime = System.nanoTime();
        long durationQuickSort = endTime - startTime;
        System.out.println("QuickSort levou: " + (durationQuickSort * 0.000000001) + " segundos.");

        // Testar o desempenho do MergeSort
        startTime = System.nanoTime();
        mergeSort(arrayForMergeSort, 0, arrayForMergeSort.length - 1);
        endTime = System.nanoTime();
        long durationMergeSort = endTime - startTime;
        System.out.println("MergeSort levou: " + (durationMergeSort * 0.000000001) + " segundos.");
    }

    // Função para gerar um array de números aleatórios
    private static int[] generateRandomArray(int size) {
        Random random = new Random();
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(size);
        }
        return array;
    }

    // Implementação do QuickSort
    private static void quickSort(int[] array, int low, int high) {
        if (low < high) {
            int pi = partition(array, low, high);
            quickSort(array, low, pi - 1);
            quickSort(array, pi + 1, high);
        }
    }

    private static int partition(int[] array, int low, int high) {
        int pivot = array[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (array[j] <= pivot) {
                i++;
                // Troca
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
        int temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;
        return i + 1;
    }

    // Implementação do MergeSort
    private static void mergeSort(int[] array, int left, int right) {
        if (left < right) {
            int middle = (left + right) / 2;
            mergeSort(array, left, middle);
            mergeSort(array, middle + 1, right);
            merge(array, left, middle, right);
        }
    }

    private static void merge(int[] array, int left, int middle, int right) {
        int n1 = middle - left + 1;
        int n2 = right - middle;

        int[] L = new int[n1];
        int[] R = new int[n2];

        System.arraycopy(array, left, L, 0, n1);
        System.arraycopy(array, middle + 1, R, 0, n2);

        int i = 0, j = 0;
        int k = left;

        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                array[k] = L[i];
                i++;
            } else {
                array[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            array[k] = L[i];
            i++;
            k++;
        }

        while (j < n2) {
            array[k] = R[j];
            j++;
            k++;
        }
    }
}
