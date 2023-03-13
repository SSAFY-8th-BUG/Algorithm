import java.util.*;
import java.io.*;


public class Main
{
    static int N,T;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    static int[][] canGo = {
        //상우하좌
        //0번인덱스는 진행방향향
        {},
        {1,0,1,2},//상우하
        {0,0,1,3},//상우좌
        {3,0,2,3},//상하좌
        {2,1,2,3},//우하좌
        {1,0,1},//상우
        {0,0,3},//상좌
        {3,2,3},//하좌
        {2,1,2},//우하
        {1,1,2},//우하
        {0,0,1},//상우
        {3,0,3},//상좌
        {2,2,3}//하좌
    };
    static Node[][] map;
    static StringTokenizer st;
    static class Node{
        int x,y;
        int[] arr;
        public Node(){};
        public Node(int x,int y, int[] arr){
            this.x=x;
            this.y=y;
            this.arr = arr;
        }
    }
    public static void main(String args[])throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        map = new Node[N][N];
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                st = new StringTokenizer(br.readLine());
                int arr[] = new int[4]; 
                for(int k=0; k<4; k++){
                    arr[k] = Integer.parseInt(st.nextToken());
                }
                Node node = new Node(i,j,arr);
                map[i][j] = node;
            }
        }
        System.out.println(bfs(0,0));
    }
    public static int bfs(int r, int c){
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][N];
        q.add(new int[]{r,c,0,0});
        visited[r][c] = true;
        int count = 1;

        while(!q.isEmpty()){
            int[] tmp = q.poll();
            if(tmp[2] > T){
                return count;
            }
            int tmpR = tmp[0];
            int tmpC = tmp[1];
            Node node = map[tmpR][tmpC];
            int curLight = node.arr[tmp[2]%4];

            if(tmp[3]==canGo[curLight][0]){
                for(int k=1; k<canGo[curLight].length; k++){
                    int nx = tmpR + dx[canGo[curLight][k]];
                    int ny = tmpC + dy[canGo[curLight][k]];

                    if(nx<0||ny<0||nx>=N||ny>=N) continue;
                    q.add(new int[] {nx,ny,tmp[2]+1,canGo[curLight][k]});
                    if(!visited[nx][ny] && tmp[2]+1 <= T){
                        count++;
                    }
                    visited[nx][ny] = true;
                }
            }
        }
        return count;
    }
}