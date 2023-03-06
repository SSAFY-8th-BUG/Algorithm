import java.util.*;
import java.io.*;


public class ST_로봇이지나간경로_tableMinPark
{
    static int a, b;
    static char[][] map;
    static boolean[][] v;
    static List<Integer> dir;
    static int[] dy = {0, -1, 0 ,1};
    static int[] dx = {-1, 0, 1, 0};
    static char[] dc = {'<', '^', '>', 'v'};

    public static void main(String args[]) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());

        map = new char[a][b];
        for (int y = 0; y < a; y++){
            map[y] = br.readLine().toCharArray();
        }

LOOP:   for (int y = 0; y < a; y++){
            for (int x = 0; x < b; x++){
                if (map[y][x] == '#'){
                    int c = 0;
                    int now = -1;
                    for (int i = 0; i < 4; i++){
                        int ny = y + dy[i];
                        int nx = x + dx[i];
                        if (ny < 0 || ny >= a || nx < 0 || nx >= b || map[ny][nx] == '.') continue;
                        now = i;
                        c++;
                    }
                    // 길이 한쪽밖에 없는 경우 (= 시작점)
                    if (c == 1){
                        sb.append(y + 1).append(" ").append(x + 1).append("\n").append(dc[now]).append("\n");

                        dir = new ArrayList<>();
                        v = new boolean[a][b];

                        v[y][x] = true;
                        dfs(y, x);

                        for (int i = 0; i < dir.size(); i++){    
                            int next = dir.get(i);     
                            // System.out.print(dc[next] + " ");                   
                            if (now == next) {
                                sb.append("A");
                                i++;
                                continue;
                            } else{
                                if ((now + 1) % 4 == next){
                                    sb.append("R");
                                } else {
                                    sb.append("L");
                                }
                                sb.append("A");
                                i++;
                            }
                            now = next;
                        }
                        break LOOP;
                    }
                }
            }
        }

        System.out.println(sb.toString());
        br.close();
    }

    static void dfs(int y, int x){
        for (int i = 0; i < 4; i++){
            int ny = y + dy[i];
            int nx = x + dx[i];

            if (ny < 0 || ny >= a || nx < 0 || nx >= b || v[ny][nx] || map[ny][nx] == '.') continue;

            dir.add(i);
            v[ny][nx] = true;
            dfs(ny, nx);
        }
    }
}