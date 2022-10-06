package bfs.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 처음에 그냥 dfs로 풀었는데 stackoverflow 발생!
 * -> 3x3 이차원 배열을 1차원 배열 String 형식으로 표현하고, HashMap에 넣어 관리
 * 
 * HashMap <String, Integer> 로 나타내는데 
 * String은 1차원 배열로 나타낸 맵, Integer은 현재 String 모습이 되기까지 몇 번 움직였는지를 나타냄 
 * 
 */
public class BOJ1525_2 {
	
	static final int dy[]= {0,0,1,-1};
	static final int dx[]= {1,-1,0,0};

	static final String ANS="123456780";	//정답 (움직일 때마다 비교하기 위해)
	
	static Map<String, Integer> map=new HashMap<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=null;
		StringBuilder sb=new StringBuilder();
		
		for (int i=0; i<3; i++) {
			st=new StringTokenizer (br.readLine());
			for (int j=0; j<3; j++) {
				sb.append(st.nextToken());	//3x3인 이차원 배열을 일차원 배열로 나타내고 String형식으로 저장
			}
		}

		System.out.println(bfs(sb.toString()));	
	}
	
	private static int bfs (String init) {
		Queue<String> q=new ArrayDeque<>();
		q.add(init);
		map.put(init, 0);
		
		while (!q.isEmpty()) {
			String pos=q.poll();
			int cnt=map.get(pos);
			int zero=pos.indexOf('0');	// 일차원 배열에서 0이 있는 위치가 어딘지 알아냄
			int y=zero/3;				// 3x3 이차원 배열로 나타냈을 때, 어느 위치인지 파악하기 위해
			int x=zero%3;
			
			if (pos.equals(ANS))		// 정답과 일치할 경우, cnt 반환
				return cnt;
			
			for (int d=0; d<4; d++) {
				int ny=y+dy[d];
				int nx=x+dx[d];
				
				if (ny<0 || nx<0 || ny>=3 || nx>=3) continue;
				int nPos=ny*3+nx;					// 3x3 이차원 상 좌표에서 일차원 좌표로
				char ch=pos.charAt(nPos);
				
				String next=pos.replace(ch, 'x');	// 현재 0이 있던 위치를 옮기는 작업
				next=next.replace('0', ch);
				next=next.replace('x', '0');
				
				
				// 이렇게 나타내진 숫자들의 배열이 처음 나타내진 것인지?
				// 일차원으로 변환해서 HashMap에 넣지 않으면 이 과정을 표현할 수 없기 때문에 stackoverflow 발생
				if (!map.containsKey(next)) {		
					q.add(next);
					map.put(next, cnt+1);
				}
			}
		}
		return -1;
	}

}
