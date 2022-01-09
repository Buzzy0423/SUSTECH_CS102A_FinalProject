import java.util.Scanner;

public class A3Q1 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int M = scanner.nextInt();
        int N = scanner.nextInt();

        int[][] result = new int[2 * M][2 * N];

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                result[i][j] = scanner.nextInt();
            }
        }

        for (int i = 0; i < 2 * M; i++) {
            for (int j = 0; j < 2 * N; j++) {
                if (i < M && j >= N) { // top right
                    result[i][j] = result[i][2 * N - j - 1];
                } else if (i >= M && j < N) { // bottom left
                    result[i][j] = result[2 * M - i - 1][j];
                } else if (i >= M && j >= N) { // bottom right
                    result[i][j] = result[2 * M - i - 1][2 * N - j - 1];
                }
            }
        }

        for (int i = 0; i < 2 * M; i++) {
            for (int j = 0; j < 2 * N; j++) {
                System.out.print(result[i][j] + " ");
            }
            System.out.println();
        }

    }

}
