import java.util.*;
public class PG_우박수열정적분 {
    static int n, x, y;
    static List<Point> list = new ArrayList<>();

    public double[] solution(int k, int[][] ranges) {
        double[] answer = new double[ranges.length];
        n = 0;
        x = 0;
        y = k;
        list.add(new Point(y, x, 0));

        while(y > 1){
            int y1 = y;
            if(y%2 == 0) y = y/2;
            else y = ((3*y) + 1);
            x++;
            n++;
            double w = (double)(y1+y)/2;
            list.add(new Point(y, x, w));
        }

        for(int i=0;i<ranges.length;i++){
            int a = ranges[i][0];
            int b = ranges[i][1];
            b = n + b;
            if(a > b) {
                answer[i] = -1.0;
                continue;
            }
            double sum = 0;
            for(int j=a+1;j<=b;j++){
                sum += list.get(j).w;
            }
            answer[i] = sum;
        }

        return answer;
    }

    static class Point{
        int y,x;
        double w;

        public Point(int y, int x, double w){
            this.y = y;
            this.x = x;
            this.w = w;
        }
    }
}
