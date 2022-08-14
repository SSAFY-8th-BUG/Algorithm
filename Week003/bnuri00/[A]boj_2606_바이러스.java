import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class A_boj_2606_바이러스 {
	
	static int C, E;
	static List[] com;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		C = Integer.parseInt(br.readLine());	// 컴퓨터 수
		E = Integer.parseInt(br.readLine());	// 간선 수
		
		// 연결된 컴퓨터 넣을 리스트 초기화
		com = new List[C+1];	//0: dummy
		for(int i = 1; i <= C; i++)
			com[i] = new ArrayList<Integer>();
		
		
		for(int i = 1; i <= E; i++) {
			st = new StringTokenizer(br.readLine());
			
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			
			// 해당 번호와 연결된 컴퓨터 리스트에 넣음 (양쪽다)
			com[n1].add(n2);
			com[n2].add(n1);
		}
		
		System.out.println(bfs());	// 리턴값 = 감염된 컴퓨터수

	}
	static int bfs() {
		Queue<Integer> q = new ArrayDeque<>();
		boolean[] visit = new boolean[C+1];
		int cnt = 0;
		
		q.add(1);	// 1번 컴퓨터에서 웜 감염 시작함
		visit[0] = visit[1] = true;
		
		while(!q.isEmpty()) {
			List<Integer> list = com[q.poll()];	// queue에서 꺼낸 컴퓨터와 연결된 컴퓨터 리스트 가져옴
			
			for(int i = 0; i < list.size(); i++) {
				int item = list.get(i);
				
				if(visit[item]) continue;	// 이미 방문한 컴퓨터
				
				// 감염시키기, 카운팅 후 큐에 넣음
				visit[item] = true;	// 감염시킴
				cnt++;
				q.add(item);	// 다음 희생 컴퓨터 찾으러감
			}
			
		}
		return cnt;
	}
}
