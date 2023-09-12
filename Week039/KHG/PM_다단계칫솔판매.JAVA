import java.util.*;

class Solution {
    static Map<String, String> parents;
    static Map<String, Integer> result;
    
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int[] answer = new int[enroll.length];
        parents = new HashMap<>();
        result = new HashMap<>();
        for(int i=0; i<enroll.length; i++){
            parents.put(enroll[i], referral[i]);
            result.put(enroll[i], 0);
        }
        
        for(int i=0; i<seller.length; i++){
            String name = seller[i];
            int num = amount[i]*100;
            rec(name, num);
            
        }
        

        for(int i=0; i<enroll.length; i++){
            String name = enroll[i];
            //System.out.println(name + " "+result.get(name));
            answer[i] = result.get(name);
        }
        
        return answer;
    }
    
    void rec(String name, int money){        
        int m2 = money/10;
        int m1 = money-m2;
        
        result.put(name, result.get(name)+m1);
        String parent = parents.get(name);
        if(!parent.equals("-") && m2>0){
            rec(parent, m2);
        }
           
    }
}