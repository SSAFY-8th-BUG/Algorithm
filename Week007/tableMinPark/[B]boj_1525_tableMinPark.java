import java.io.*;
import java.util.*;

// 퍼즐
public class boj_1525_tableMinPark {

    static int answer;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;
        
        String input = "";
        for (int y = 0; y < 3; y++){
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < 3; x++){
                input += st.nextToken();
            }
        }

        System.out.println(input);
        answer = Integer.MAX_VALUE;
        bfs(input);

        br.close();
    }

    static int[] dy = {0, 0, -1, 1};
    static int[] dx = {-1, 1, 0, 0};

    static void bfs(String input){
        Queue<String> q = new ArrayDeque<>();
        Map<String, Integer> v = new HashMap<>();

        q.offer(input);
        v.put(input, 0);

        while(!q.isEmpty()){
            String now = q.poll();

            int zero = now.indexOf("0");
            int y = zero / 3;
            int x = zero % 3;

            for (int i = 0; i < 4; i++){
                int ny = y + dy[i];
                int nx = x + dx[i];
                int nm = ny * 3 + nx;

                if (ny < 0 || ny >= 3 || nx < 0 || nx >= 3) continue;
                
                StringBuilder next = new StringBuilder(now);
                char temp = next.charAt(nm);
                next.setCharAt(zero, temp);
                next.setCharAt(nm, '0');

                if (!v.containsKey(next.toString())){
                    q.offer(next.toString());
                    v.put(next.toString(), v.get(now) + 1);
                }
            }
        }

        if (v.containsKey("123456780")) answer = v.get("123456780");
        else answer = -1;
    }
}
