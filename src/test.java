import java.util.Scanner;

public class test {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int i = scanner.nextInt();
        if (i <= 200) {
            int[] arr = new int[i];
            for (int j = 0; j < arr.length; j++) {
                arr[j] = (int) (1 + Math.random() * (1000 - -1000 + 1));
            }
            for (int j = 0; j < arr.length; j++) {
                for (int k = j; k < arr.length; k++) {
                    if (arr[j] > arr[k]) {
                        int temp = arr[k];
                        arr[k] = arr[j];
                        arr[j] = temp;
                    }
                }
            }
            for (int k = 0; k < arr.length; k++) {
                System.out.print(arr[k] + "\t");
            }
        }
    }
}