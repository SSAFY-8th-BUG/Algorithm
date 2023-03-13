import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int N;
	static int[] ldp;
	static int[] rdp;
	static int[] lst;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		StringTokenizer stn = new StringTokenizer(br.readLine());
		lst = new int[N];
		for(int i=0; i<N; i++) {
			lst[i] = Integer.parseInt(stn.nextToken());
		}
		
		ldp = new int[N];
		rdp = new int[N];
		
		checkLeft();
		checkRight();
		//System.out.println(Arrays.toString(ldp));
		//System.out.println(Arrays.toString(rdp));
		int answer=0;
		for (int i=0; i<N; i++) {
			answer = Math.max(answer, ldp[i]+rdp[i]-1);
		}
		System.out.println(answer);
	}

	
	static void checkLeft() {
		ldp[0]=1;
		for(int i=1;i<N; i++) {
			ldp[i]=1;
			for(int j=i-1; j>=0; j--) {
				if(lst[j]< lst[i]) {
					ldp[i] = Math.max(ldp[i], ldp[j]+1);
				}
			}
		}
	}
	
	static void checkRight() {
		rdp[N-1]=1;
		for(int i=N-2; i>=0; i--) {
			rdp[i]=1;
			for(int j=i+1; j<N; j++) {
				if(lst[j]< lst[i]) {
					rdp[i] = Math.max(rdp[i], rdp[j]+1);
				}
			}
		}
	}
}
