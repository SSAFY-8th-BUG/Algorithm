package studygroup.Week043;

public class PG_큰수만들기 {
    public String solution(String number, int k) {
        StringBuilder sb = new StringBuilder();
        //큰수를 찾은 이후 탐색범위
        int index = 0;
        int max = 0;
        for(int i=0; i<number.length() - k; i++) {
            max = 0;
            for(int j = index; j<= k+i; j++) {
                if(max < number.charAt(j)-'0') {
                    max = number.charAt(j)-'0';
                    index = j+1;
                }
            }
            sb.append(max);
        }
        return sb.toString();
    }
}
