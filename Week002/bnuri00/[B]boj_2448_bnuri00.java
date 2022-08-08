package stude_02;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class B_boj_2448_bnuri00 {

	static char[][] starArr;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		int arrWidth = 5;
		
		if (n != 3 ){
			int blank = 0;
			int exp = (int) (Math.log(n/3) / Math.log(2));
			for(int i = exp; i > 0; i--) blank+= Math.pow(2, i-1); 
			
			arrWidth = n/3*5 +  blank;
		} 
		
		starArr = new char[n][arrWidth];
		
		for(int i = 0; i < n; i++)
			Arrays.fill(starArr[i], ' ');
		
		star(n, 0, 0);
		
		for(char[] c : starArr) System.out.println(c);

	}
	
	static void star(int n, int row, int col) {
		
		if(n == 3) {
			for(int i = 0; i < 3; i++) {
				for(int j = 0; j < 5; j++) {
					if((i==0 && j != 2)||
							(i==1 && !(j==1 || j == 3)))
						continue;
					starArr[row+i][col+j] = '*';
				}
			}
			return;
		}
		
		int h = n/2;
		
		int blank = 0;
		int exp = (int) (Math.log(n/3) / Math.log(2));
		for(int i = exp; i > 0; i--) blank+= Math.pow(2, i-1); 
		
		int width = n/3*5 +  blank;
		
		for(int i = 0; i < 2; i++) {
			for(int j = 0; j < 4; j++) {
				if((i == 0 && j == 1)
						||(i==1 && (j==0 || j == 2)))
					star(h, row + (h*i), col+(h*j));
			}
		}	
	}

}
