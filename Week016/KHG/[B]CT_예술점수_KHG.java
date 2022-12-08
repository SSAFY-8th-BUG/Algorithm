import java.awt.Point;
import java.io.BufferedReader; 
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int answer;
	static int N,N2, totalG; // 넓이,넓이/2, 그룹갯수
	static int[][] mat; //실제 mat
	static int[][] gmat; //새로 그룹지은 mat
	static List<Integer>group2num; //그룹의 실제 번호
	static List<Integer>group2cnt; //그룹별 갯수
	static int[][] dirs= {{1,0},{0,1},{-1,0},{0,-1}};
	static int[][] matches; //그룹간 붙는 변 갯수
	
	static void init() {
		boolean[][] visited=new boolean[N][N];
		int[][] nmat=new int[N][N];
		group2num=new ArrayList<>();
		group2num.add(0);
		group2cnt=new ArrayList<>();
		group2cnt.add(0);
		totalG=0; //그룹의 총수
		for(int y=0; y<N; y++) { //그룹을 새로 구분짓고 갯수 셈
			for(int x=0; x<N; x++) {
				if(visited[y][x]) continue;
				visited[y][x]=true;
				int g=mat[y][x];
				int cnt=0;
				totalG++;
				group2num.add(mat[y][x]);
				Deque<Point> que = new ArrayDeque<>();
				que.add(new Point(x,y));
				while(!que.isEmpty()) {
					Point cur = que.pollFirst();
					int cx=cur.x, cy=cur.y;
					cnt++;
					nmat[cy][cx]=totalG;
					for(int dr=0; dr<4;dr++) {
						int nx=cx+dirs[dr][0];
						int ny=cy+dirs[dr][1];
						if(nx<0 || ny<0 || nx>=N || ny>=N || visited[ny][nx] || mat[ny][nx]!=g) continue;
						visited[ny][nx]=true;
						
						que.add(new Point(nx,ny));
					}
				}
				group2cnt.add(cnt);
			}
		}
		gmat=nmat;
		
		
		matches=new int[totalG+1][totalG+1];
		for(int y=0; y<N; y++) {  //그룹간 매칭 수 세기
			for(int x=0; x<N; x++) {
				int g=gmat[y][x];
				for(int dr=0; dr<4; dr++) {
					int nx=x+dirs[dr][0];
					int ny=y+dirs[dr][1];
					if(nx<0 || ny<0 || nx>=N || ny>=N) continue;
					if(gmat[ny][nx]!=g) {
						matches[g][gmat[ny][nx]]++;
					}
				}
			}
		}	
	}
	
	static void cal() { //점수계산
		int total=0;
		for(int i=1; i<totalG; i++) {
			for(int j=i+1; j<=totalG; j++) {
				total+=(group2cnt.get(i)+group2cnt.get(j))*matches[i][j]*group2num.get(i)*group2num.get(j);
			}
		}
		answer+=total;
	}
	
	static void turnLeft() { //십자회전
		int[] row= mat[N2].clone();
		
		for(int x=0; x<N; x++) {
			mat[N2][x]=mat[x][N2];
		}
		for(int y=N-1; y>=0; y--) {
			mat[y][N2]=row[N-1-y];
		}
	}
	
	static void turnRight(int sx, int sy,int[][] nmat){ //사분면 하나 회전
		for(int y=0; y<N2; y++) {
			for(int x=0; x<N2; x++) {
				nmat[sy+y][sx+x]=mat[sy+N2-1-x][sx+y];
			}
		}
	}
	
	static void turnAll() { //모두 회전
		turnLeft(); //십자 회전
		int[][] nmat=new int[N][N];
		for(int y=0; y<N; y++) {
			nmat[y]=mat[y].clone();
		}
		//사분면 모두 회전
		turnRight(0,0,nmat);
		turnRight(0,N2+1,nmat);
		turnRight(N2+1,0,nmat);
		turnRight(N2+1,N2+1,nmat);
		mat=nmat;
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N=Integer.parseInt(br.readLine());
		N2= (int)N/2;
		StringTokenizer stn;
		mat=new int[N][N];
		for(int y=0; y<N; y++) {
			stn= new StringTokenizer(br.readLine());
			for(int x=0; x<N; x++) {
				mat[y][x]=Integer.parseInt(stn.nextToken());
			}
		}
		answer=0;
		
		init();
		cal();
		turnAll();
		
		init();
		cal();
		turnAll();
		
		init();
		cal();
		turnAll();
		
		init();
		cal();
		System.out.println(answer);
		
	}
	
}

