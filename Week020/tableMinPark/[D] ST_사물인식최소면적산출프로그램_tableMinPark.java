import java.util.*;
import java.io.*;


public class ST_사물인식최소면적산출프로그램_tableMinPark
{
    static int N, K, answer;
    static List<List<P>> map;
    static class P {
        int y;
        int x;
        public P(int y, int x){
            this.y = y;
            this.x = x;
        }
    }

    public static void main(String args[]) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new ArrayList<>();
        for (int i = 0; i < K; i++) map.add(new ArrayList<>());

        for (int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken()) - 1;
            map.get(k).add(new P(y, x));
        }

        answer = Integer.MAX_VALUE;
        dfs(0, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE);

        System.out.println(answer);
        br.close();
    }

    static void dfs(int cnt, int minX, int maxX, int minY, int maxY){
        if (cnt == K){
            int a = (maxX - minX) * (maxY - minY);
            if (answer > a) {
                answer = a;
            }
            return;
        }

        for (int i = 0; i < map.get(cnt).size(); i++){
            int y = map.get(cnt).get(i).y;
            int x = map.get(cnt).get(i).x;

            int minXX = minX;
            int maxXX = maxX;
            int minYY = minY;
            int maxYY = maxY;

            if(minX > x) minXX = x;
            if(maxX < x) maxXX = x;
            if(minY > y) minYY = y;
            if(maxY < y) maxYY = y;

            int a = (maxXX - minXX) * (maxYY - minYY);

            if (answer <= a){
                continue;
            }

            dfs(cnt + 1, minXX, maxXX, minYY, maxYY);
        }

    }
}