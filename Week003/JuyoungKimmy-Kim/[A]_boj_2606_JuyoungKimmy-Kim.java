package bfs.dfs;

/*
 * 처음에 단방향 인접 리스트로 만들었다가
 * 2-1로 연결된 경우 바이러스에 걸리게 되는 컴퓨터의 수가 1개 임에도 체크를 하지 못 함
 *
 * 시간 복잡도 : O(N)
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ2606 {

	static int N,M;
	static List<Integer>[] adj;
	static boolean[] checked;
	static int ret;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=null;
		
		N=Integer.parseInt(br.readLine());
		M=Integer.parseInt(br.readLine());
		
		adj=new ArrayList[N+1];
		checked=new boolean[N+1];
		
		for (int i=1; i<=N; i++)
			adj[i]=new ArrayList<Integer>();
		
		for (int i=0; i<M; i++) {
			st=new StringTokenizer (br.readLine());
			int from=Integer.parseInt(st.nextToken());
			int to=Integer.parseInt(st.nextToken());
			
			adj[from].add(to);
			adj[to].add(from);	// 양방향 리스트 생성
		}
		checked[1]=true; 
		find(1);
		System.out.println(ret);
		

	}
	
	private static void find (int from) {
		for (int to=0; to<adj[from].size(); to++) {
			int next=adj[from].get(to);
			if (!checked[next]) {
				checked[next]=true;
				ret++;
				
				find (next);
			}
		}
	}
}
