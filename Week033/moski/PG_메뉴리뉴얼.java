import java.util.*;
public class PG_메뉴리뉴얼 {
    static char[] com;
    static List<Character> cook;
    static List<String> ans;
    static boolean[] visited;
    static PriorityQueue<Course> pq = new PriorityQueue<>((o1,o2) -> o2.cnt - o1.cnt);
    static HashMap<String, Integer> map = new HashMap<>();


    public String[] solution(String[] orders, int[] course) {

        // 어짜피 최대 10개라
        com = new char[11];

        // 이방식은 시간초과군
//         Set<Character> ingredient = new HashSet<>();
//         ans = new ArrayList<>();

//         for(int i=0;i<orders.length;i++){
//             char[] c = orders[i].toCharArray();
//             for(int j=0;j<c.length;j++){
//                 ingredient.add(c[j]);
//             }
//         }

//         cook = new ArrayList<>(ingredient);
//         visited = new boolean[cook.size()];

//         for(int i=0; i<course.length; i++){
//             pq.clear();
//             combi(orders, 0, course[i], 0);
//             if(!pq.isEmpty()){
//                 Course maxCourse = pq.poll();
//                 int maxCnt = maxCourse.cnt;
//                 ans.add(maxCourse.course);

//                 while(!pq.isEmpty()){
//                     Course c = pq.poll();
//                     if(maxCnt > c.cnt) break;
//                     ans.add(c.course);
//                 }
//             }

//         }

        for (int i = 0; i < orders.length; i++) {
            char[] arr = orders[i].toCharArray();
            Arrays.sort(arr);
            orders[i] = String.valueOf(arr);
        }

        ans = new ArrayList<>();

        for(int leng : course){
            for(String order : orders){
                char[] c = order.toCharArray();
                visited = new boolean[c.length];
                combi(c, 0, leng, 0);
            }
            if(!map.isEmpty()){
                List<Integer> count = new ArrayList<>(map.values());
                int max = Collections.max(count);
                if(max > 1){
                    for(String key : map.keySet()){
                        if(map.get(key) == max){
                            ans.add(key);
                        }
                    }
                }
                map.clear();
            }
        }

        Collections.sort(ans);

        String[] answer = new String[ans.size()];
        for(int i=0;i<ans.size();i++){
            answer[i] = ans.get(i);
        }
        return answer;
    }

    static void combi(char[] order, int now, int idx, int start){
        if(now == idx){
            StringBuilder sb = new StringBuilder();
            for(int i=0;i<idx;i++){
                sb.append(com[i]);
            }
            String str = sb.toString();
            map.put(str, map.getOrDefault(str, 0) + 1);
            return;
        }

        for(int i=start;i<order.length;i++){
            if(visited[i]) continue;
            com[now] = order[i];
            visited[i] = true;
            combi(order, now+1, idx, i+1);
            visited[i] = false;

        }

    }

//     static void combi(String order, int now, int idx, int start){
//         if(now == idx){
//             int check = 0;

//             // 생성된 배열이 겹치는지 판단
//             for(int i=0; i<orders.length; i++){
//                 int cnt = 0;
//                 for(int j=0;j<idx;j++){
//                     if(orders[i].contains(Character.toString(com[j]))){
//                         cnt++;
//                     }
//                 }
//                 if(cnt == idx){
//                     check++;
//                 }
//             }
//             if(check >= 2){
//                 StringBuilder sb = new StringBuilder();
//                 for(int i=0;i<idx;i++){
//                     sb.append(com[i]);
//                 }
//                 pq.add(new Course(sb.toString(), check));
//             }
//             return;
//         }

//         for(int i=start;i<cook.size();i++){
//             if(visited[i]) continue;
//             com[now] = cook.get(i);
//             visited[i] = true;
//             combi(orders, now+1, idx, i+1);
//             visited[i] = false;

//         }

//     }

    static class Course{
        String course;
        int cnt;

        public Course(String course, int cnt){
            this.course = course;
            this.cnt = cnt;
        }
    }
}
