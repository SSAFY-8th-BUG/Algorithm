import java.util.*;
public class PG_경주로건설 {
    static int N;
    static boolean[][] visited;
    static int[][][] cost;
    // W = G(출발지로 부터의 도로 비용) + H(도착지까지의 예상 도로 비용)
    // 0 : y, 1: x, 2 : W = G + H, 3 : 이전 방향, 4 : G
    static Queue<int[]> q = new ArrayDeque<>();

    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};

    public int solution(int[][] board) {
        // A*로 시작해보자. - 실패
        //
        // 직선은 100원 코너는 500원
        int answer = 0;

        N = board.length;
        cost = new int[N][N][4];

        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                Arrays.fill(cost[i][j], Integer.MAX_VALUE);
            }
        }

        q.add(new int[]{0,0,0,1});
        q.add(new int[]{0,0,0,3});

        while(!q.isEmpty()){

            int[] node = q.poll();
            // System.out.println("y : " + node[0] + " x : " + node[1] + " G : " + node[4] + " W : " + node[2]);

            //step 2. 주변 노드 상태에 따라 변경
            for(int d=0;d<4;d++){
                int ny = node[0] + dy[d];
                int nx = node[1] + dx[d];
                int c = node[2] + (node[3] == d ? 100 : 600);
                if(ny<0 || nx<0 || ny>=N || nx>=N) continue;
                if(cost[ny][nx][d] <= c) continue;

                if(board[ny][nx] == 0){
                    cost[ny][nx][d] = c;
                    q.add(new int[]{ny, nx, c, d});
//                     int G = 0;
//                     int H = 0;
//                     if(node[3] == -1){
//                         G = 100;
//                     }else if(node[3] == d){
//                         G = node[4] + 100;
//                     }else{
//                         G = node[4] + 600;
//                     }

//                     if(ny == N-1 && nx == N-1){
//                         H = 0;
//                     }else if(ny == N-1 && nx != N-1){
//                         H = 100*(N-1-nx);
//                     }else if(nx == N-1 && ny != N-1){
//                         H = 100*(N-1-ny);
//                     }else {
//                         H = 100*(N-1-ny) + 500 + 100*(N-1-nx);
//                     }
//                     if(cost[ny][nx] > G+H){
//                         pq.add(new int[]{ny, nx, G+H, d, G});
//                     }
                }
            }
        }
        answer = Integer.MAX_VALUE;
        for(int i=0;i<4;i++){
            answer = Math.min(answer, cost[N-1][N-1][i]);
        }
        return answer;
    }
}
