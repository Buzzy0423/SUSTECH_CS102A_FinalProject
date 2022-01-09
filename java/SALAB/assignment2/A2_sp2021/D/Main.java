import java.util.Scanner;

/**
 * @author : macmo
 * @created : 2021-03-03
 **/
public class Main {
    public static void main(String[] args) {
        int[] daysOfMonth = {-1, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        Scanner sc = new Scanner(System.in);
        int d1 = sc.nextInt();
        int d2 = sc.nextInt();
        int year = d1 / 10000;
        if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
            daysOfMonth[2] = 29;
        }

        int count = 0;
        while (d1 <= d2) {
            int tmp = d1;
            int reverse = 0;
            for (int i = 0; i < 8; i++) {
                reverse = reverse * 10 + tmp % 10;
                tmp /= 10;
            }
            if (reverse == d1) {
                count++;
            }

            year = d1 / 10000;
            int month = d1 % 10000 / 100;
            int day = d1 % 100;
            day++;
            if (day == daysOfMonth[month] + 1) {
                day = 1;
                month++;
                if (month == 13) {
                    month = 1;
                    year++;
                    if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
                        daysOfMonth[2] = 29;
                    } else {
                        daysOfMonth[2] = 28;
                    }
                }
            }
            d1 = year * 10000 + month * 100 + day;
        }
        System.out.println(count);
    }
}
