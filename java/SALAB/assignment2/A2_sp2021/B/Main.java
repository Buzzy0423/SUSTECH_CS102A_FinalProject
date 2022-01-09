import java.util.Scanner;

/**
 * @author : macmo
 * @created : 2021-03-03
**/

public class Main {
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] a = new int[n];
        int[] b = new int[m];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
        int match = 0;
        for (int i = 0; i < m; i++) {
            b[i] = sc.nextInt();
            if (b[i] == a[match]) {
                match++;
                if (match == n) {
                    System.out.println("Yes");
                    return;
                }
            }
        }
        System.out.println("No");
    }
}
