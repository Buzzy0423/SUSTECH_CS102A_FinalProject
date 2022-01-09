import java.util.Scanner;

/**
 * @author : macmo
 * @created : 2021-03-03
**/

public class Main {
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] s = new int[n];
        int startIndex = 0, endIndex = 0;
        for (int i = 0, j = 0; i < n; i++) {
            s[i] = sc.nextInt();
            if (i == 0 || s[i] < s[i - 1]) {
                if (i - j >= endIndex - startIndex) {
                    endIndex = i;
                    startIndex = j;
                }
                j = i;
            }
        }
        if (endIndex == 0) {
            endIndex = n;
        }
        for (int i = startIndex; i < endIndex; i++) {
            System.out.printf("%d ", s[i]);
        }
    }
}
