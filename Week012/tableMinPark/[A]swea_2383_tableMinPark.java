import java.io.*;
import java.util.*;

// 비트마스크
// 돌리는 함수하나
public class swea_2383_tableMinPark {
    static PriorityQueue<Integer> s1, s2;
    static List<P> person, stair;
    static int T, N, PS, answer;
    static int[][] map;
    static class P {
        int y;
        int x;
        public P(int y, int x){
            this.y = y;
            this.x = x;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());

        StringTokenizer st;
        StringBuilder sb = new StringBuilder();


        for (int t = 1; t <= T; t++){
            N = Integer.parseInt(br.readLine());
            
            person = new ArrayList<>();
            stair = new ArrayList<>();
            map = new int[N][N];
            for (int y = 0; y < N; y++){
                st = new StringTokenizer(br.readLine());
                for (int x = 0; x < N; x++){
                    map[y][x] = Integer.parseInt(st.nextToken());
                    if (map[y][x] == 1) person.add(new P(y, x));
                    else if (map[y][x] != 0) stair.add(new P(y, x));
                }
            }

            s1 = new PriorityQueue<>((n1, n2) -> n1 - n2);
            s2 = new PriorityQueue<>((n1, n2) -> n1 - n2);
            answer = Integer.MAX_VALUE;

            PS = person.size();
            // 비트마스킹으로 부분집합
            for (int mask = 0; mask < 1 << PS; mask++){
                P S1 = stair.get(0);
                P S2 = stair.get(1);
                for (int i = 0; i < PS; i++){
                    P Per = person.get(i);

                    // 1번 계단 이용
                    if ((mask & 1 << i) != 0) s1.add(Math.abs(Per.y - S1.y) + Math.abs(Per.x - S1.x));
                    // 2번 계단 이용
                    else s2.add(Math.abs(Per.y - S2.y) + Math.abs(Per.x - S2.x));
                }

                // 계단 두개 따로돌려서 더 오래걸린 시간을 찾음
                int min = Math.max(solve(s1, map[S1.y][S1.x]), solve(s2, map[S2.y][S2.x]));
                // 오래걸린 시간 중에서 가장 짧은 것
                answer = Math.min(answer, min);
            }
            sb.append("#").append(t).append(" ").append(answer).append("\n");            
        }

        System.out.println(sb.toString());
        br.close();
    }

    // 계단 내려가는 함수
    static int solve(PriorityQueue<Integer> s, int stairTime){
        int time = 0;

        Queue<Integer> wait = new ArrayDeque<>();

        while (true){
            for (int i = 0, size = wait.size(); i < size; i++){
                int now = wait.poll();
                // 아직 내려가는중이면 + 1
                if (now < stairTime) wait.offer(now + 1);
                // 시간이 다되면 나가리
            }

            while (!s.isEmpty() && s.peek() < time && wait.size() < 3){
                // 계단에 자리가 남았고, 도착해버린친구들 계단 대기열에 넣음
                s.poll();
                wait.add(1);
            }
            
            if (s.isEmpty() && wait.isEmpty()) break;
            time++;
        }

        return time;
    }
}