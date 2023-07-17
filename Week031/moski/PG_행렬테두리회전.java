import java.util.*;

public class PG_행렬테두리회전 {
    static int[][] map;

    public int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = new int[queries.length];
        map = new int[rows][columns];
        int v = 1;
        for(int i=0; i<rows; i++){
            for(int j=0; j<columns; j++){
                map[i][j] = v;
                v++;
            }
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int idx = 0;
        for(int[] query : queries){
            pq.clear();
            int x1 = query[0]-1;
            int y1 = query[1]-1;
            int x2 = query[2]-1;
            int y2 = query[3]-1;
            int firstNum = map[x1][y2];
            pq.add(firstNum);

            // 우로 이동
            for(int i=y2-1; i>=y1; i--){
                pq.add(map[x1][i]);
                map[x1][i+1] = map[x1][i];
            }

            // 위로 이동
            for(int i=x1+1; i<=x2; i++){
                pq.add(map[i][y1]);
                map[i-1][y1] = map[i][y1];
            }

            // 좌로 이동
            for(int i=y1+1; i<=y2; i++){
                pq.add(map[x2][i]);
                map[x2][i-1] = map[x2][i];
            }

            // 아래로 이동
            for(int i=x2-1; i>=x1; i--){
                pq.add(map[i][y2]);
                map[i+1][y2] = map[i][y2];
            }

            map[x1+1][y2] = firstNum;
            answer[idx] = pq.poll();
            idx++;
        }

        return answer;
    }
}
