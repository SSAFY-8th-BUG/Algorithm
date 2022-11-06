package week015;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/*
 * 보고풀엇음
 * */
// 매직스타
public class C_boj_3967_bnuri00 {
	
	static final int dy[][] = {{1,1,1,1},{3,3,3,3},{0,1,2,3},{0,1,2,3},{1,2,3,4},{1,2,3,4}};
	static final int dx[][] = {{1,3,5,7},{1,3,5,7},{4,3,2,1},{4,5,6,7},{1,2,3,4},{7,6,5,4}};
	static char[][] input = new char[5][];
	static boolean[] used = new boolean[13];
	
	static ArrayList<Point> list = new ArrayList<>();
	static int end;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int i = 0; i < 5; i++) {
			input[i] = br.readLine().toCharArray();
		}
		
		initStar();
		end = list.size();
		
		dfs(0);
	}
	private static void dfs(int idx) {
		if(idx==end) {
			if(check()) {
			for(int i = 0 ; i < 5 ; i++) {
					for(int j = 0 ; j < 9 ; j++) {
						System.out.print(input[i][j]);
					}
					System.out.println();
				}
				System.exit(0);
			}
		}
		
		for (int i = 1; i <= 12; i++) {
			if(!used[i]) {
				used[i] = true;
				Point p = list.get(idx);
				input[p.y][p.x] = (char) ('A'+i-1);
				dfs(idx+1);
				used[i] = false;
			}
		}
		
	}
	static boolean check() {
		for (int i = 0; i < 6; i++) {
			int sum = 0;
			for (int j = 0; j < 4; j++) {
				int y = dy[i][j];
				int x = dx[i][j];
				sum += input[y][x]-'A'+1;
			}
			if(sum != 26) return false;
		}
		return true;
	}
	private static void initStar() {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 9; j++) {
				if(input[i][j]=='.') continue;
				else if(input[i][j]=='x') list.add(new Point(j, i));
				else used[input[i][j]-'A'+1] = true;
			}
		}
		
	}
}
