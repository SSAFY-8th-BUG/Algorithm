package Week034.moski;

import java.util.*;
public class PG_여행경로 {
    // 인터넷 참고
    static boolean[] used;
    static List<String> list = new ArrayList<>();

    public String[] solution(String[][] tickets) {

        used = new boolean[tickets.length];

        dfs(0, "ICN", "ICN", tickets);

        Collections.sort(list);

        return list.get(0).split(" ");
    }

    static void dfs(int depth, String now, String path, String[][] tickets){
        if (depth == tickets.length) {
            list.add(path);
            return;
        }

        for (int i = 0; i < used.length; i++) {
            if (!used[i] && now.equals(tickets[i][0])) {
                used[i] = true;
                dfs(depth+1, tickets[i][1], path + " " +tickets[i][1], tickets);
                used[i] = false;
            }
        }
    }
}
