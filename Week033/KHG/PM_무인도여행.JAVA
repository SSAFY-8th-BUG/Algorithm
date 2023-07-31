import java.util.*;

class Solution {
    static char[][] mat;
    static List<Integer> ans;
    static int H,W;
    static boolean[][] visited;
    static int[][] dirs = {{1,0},{0,1},{-1,0},{0,-1}};
    public int[] solution(String[] maps) {
        
        ans = new ArrayList<>();
        H=maps.length;
        W=maps[0].length();
        mat = new char[H][W];
        visited = new boolean[H][W];
        for(int i=0; i<H; i++){
            mat[i] = maps[i].toCharArray();
        }
        for(int y=0; y<H; y++){
            for(int x=0; x<W; x++){
                if(!visited[y][x] && mat[y][x]!='X') bfs(x,y);
            }
        }
        Collections.sort(ans,(o1,o2)->o1-o2);
        if(ans.size()==0)ans.add(-1);
        
        int[] answer = new int[ans.size()];
        for(int i=0; i<ans.size(); i++){
            answer[i]=ans.get(i);
        }
        return answer;
    }
    
    static void bfs(int sx, int sy){
        int sum=mat[sy][sx]-'0';
        Deque<Node> que = new ArrayDeque<>();
        visited[sy][sx]=true;
        que.add(new Node(sx,sy));
        while(!que.isEmpty()){
            Node cur = que.pollFirst();
            int cx = cur.x;
            int cy = cur.y;
            for(int dr=0; dr<4; dr++){
                int nx = cx+dirs[dr][0];
                int ny = cy+dirs[dr][1];
                if(nx<0 || ny<0 || nx>=W || ny>=H || visited[ny][nx]) continue;
                if(mat[ny][nx]=='X')continue;
                visited[ny][nx]=true;
                sum+=mat[ny][nx]-'0';
                que.add(new Node(nx,ny));
            }
        }
        ans.add(sum);
    }
    
    static class Node{
        int x,y;
        Node(int x, int y){
            this.x=x;
            this.y=y;
        }
    }
}