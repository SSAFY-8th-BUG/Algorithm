import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 거의 내가 안풀엇음, 완탐을 못했다
 * 
 * <풀이방식>
 * - bfs, 맵 활용
 * 
 * - 이전과 같은 순서로 배열되면 해당 위치에서 더 탐색할 필요 없다 (맵 활용)
 * - 메모리 이슈로 배열 대신 string, int 이용
 * - 인덱스 이렇게 사용!
 * 		{
 * 			0 1 2
 * 			3 4 5
 * 			6 7 8
 * 		}
 * 
 * */

// boj 퍼즐
public class Bboj_1525_bnuri00 {
	
	static int input = 0;
	
	static Queue<Integer> q = new ArrayDeque<>();
	static Map<Integer, Integer> map = new HashMap<>();
	
  
    static int[] dy = {1, -1, 0, 0};
	static int[] dx = {0, 0, 1, -1};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for (int i = 0; i < 3; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				int n = Integer.parseInt(st.nextToken());
				if(n == 0) n = 9;	// 0 사용하면 int에서 문제생김.. -> 9로 대체
				
				input = input*10 + n;
			}
		}
		
		if(input == 123456789) {
			System.out.println(0);
			return;
		}
		
		System.out.println(bfs());
		
		
	}


	static int bfs() {
		
        map.put(input, 0);
        q.add(input);
        
        while(!q.isEmpty()) {
            int currN = q.poll();
            String curr= String.valueOf(currN); 
            
            int empty = curr.indexOf("9"); // 빈곳 인덱스
            int x = empty / 3;
            int y = empty % 3;
            
            for(int i=0; i<4; i++) {
                int nx = x+dx[i]; 
                int ny = y+dy[i];
                
                int move = nx*3+ny; 
                
                if (nx < 0 || nx > 2 || ny < 0 || ny > 2 ) continue;
               
                StringBuilder next = new StringBuilder(curr);
                
                //move와 empty swap
                char temp = next.charAt(move);
                next.setCharAt(move, '9'); 
                next.setCharAt(empty, temp); 
                
                int nextN = Integer.parseInt(next.toString());
                
                if(!map.containsKey(nextN)) { //맵에 몇 번이동했는지 저장
                    map.put(nextN, map.get(currN)+1);
                    q.add(nextN);
                }
                
            }
        }
    
       
       if(map.containsKey(123456789)) return map.get(123456789);
       
      return -1;
	}
}
