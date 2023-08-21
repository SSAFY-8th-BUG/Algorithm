package Week036.moski;

import java.util.*;
public class PG_하노이의탑 {
    static List<FromTo> list = new ArrayList<>();

    public int[][] solution(int n) {
        hanoi(n, 1, 3, 2);

        int[][] answer = new int[list.size()][2];

        for(int i=0; i<list.size(); i++){
            FromTo ft = list.get(i);
            answer[i][0] = ft.from;
            answer[i][1] = ft.to;
        }

        return answer;
    }

    static void hanoi(int n, int from, int to, int other){
        FromTo ft = new FromTo(from, to);

        if(n == 1){
            list.add(ft);
        }else{
            hanoi(n-1, from, other, to);
            list.add(ft);
            hanoi(n-1, other, to, from);
        }
    }

    static class FromTo{
        int from;
        int to;

        public FromTo(int from, int to){
            this.from = from;
            this.to = to;
        }
    }
}
