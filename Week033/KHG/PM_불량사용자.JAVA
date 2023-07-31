import java.util.*;

class Solution {
    static boolean[][] matches;
    static int U, B, answer;
    static boolean[] dupCheck, visited;
    static int[] nums;
    static String[] user_id2;
    public int solution(String[] user_id, String[] banned_id) {
        answer = 0;
        U = user_id.length;
        B = banned_id.length;
        matches = new boolean[U][B];
        user_id2 = user_id;
        dupCheck = new boolean[(int)Math.pow(U,U)+1];
        
        //일치여부 미리확인
        for(int i=0; i<U; i++){
            for(int j=0; j<B; j++){
                if(user_id[i].length()==banned_id[j].length()){
                    boolean isMatch=true;
                    for(int k=0; k<user_id[i].length(); k++){
                        if(banned_id[j].charAt(k)!='*' && user_id[i].charAt(k)!=banned_id[j].charAt(k)){
                            isMatch=false;
                            break;
                        }
                    }
                    if(isMatch) matches[i][j]=true;
                }
                
            }
        }
        
        
        visited = new boolean[U];
        nums = new int[B];
        
        //순열로 확인
        perm(0);
        return answer;
    }
    
    static void perm(int idx){
        if(idx==B){
            boolean isMatch=true;
            for(int i=0; i<B; i++){
                if(!matches[nums[i]][i]){
                    isMatch=false;
                    break;
                }
            }
            //모두 일치한 경우
            if(isMatch){
                int[]temp = nums.clone();
                Arrays.sort(nums);
                int num = 0;
                int mod=1;
                for(int i=0; i<B; i++){
                    num+= nums[i] * mod;
                    mod*=B;
                }
                //중복확인
                if(!dupCheck[num]){
                    dupCheck[num]=true;
                    answer++;
                }
                nums=temp;
            }
            return;
        }
        for(int i=0; i<U; i++){
            if(visited[i])continue;
            visited[i]=true;
            nums[idx]=i;
            perm(idx+1);
            visited[i]=false;
        }
    }
}