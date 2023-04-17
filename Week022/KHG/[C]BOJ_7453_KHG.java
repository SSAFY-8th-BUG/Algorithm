import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Main { 
	
	static int N,M;
	static int[][] sorted, unis;
	static double[][]  order;
	static int answer;
	static boolean[] checked;
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer stn = new StringTokenizer(br.readLine());
		M = Integer.parseInt(stn.nextToken());
		N = Integer.parseInt(stn.nextToken());
		unis = new int[M][N];
		sorted = new int[M][N];
		for(int i=0; i<M; i++) {
			stn = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				unis[i][j] = Integer.parseInt(stn.nextToken());
				sorted[i][j] =unis[i][j]; 
			}
			Arrays.sort(sorted[i]);
		}
		order = new double[M][N];
		for(int i=0; i<M; i++) {
			double[] cnt = new double[1000001];
			for(int j=0; j<N; j++) {
				order[i][j] = find(i, j) + cnt[unis[i][j]];
				cnt[unis[i][j]]+= 0.0001;
			}
		}
		//for(int i=0; i<M; i++)System.out.println(Arrays.toString(order[i]));
		
		checked = new boolean[M];
		for(int u1=0; u1<M-1; u1++) {
			
			for(int u2=u1+1; u2<M; u2++) {
				
				check(u1,u2); 
			}
		}
		
		System.out.println(answer);
		
		
	}	
	
	static int find(int i, int j) {
		if (sorted[i][0] == sorted[i][N-1]) return 0;
		int num = unis[i][j];
		int index = Arrays.binarySearch(sorted[i], num);
		while(index>0) {
			if(sorted[i][index-1] == num) 
				index--;
			else
				break;
		}
		
		return index;
	}
	
	static boolean check(int u1, int u2) {
		for(int i=0; i<N; i++) {
			if(order[u1][i] != order[u2][i]) 
				return false;
		}
		answer++;
		checked[u1]=true;
		checked[u2]=true;
		
		return true;
	}
	
	

}