public class PG_조이스틱 {
    public int solution(String name) {
        int answer = 0;
        int size = name.length();

        int idx = 0;
        // 오른쪽으로 쭉 달리는 경우
        int move = size - 1;

        for(int i=0;i<size;i++){
            answer += Math.min(name.charAt(i) - 'A', 'Z' - name.charAt(i) + 1);
            idx = i + 1;

            // 연속된 A의 개수 체크
            while(idx < size && name.charAt(idx) == 'A'){
                idx++;
            }

            // 오른쪽 갔다가 왼쪽으로 꺾는 경우
            move = Math.min(move, i * 2 + size - idx);

            // 왼쪽으로 갔다가 오른쪽으로 꺾는 경우
            move = Math.min(move, (size - idx) * 2 + i);
        }



        return answer + move;
    }
}
