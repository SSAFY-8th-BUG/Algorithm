import java.io.*;
import java.util.*;

// 시간복잡도 -> 
// 공간복잡도 -> 
public class boj_17136_tableMinPark {
    static int[][] map;
	static int answer;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st;
		map = new int[10][10];
		for (int y = 0; y < 10; y++) {
			st = new StringTokenizer(br.readLine());
			for (int x = 0; x < 10; x++) map[y][x] = Integer.parseInt(st.nextToken());
		}
		
		answer = Integer.MAX_VALUE;
		
		bt(0, 0, 0);
		
		System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
		br.close();
	}
	
	static int[] p = new int[6];	
	static void bt (int count, int y, int x) {
		if (y == 9 && x > 9) {
			answer = Math.min(answer, count);
			return;
		}	
		
		if (count > answer) return;
		
		if (x > 9) {
			bt(count, y + 1, 0);
			return;
		}
		
		if (map[y][x] == 1) {			
			for (int i = 5; i >= 1; i--) {
				if (!check(y, x, i) || p[i] >= 5) continue;
				
				change(y, x, i, 0);
				p[i]++;		
				
				bt(count + 1, y, x + 1);	

				change(y, x, i, 1);
				p[i]--;
			}		
		} else bt(count, y, x + 1);
	}
	
	static void change(int sy, int sx, int size, int state) {
		for (int y = sy; y < sy + size; y++) {
			for (int x = sx; x < sx + size; x++) {
				map[y][x] = state;
			}
		}
	}
	
	static boolean check(int sy, int sx, int size) {
		for (int y = sy; y < sy + size; y++) {
			for (int x = sx; x < sx + size; x++) {
				if (y < 0 || y >= 10 || x < 0 || x >= 10) return false;
				if (map[y][x] == 0) return false;
			}
		}
		return true;
	}
}
