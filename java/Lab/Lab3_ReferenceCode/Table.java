

public class Table {

	public static void main(String[] args) {
		//for(int i = 1; i <= 9; i++){
		int i=1;
		while(i<=9){
			//for(int j = 1; j <= i; j++)
			int j=1;
			while(j<=i) {
				System.out.printf("%d * %d = %2d  ", j, i, j * i);
				j++;
			}
			System.out.println();
			i++;
		}
				
	}
}