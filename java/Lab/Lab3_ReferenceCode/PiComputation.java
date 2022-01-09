

import java.util.Scanner;


public class PiComputation {

	public static void main(String[] args) {
		float Pi = 0;
		
		Scanner in = new Scanner(System.in);
		System.out.println("Please input the precision:");
		float precision = in.nextFloat();
		in.close();
		int i = 1;
		float P=3;
		while(Math.abs(P-Pi) > precision){
			P = Pi;
			if(i % 2 == 0)
				Pi -= 4.0/(2*i-1);
			else
				Pi += 4.0/(2*i-1);

			i++;
		}


		System.out.printf("The estimatioin of Pi is %.6f \nIt computed %d times", Pi, --i);
	}

}
