import java.util.*;
public class PG_무인도여행 {
    static int N,M;
    static boolean[][] visited;
    static int[][] map;
    static List<Node> land = new ArrayList<>();
    static List<Integer> ans = new ArrayList<>();

    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};

    public int[] solution(String[] maps) {

        N = maps.length;
        M = maps[0].length();

        map = new int[N][M];
        visited = new boolean[N][M];

        for(int i=0;i<N;i++){
            char[] c = maps[i].toCharArray();
            for(int j=0;j<M;j++){
                if(c[j] != 'X'){
                    map[i][j] = c[j] - '0';
                    land.add(new Node(i, j));
                }else{
                    map[i][j] = 0;
                }
            }
        }

        for(int i=0;i<land.size();i++){
            Node n = land.get(i);
            if(visited[n.y][n.x]) continue;
            // 방문 안햇었으면 bfs 돌리기
            int cnt = bfs(n);
            ans.add(cnt);
        }

        if(ans.isEmpty() || ans.size()==0){
            int[] answer = new int[1];
            answer[0] = -1;
            return answer;
        }

        int[] answer = new int[ans.size()];
        Collections.sort(ans);
        for(int i=0;i<ans.size();i++){
            answer[i] = ans.get(i);
        }

        return answer;
    }

    static int bfs(Node n){
        int cnt = 0;
        visited[n.y][n.x] = true;
        Queue<Node> q = new ArrayDeque<>();
        q.add(n);

        while(!q.isEmpty()){
            Node node = q.poll();
            cnt += map[node.y][node.x];

            for(int i=0;i<4;i++){
                int ny = node.y + dy[i];
                int nx = node.x + dx[i];

                if(ny<0 | nx<0 | ny>=N | nx>=M) continue;
                if(visited[ny][nx]) continue;
                visited[ny][nx] = true;
                if(map[ny][nx] != 0){
                    q.add(new Node(ny,nx));
                }
            }
        }

        return cnt;
    }

    static class Node{
        int y,x;

        public Node(int y, int x){
            this.y = y;
            this.x = x;
        }
    }
}
