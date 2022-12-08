
import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	static int N,M,K; //크기, 팀수, 라운드
	static int answer;
	static int[][] mat; //사람위치와 순서
	static int[][] tmat;  //팀별 동선
	static boolean[] is_fwd; //팀의 큐 방향이 앞인지
	static Deque<Point>[] ques; //팀별 큐
	static int[][] dirs= {{0,1},{1,0},{-1,0},{0,-1}};
	
	
	static void init() {
		boolean[][] visited=new boolean[N][N];
		tmat=new int[N][N];
		int t=0;
		//팀별 동선과 사람들의 위치를 저장
		for(int y=0; y<N; y++) {
			for(int x=0; x<N; x++) { 
				if(mat[y][x]==1 && !visited[y][x] ) {
					visited[y][x]=true;
					t++;
					tmat[y][x]=t;
					Deque<Point> que = new ArrayDeque<Point>();
					que.add(new Point(x,y));
					ques[t]=new ArrayDeque<>();
					ques[t].add(new Point(x,y));
					int cnt=0;
					
					while(!que.isEmpty()) {
						Point cur = que.pollFirst();
						int cx=cur.x, cy=cur.y;
						int cn=mat[cy][cx];
						if(mat[cy][cx]==4) {
							mat[cy][cx]=-1;
						}else {
							mat[cy][cx]=++cnt;
						}
						tmat[cy][cx]=t;
						for(int dr=0; dr<4; dr++) {
							int nx=cx+dirs[dr][0];
							int ny=cy+dirs[dr][1];
							if(nx<0 || nx<0 || nx>=N || ny>=N || visited[ny][nx] || mat[ny][nx]==0) continue;
							if(mat[ny][nx]==cn || mat[ny][nx]==cn+1) {
								visited[ny][nx]=true;
								if(mat[ny][nx]==2 || mat[ny][nx]==3) {
									ques[t].add(new Point(nx,ny));
									que.add(new Point(nx,ny));
								}else {
									que.add(new Point(nx,ny));
								}
								
								break;
							}
						}
					}
				}
				
			}
		}
		
		for(int i=1; i<=M; i++) {
			is_fwd[i]=true;
		}
		
		/*for(int y=0; y<N; y++) {
			System.out.println(Arrays.toString(mat[y]));
		}
		System.out.println("====");
		System.out.println("팀별동선");
		for(int y=0; y<N; y++) {
			System.out.println(Arrays.toString(tmat[y]));
		}*/
		
	}
	
	static void throwBall(int r1, int r2) { //몫과 나머지
		int hitT=-1;
		if(r1==0) { //왼쪽에서 던짐
			for(int x=0; x<N; x++) {
				if(mat[r2][x]!=0 && mat[r2][x]!=-1) {
					int n=mat[r2][x];
					answer+=n*n;
					hitT=tmat[r2][x];
					//System.out.println("공맞은곳 :"+x+", "+r2+" 추가점수:"+n*n);
					break;
				}
			}
		}else if(r1==1) { //밑에서 던짐
			for(int y=N-1; y>=0; y--) {
				if(mat[y][r2]!=0 && mat[y][r2]!=-1) {
					int n=mat[y][r2];
					answer+=n*n;
					hitT=tmat[y][r2];
					//System.out.println("공맞은곳 :"+r2+", "+y+" 추가점수:"+n*n);
					break;
				}
			}
		}else if(r1==2) { //오른쪽에서 던짐
			r2=N-1-r2;
			for(int x=N-1; x>=0; x--) {
				if(mat[r2][x]!=0 && mat[r2][x]!=-1) {
					int n=mat[r2][x];
					answer+=n*n;
					hitT=tmat[r2][x];
					//System.out.println("공맞은곳 :"+x+", "+r2+" 추가점수:"+n*n);
					break;
				}
			}
		}else { //위에서 던짐
			r2=N-1-r2;
			for(int y=0; y<N; y++) {
				if(mat[y][r2]!=0 && mat[y][r2]!=-1) {
					int n=mat[y][r2];
					answer+=n*n;
					hitT=tmat[y][r2];
					//System.out.println("공맞은곳 :"+r2+", "+y+" 추가점수:"+n*n);
					break;
				}
			}
		}
		if(hitT!=-1) { //공맞은 팀 방향 바꾸기
			is_fwd[hitT]=!is_fwd[hitT];
			if(is_fwd[hitT]) {
				int ord=1;
				for(Point cur: ques[hitT]) {
					mat[cur.y][cur.x]=ord++;
				}
			}else {
				int ord=ques[hitT].size();
				for(Point cur: ques[hitT]) {
					mat[cur.y][cur.x]=ord--;
				}
			}
		}
	}
	
	static void move() {
		for(int t=1; t<=M; t++) {
			if(is_fwd[t]) {//방향이 true면
				Point first=ques[t].peekFirst();
				int fx=first.x, fy=first.y;
				Point next = null;
				for(int dr=0; dr<4; dr++) {
					int nx=fx+dirs[dr][0];
					int ny=fy+dirs[dr][1];
					if(nx<0 || ny<0 || nx>=N || ny>=N || mat[ny][nx]==0) continue;
					if(mat[ny][nx]!=2 && tmat[ny][nx]==t) {
						next=new Point(nx,ny);  //젤앞사람의 다음 위치
						break;
					}
				}
				ques[t].addFirst(next);
				Point del = ques[t].pollLast();  //끝 삭제
				mat[del.y][del.x]=-1;
				
				int ord=0;
				for(Point cur : ques[t]) { //순서 새로 써주기
					int cx=cur.x, cy=cur.y;
					mat[cy][cx]=++ord;
				}
			}
			else {//방향이 false면
				Point first=ques[t].peekLast();
				int fx=first.x, fy=first.y;
				Point next = null;
				for(int dr=0; dr<4; dr++) {
					int nx=fx+dirs[dr][0];
					int ny=fy+dirs[dr][1];
					if(nx<0 || ny<0 || nx>=N || ny>=N || mat[ny][nx]==0) continue;
					if(mat[ny][nx]!=2 && tmat[ny][nx]==t) {
						next=new Point(nx,ny); //젤앞사람의 다음 위치
						break;
					}
				}
				ques[t].addLast(next);
				Point del = ques[t].pollFirst(); //끝 삭제
				mat[del.y][del.x]=-1;
				
				int ord= ques[t].size();
				for(Point cur : ques[t]) {
					int cx=cur.x, cy=cur.y;
					mat[cy][cx]=ord--;
				}
			}
		}
		/*System.out.println("이동후");
		for(int y=0; y<N; y++) {
			System.out.println(Arrays.toString(mat[y]));
		}*/
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer stn;
		stn= new StringTokenizer(br.readLine());
		N=Integer.parseInt(stn.nextToken());
		M=Integer.parseInt(stn.nextToken());
		K=Integer.parseInt(stn.nextToken());
		answer=0;
		
		mat=new int[N][N];
		for(int y=0; y<N; y++) {
			stn=new StringTokenizer(br.readLine());
			for(int x=0; x<N; x++) {
				mat[y][x]=Integer.parseInt(stn.nextToken());
			}
		}
		
		is_fwd=new boolean[M+1];
		ques = new ArrayDeque[M+1];
		
		init();
		move();
		for(int k=0; k<K; k++) {
			
			int r=k%(4*N);
			int r1=(int)(r/N);
			int r2=r%N;
			//System.out.println(k+"라운드!! ");
			throwBall(r1,r2);
			move();
		}
		System.out.println(answer);
		
	}
	
}

