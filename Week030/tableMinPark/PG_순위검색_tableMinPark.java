import java.util.*;

// 이분탐색
// map (key-value)
class Solution {
    static Map<String, List<Integer>> map;
    static int score;
    static String[] src;
    static String[] target;

    public int[] solution(String[] infos, String[] query) {
        int[] answer = new int[query.length];

        map = new HashMap<>();
        target = new String[4];

        for (String info : infos) {
            src = info.split(" ");
            score = Integer.parseInt(src[4]);
            solve(0);
        }

        for (String key : map.keySet())
            Collections.sort(map.get(key));

        for (int i = 0; i < query.length; i++) {
            String q = query[i];
            String[] temp = q.replace(" and ", "").split(" ");
            String key = temp[0];

            if (!map.containsKey(key)) {
                answer[i] = 0;
                continue;
            }

            int nowScore = Integer.parseInt(temp[1]);
            List<Integer> list = map.get(key);


            int tail = 0;
            int head = list.size() - 1;

            while(tail <= head){
                int mid = (tail + head) / 2;
                if (list.get(mid) < nowScore) {
                    tail = mid + 1;
                } else {
                    head = mid - 1;
                }
            }
            answer[i] = list.size() - tail;

        }

        return answer;
    }

    static void solve(int depth) {
        if (depth == 4) {
            String key = String.join("", target);

            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }

            map.get(key).add(score);
            return;
        }
        target[depth] = src[depth];
        solve(depth + 1);
        target[depth] = "-";
        solve(depth + 1);
    }
}