import java.util.Scanner;public class A3Q3 {    public static void main(String[] args) {        Scanner input = new Scanner(System.in);        int n = input.nextInt();        char[][] data = new char[n + 2][n + 2];        // create an extra circle outside the chessboard to avoid array index out of bounds exception.        for(int i = 1; i <= n; i++){            char[] characters = input.next().toCharArray();            for(int j = 1; j <= n; j++){                data[i][j] = characters[j - 1] == '*' ? 'F' : '0';            }        }        for(int i = 1; i <= n; i++){            for(int j = 1; j <= n; j++){                if(data[i][j] == 'F'){                    for(int x = i - 1; x <= i + 1; x++){                        for(int y = j - 1; y <= j + 1; y++){                            data[x][y] = data[x][y] == 'F' ? 'F' : (char) (data[x][y] + 1);                        }                    }                }            }        }        for(int i = 1; i <= n; i++){            for(int j = 1; j <= n; j++){                System.out.print(data[i][j]);            }            System.out.print('\n');        }    }}