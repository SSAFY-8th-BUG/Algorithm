import java.util.*;

class Solution {
    static long[] mods;
    static boolean[] visited;
    static int N;
    public int[] solution(int n, long k) {
        mods = new long[n];
        k=k-1;
        N=n;
        mods[n-2]=1L;
        mods[n-1]=1L;
        for(int i=2; i<n; i++){
            mods[n-i-1] = mods[n-i]*i;
        }
        //System.out.println(Arrays.toString(mods));
        int[] answer = new int[n];
        visited = new boolean[n];
        for(int i=0; i<n; i++){
            int m = (int)(k/mods[i]);
            int num = getNum(m);
            //System.out.println(k+" "+m + " "+num);
            answer[i]=num;
            k=k%mods[i];
        }
        return answer;
    }
    
    int getNum(int m){
        for(int i=0; i<N; i++){
            if(!visited[i]) m--;
            if(m==-1){
                visited[i]=true;
                return i+1;
            }
        }
        return -1;
    }
}