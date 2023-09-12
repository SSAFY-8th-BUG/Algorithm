import java.util.*;

class PG_풍선터트리 {
    public int solution(int[] a) {
        int answer = 2;
        int leftMin = a[0];
        int rightMin = a[a.length - 1];

        if (a.leng기h == 1) {
            return 1;
        }

        for (int i = 1; i < a.length - 1; i++) {
            // 왼쪽의 최댓값이 현재 값보다 큰 경우 (남기는 것 가능)
            if (leftMin > a[i]) {
                leftMin = a[i];
                answer++;
            }
            // 오른쪽의 최댓값이 현재 값보다 큰 경우 (남기는 것 가능)
            if (rightMin > a[a.length - 1 - i]) {
                rightMin = a[a.length - 1 - i];
                answer++;
            }
            // 배열의 크기가 홀수라 중간에 만나는 경우 answer 1감소 및 반복문 중지
            // 모든 수는 다 다르다고 했으므로 중간에 만나는 것말고 같을 경우는 없음
            if (leftMin == rightMin) {
                answer--;
                break;
            }
        }

        return answer;
    }
}
