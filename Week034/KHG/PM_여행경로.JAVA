import java.util.*;

class Solution {
    static Map<String,List<Node>> graph;
    static boolean[] visited;
    static int len;
    static boolean isEnd;
    static String[] answer;
    public String[] solution(String[][] tickets) {
        
        graph = new HashMap<>();
        
        len = tickets.length+1;
        visited = new boolean[len];
        
        for(int i=0; i<len-1; i++){ //그래프 생성
            String[] ticket = tickets[i];
            if(graph.containsKey(ticket[0])){
                graph.get(ticket[0]).add(new Node(i,ticket[1]));
            }else{
                graph.put(ticket[0], new ArrayList<>());
                graph.get(ticket[0]).add(new Node(i,ticket[1]));
            }
        }
        
        for(String key : graph.keySet()){ //이름 순정렬
            Collections.sort(graph.get(key), (o1,o2)->o1.name.compareTo(o2.name));
        }
        answer = new String[len];
        answer[0] = "ICN";
        rec("ICN", 1);
        
        return answer;
    }
    
    static void rec(String cur, int num){
        if(isEnd)return;
        if(num==len){
            isEnd = true;
            //System.out.println(Arrays.toString(answer));
            return;
        }
        if(!graph.containsKey(cur)) return;
        for(Node next : graph.get(cur)){
            if(visited[next.idx]) continue;
            if(isEnd)break;
            
            visited[next.idx]=true;
            answer[num] = next.name;
            rec(next.name, num+1);
            visited[next.idx]=false;
        }
        
    }
    
    static class Node{
        int idx;
        String name;
        Node(int idx, String name){
            this.idx = idx;
            this.name = name;
        }
    }
}