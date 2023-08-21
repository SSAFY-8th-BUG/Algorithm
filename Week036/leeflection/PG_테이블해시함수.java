package studygroup.Week032;

import java.util.Arrays;

public class PG_테이블해시함수 {
    public int solution(int[][] data, int col, int row_begin, int row_end) {
        int answer = 0;

        Arrays.sort(data, (o1, o2)->{
            if(o1[col-1] == o2[col-1]){
                return o2[0] - o1[0];
            }else{
                return o1[col-1] - o2[col-1];
            }
        });

        int range[] = new int[row_end - row_begin +1];
        int idx = 0;
        for(int i=row_begin-1; i<=row_end-1; i++){
            int sum = 0;
            for(int j=0; j<data[0].length; j++){
                sum += data[i][j] % (i+1);
            }
            range[idx] = sum;
            idx++;
        }
        int a = range[0];
        for(int i=1; i<range.length; i++){
            a ^= range[i];
        }
        return a;
    }
}
