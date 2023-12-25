import java.util.*;

class Solution {
    static List<Integer>[] tree;
    static int answer,N;
    public int solution(int n, int[][] wires) {
        answer = Integer.MAX_VALUE;
        N=n;
        tree = new List[n+1];
        for(int i=1; i<=n; i++){
            tree[i] = new ArrayList<>();
        }
        for(int[] wire : wires){
            int n1 = wire[0];
            int n2 = wire[1];
            tree[n1].add(n2);
            tree[n2].add(n1);
        }
        for(int[] wire : wires){
            int cnt1 = go(wire[0],wire[1]);
            int cnt2 = go(wire[1],wire[0]);
            int diff = Math.abs(cnt1-cnt2);
            if(diff<answer)answer=diff;
        }
        return answer;
    }
    
    int go(int n1, int n2){
        int cnt=0;
        boolean[] visited = new boolean[N+1];
        Deque<Integer> que = new ArrayDeque<>();
        que.add(n1);
        visited[n1]=true;
        while(!que.isEmpty()){
            cnt++;
            int cn = que.pollFirst();
            for(int nn : tree[cn]){
                if(cn==n1 && nn==n2 || cn==n2 && nn==n1) continue;
                if(visited[nn])continue;
                visited[nn]=true;
                que.add(nn);
            }
        }
        return cnt;
    }
}

