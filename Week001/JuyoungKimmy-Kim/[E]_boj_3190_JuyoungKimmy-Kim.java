import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/*
 * O(Time)
 * 
 * 1. 뱀이 위치할 좌표 (y,x)를 deque에 저장하기 위해 y,x를 가지는 클래스 생성
 * 2. 뱀의 현재 방향을 기준으로 왼쪽, 오른쪽으로 회전했을 때 뱀의 방향을 바꾸는 함수 change_dir()
 * 
 */

class Snake {
	int y,x;
	
	Snake(int y, int x) {
		this.y=y; this.x=x;
	}
}

public class E_boj_3190 {
	
	static final int dy[]= {0,0,1,-1};
	static final int dx[]= {1,-1,0,0};
	
	static final int EMPTY=0; // map에서 빈 공간
	static final int APPLE=1; // map에서 사과가 있는 공간
	static final int POS=-1; // map에서 뱀이 위치한 공간
	
	static int N;
	static int[][] map;
	static int cur_y=1, cur_x=1; // 뱀의 처음 위치
	static int cur_d=0; //뱀의 처음 방향

	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=null;
		
		N=Integer.parseInt(br.readLine());
		map=new int[N+1][N+1];
		
		//#1. 뱀의 처음 위치를 deque에 추가하고 map에 표시해줌
		Deque<Snake> snake=new ArrayDeque<>();
		snake.add(new Snake(1,1));
		map[1][1]=POS;
		
		
		int K=Integer.parseInt(br.readLine());
		
		//#2. 사과의 좌표를 입력받으며 map에 표시해줌
		for (int i=0; i<K; i++) {
			st=new StringTokenizer (br.readLine());
			int y=Integer.parseInt(st.nextToken());
			int x=Integer.parseInt(st.nextToken());
			
			map[y][x]=APPLE;
		}
		
		int L=Integer.parseInt(br.readLine());
		
		int Time=0;
		
		//#3. 뱀의 방향이 바뀌는 시간과 방향을 입력 받으며 동시에 뱀 움직임 진행
		/*
		 * 
		 * e.g. 3 L => 3초째에 L방향으로 회전이라는 입력을 받았을 때
		 * 		현재 시간 (Time)을 기준으로 3초까지는 계속 움직임 진행
		 * 		3초째에 방향 변경
		 * 
		 */
		for (int i=0; i<L; i++) {
			st=new StringTokenizer (br.readLine());
			int sec=Integer.parseInt(st.nextToken()); // 뱀이 방향을 바꿀 시간
			char d=st.nextToken().charAt(0); // 뱀이 바꿀 방향
			
			while (Time!=sec) {
				int ny=cur_y+dy[cur_d];
				int nx=cur_x+dx[cur_d];
				
				// 뱀이 다음에 위치할 좌표가 벽이거나 자신의 몸이면 게임 종료
				if (ny<1 || nx<1 || ny>N || nx>N || 
						map[ny][nx]==POS) {
					System.out.println(Time+1);
					return ;
				}
				
				// 다음 방향이 사과가 아니면 꼬리 한 칸 땡겨줌
				if (map[ny][nx]!=APPLE) {
					Snake tmp=snake.removeLast();
					map[tmp.y][tmp.x]=EMPTY;
				}
				
				// 앞 칸에 머리 위치
				map[ny][nx]=POS;
				snake.addFirst(new Snake (ny, nx));
				cur_y=ny;
				cur_x=nx;
				
				Time++;
			}
			change_dir(d);
		}
		
		// #4. 뱀의 방향 정보가 입력이 끝났는데 아직 게임이 끝나지 않은 경우
		// -> 계속 현재 방향으로 이동
		
		while (true) {
			cur_y+=dy[cur_d];
			cur_x+=dx[cur_d];
			
			if (cur_y<1 || cur_x<1 || cur_y>N || cur_x>N || 
					map[cur_y][cur_x]==POS) {
				System.out.println(Time+1);
				return ;
			}	
			Time++;
		}
	}
	
	static void change_dir (char d) {
		if (d=='L') {
			switch (cur_d) {
			case 0: cur_d=3; break;
			case 1: cur_d=2; break;
			case 2: cur_d=0; break;
			case 3: cur_d=1; break;
			}
		}
		else if (d=='D') {
			switch(cur_d) {
			case 0: cur_d=2; break;
			case 1: cur_d=3; break;
			case 2: cur_d=1; break;
			case 3: cur_d=0; break;
			}
		}
	}

}
