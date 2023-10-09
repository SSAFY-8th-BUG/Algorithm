package studygroup.Week043;

public class PG_조이스틱 {
    public int solution(String name) {
        int answer = 0;
        int len = name.length();
        int move = len - 1;

        for(int i = 0; i < len; i++) {
            char c = name.charAt(i);
            answer += upDown(c);

            int index = i + 1;
            while(index < len && name.charAt(index) == 'A') {
                index++;
            }
            move = Math.min(move, (i * 2) + (len - index));
            move = Math.min(move, (len - index) * 2 + i);
        }

        return answer + move;
    }
    public int upDown(char c) {
        int num = c - 'A';
        return num > 12 ? 26 - num : num;
    }
}
