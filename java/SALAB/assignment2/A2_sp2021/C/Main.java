import java.util.Scanner;

/**
 * @author : macmo
 * @created : 2021-03-03
**/

public class Main {
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        char[] a = sc.next().toCharArray();
        char[] b = sc.next().toCharArray();
        if (a.length < b.length) {
            char[] c = a;
            a = b;
            b = c;
        }
        char[] ans = new char[a.length + 1];
        for (int i = a.length - 1, j = b.length - 1; i >= 0; ) {
            ans[k + 1] = (char) (a[i] - '0' + b[j] + ans[k + 1]);
            if (ans[k + 1] == '2') {
                ans[k] = '\001';
                ans[k + 1] = '0';
            }

            i--;
            j--;
        }

        if (ans[0] == '\001') {
            System.out.print(1);
        }
        for (int k = 1; k < ans.length; k++) {
            System.out.print(ans[k]);
        }
    }
}
