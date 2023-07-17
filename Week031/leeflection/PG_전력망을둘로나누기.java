package studygroup.Week031;

public class PG_전력망을둘로나누기 {
    static int[] parent;
    static int answer = Integer.MAX_VALUE;
    static int N;
    public int solution(int n, int[][] wires) {
        N = n;
        for(int i=0; i<wires.length; i++){
            parent = new int[n+1];
            for(int j=1; j<=n; j++){
                parent[j] = j;
            }
            for(int j=0; j<wires.length; j++){
                if(i==j) continue;
                int a = wires[j][0];
                int b = wires[j][1];

                union(a,b);
            }
            for(int j=1; j<=N; j++){
                find(j);
            }
            int tmp = check();
            answer = Math.min(answer,tmp);
        }
        return answer;
    }
    public static int find(int x){
        if(x == parent[x]) return x;
        else return parent[x] = find(parent[x]);
    }
    public static void union(int x, int y){
        x = find(x);
        y = find(y);

        if(x != y){
            if(x > y) parent[x] = y;
            else parent[y] = x;
        }
    }
    public static int check(){
        int count = 0;
        int mask = parent[1];
        for(int i=1; i<=N; i++){
            if(mask == parent[i]) count++;
        }
        return Math.abs((N-count)-count);
    }
}
