import java.util.*;
public class PG_테이블해시함수 {
    public int solution(int[][] data, int col, int row_begin, int row_end) {

        int answer = 0;
        // int[] S = new int[data.length];
        // col을 기준으로 정렬
        Arrays.sort(data, (o1, o2)->o1[col-1] == o2[col-1] ? o2[0] - o1[0] : o1[col-1] - o2[col-1]);

        for(int i=row_begin-1; i< row_end;i++){
            int n = 0;
            for(int j=0;j<data[0].length;j++){
                n += data[i][j] % (i+1);
            }
            answer ^= n;
        }


        return answer;
    }
}
