import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main { 
	
	static int N;
	
	static int[] lst;
	static int minGap, lv, rv;
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		lst = new int[N];
		StringTokenizer stn = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			lst[i] = Integer.parseInt(stn.nextToken());
		}
		minGap = 2000000001;
		find();
		System.out.println(lv+" "+rv);
		
	}	
	
	static void find() {
		int left=0; int right = N-1;
		while(left<right) {
			int sum = lst[left] + lst[right];
			if(Math.abs(sum) < minGap) {
				minGap = Math.abs(sum);
				lv = lst[left];
				rv = lst[right];
			}
			if(sum > 0) {
				right--;
			}
			else if(sum < 0) {
				left++;
			}else {
				return;
			}
		}
	}
	
	

}