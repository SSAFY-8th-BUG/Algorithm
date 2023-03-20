import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


public class Main { 
	
	static int N;
	static int answer;
	static Integer[] plusLst, lst;
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		answer=0;
		lst = new Integer[N];
		for(int i=0; i<N; i++) {
			lst[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(lst);
		
		int N2=(N+1)*N/2;
		plusLst = new Integer[N2];
		int idx=0;
		for(int i=0; i<N; i++) {
			for(int j=i; j<N; j++) {
				plusLst[idx++] = lst[i]+lst[j];
			}
		}
		Arrays.sort(plusLst);
		//System.out.println(Arrays.toString(plusLst));
		find();
		System.out.println(answer);
		
		
	}	
	
	static void find() {
		for(int i=N-1; i>=0; i--) {
			for(int j=0; j<=i; j++) {
				
				if(Arrays.binarySearch(plusLst,  lst[i]-lst[j])>=0) {
					answer= lst[i];
					return;
				}
			}
		}
	}
	

}