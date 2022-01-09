
import java.io.*;
import java.util.*;

public class A3Q2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int [][] array = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                char cur = input.next().charAt(0);
                if (cur == 'O'){
                    array[i][j] = 1;
                }
                else if (cur == 'X'){
                    array[i][j] = -1;
                }
            }
        }
        //char type -> int type, N-0, O->1, X->-1

        int[][] upLeft = new int[n][n];
        int[][] up = new int[n][n];
        int[][] upRight = new int[n][n];
        int[][] left = new int[n][n];
        int[][] right = new int[n][n];
        int[][] downLeft = new int[n][n];
        int[][] down = new int[n][n];
        int[][] downRight = new int[n][n];
        int[][] upDown = new int[n][n];
        int[][] leftRight = new int[n][n];
        int[][] leftUpDown = new int[n][n];
        int[][] rightUpDown = new int[n][n];

        //upLeft
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < n; j++) {
                if (array[i-1][j-1] == 1){
                    upLeft[i][j] = upLeft[i-1][j-1] + 1;

                }
            }
        }

        //up
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (array[i-1][j] == 1){
                    up[i][j] = up[i-1][j] + 1;


                }
            }
        }

        // upRight
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < n-1; j++) {
                if (array[i-1][j+1] == 1){
                    upRight[i][j] = upRight[i-1][j+1] + 1;
                }
            }
        }

        //left
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < n; j++) {
                if (array[i][j-1] == 1){
                    left[i][j] = left[i][j-1] + 1;
                }
            }
        }

        //right
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < n; j++) {
                if (array[i][n-j] == 1){
                    right[i][n-j-1] = right[i][n-j] + 1;
                }
            }
        }

        //downLeft
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < n; j++) {
                if (array[n-i][j-1] == 1){
                    downLeft[n-i-1][j] = downLeft[n-i][j-1] + 1;
                }
            }
        }

        //down
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (array[n-i][j] == 1){
                    down[n-i-1][j] = down[n-i][j] + 1;
                }
            }
        }

        //downRight
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < n; j++) {
                if (array[n-i][n-j] == 1){
                    downRight[n-i-1][n-j-1] = downRight[n-i][n-j] + 1;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                upDown[i][j] = up[i][j] + down[i][j];
                leftRight[i][j] = left[i][j] + right[i][j];
                leftUpDown[i][j] = upLeft[i][j] + downRight[i][j];
                rightUpDown[i][j] = upRight[i][j] + downLeft[i][j];
            }
        }

        boolean flag = false;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (array[i][j] == 0){
                    if (upDown[i][j] >= 4 || leftRight[i][j] >= 4 || leftUpDown[i][j] >= 4
                            || rightUpDown[i][j] >= 4){
                        flag = true;
                        System.out.printf("(%d,%d)\n",j+1,i+1);

                    }
                }
            }
        }
        if (!flag){
            System.out.println("No");
        }
    }
}
