import java.util.Scanner;

/**
 * @author : macmo
 * @created : 2021-03-03
**/
public class Main {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        for (int i = 2; i <= n && i <= m; i++) {
            while (n % i == 0 && m % i == 0) {
                n /= i;
                m /= i;
            }
        }
        System.out.printf("%d %d", n, m);
    }
}
