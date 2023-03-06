package softeer.안전운전을도와줄차세대지능형교통시스템_1차;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main
{
    static int N, T;
    static int[][][] map;
    static Signal[] signals;
    static Set<Intersection> intersections;
    
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    
    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        signals = new Signal[13];
        map = new int[N][N][4];

        intersections = new HashSet<>();
        
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                st = new StringTokenizer(br.readLine());
                for(int k=0; k<4; k++){
                    map[i][j][k] = Integer.parseInt(st.nextToken());
                }
            }
        }

        for (int i = 1; i < 13; i++) {
         signals[i] = new Signal();
         
         switch(i) {
         case 1:
            signals[i].startD = 3;
            signals[i].directions.add(0);
            signals[i].directions.add(3);
            signals[i].directions.add(1);
            break;
         case 2:
            signals[i].startD = 0;
            signals[i].directions.add(2);
            signals[i].directions.add(0);
            signals[i].directions.add(3);
            break;
         case 3:
            signals[i].startD = 2;
            signals[i].directions.add(0);
            signals[i].directions.add(2);
            signals[i].directions.add(1);
            break;
         case 4:
            signals[i].startD = 1;
            signals[i].directions.add(2);
            signals[i].directions.add(1);
            signals[i].directions.add(3);
            break;
         case 5:
            signals[i].startD = 3;
            signals[i].directions.add(0);
            signals[i].directions.add(3);
            break;
         case 6:
            signals[i].startD = 0;
            signals[i].directions.add(2);
            signals[i].directions.add(0);
            break;
         case 7:
            signals[i].startD = 2;
            signals[i].directions.add(2);
            signals[i].directions.add(1);
            break;
         case 8:
            signals[i].startD = 1;
            signals[i].directions.add(1);
            signals[i].directions.add(3);
            break;
         case 9:
            signals[i].startD = 3;
            signals[i].directions.add(3);
            signals[i].directions.add(1);
            break;
         case 10:
            signals[i].startD = 0;
            signals[i].directions.add(0);
            signals[i].directions.add(3);
            break;
         case 11:
            signals[i].startD = 2;
            signals[i].directions.add(0);
            signals[i].directions.add(2);
            break;
         case 12:
            signals[i].startD = 1;
            signals[i].directions.add(2);
            signals[i].directions.add(1);
            break;
         }
      }
        
        bfs(0,0,0,0);
        
        System.out.println(intersections.size());

    }
    
    static void bfs(int t, int D, int y, int x) {
       Queue<Car> q = new ArrayDeque<>();
       q.add(new Car(t,D,y,x));
       
       while(!q.isEmpty()) {
          Car c = q.poll();
          if(c.T > T) break;
          intersections.add(new Intersection(c.y, c.x));
          Signal s = signals[map[c.y][c.x][c.T%4]];
          
          if(c.D == s.startD) {
             for (int i = 0; i < s.directions.size(); i++) {
                int d = s.directions.get(i);
                int ny = c.y + dy[d];
                int nx = c.x + dx[d];
                
                if(ny < 0 || nx < 0 || ny >= N || nx >= N) continue;
                
                q.add(new Car(c.T+1, d, ny, nx));
             }
          }
       }
    }
    
    static class Signal{
       int startD;
       List<Integer> directions = new ArrayList<>();
    }
    
    static class Intersection{
       int y, x;
       
       public Intersection(int y, int x) {
          this.y = y;
          this.x = x;
       }
       
       @Override
        public boolean equals(Object obj) {
            if (this.getClass() != obj.getClass()) return false;
            return (((Intersection) obj).x == this.x) && (((Intersection) obj).y == this.y);
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
    
    static class Car {
       int T;
       int D;
       int y, x;
       
       public Car(int T, int D, int y, int x) {
          this.T = T;
          this.D = D;
          this.x = x;
          this.y = y;
       }
    }
}