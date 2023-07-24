import java.util.*;

class Solution {
    static List<Long> nums;
    static List<Character> ops;
    static Long answer;
    static char[] order;
    public long solution(String expression) {
        answer = 0L;
        String numstr = new String(expression);
        String []numst=numstr.split("[*,\\-,+]");
        Long [] numb = new Long[numst.length];
        for(int i=numst.length-1; i>=0;i--){ //뒤집어 넣기
            numb[numst.length-i-1]=Long.parseLong(numst[i]);
        }
        
            
        List<Long> numbers= new LinkedList<>(Arrays.asList(numb));
        List<Character> opers= new LinkedList<>();
        
        for(int i=numstr.length()-1; i>=0; i--){ //뒤집어 넣기
            char c = numstr.charAt(i);
            if(c=='-' || c=='*' || c=='+'){
                opers.add(c);
            }
        }
        //System.out.println(numbers);
        //System.out.println(opers);
        char[][] orders= {{'-','*','+'},{'-','+','*'},{'+','-','*'},{'+','*','-'},{'*', '+', '-'}, {'*', '-', '+'}};
        
        for(char[] ord :orders){
            nums = new LinkedList<>(numbers);
            ops = new LinkedList<>(opers);
            order = ord;
            rec(0);
        }
        
        return answer;
    }
    
    
    static void rec(int idx){
        if(idx==3){
            Long num = nums.get(0);
            if(num<0L) num = -num;
            if(answer<num){
                answer = num;
            }
            return;
        }
        
        for(int i=ops.size()-1; i>=0; i--){
            if(ops.get(i) == order[idx]){
                Long num = cal(nums.get(i),nums.get(i+1),ops.get(i));
                ops.remove(i);
                nums.set(i,num);
                nums.remove(i+1);
            }
        }
        rec(idx+1);
        
        
    }
    
    static Long cal(Long n2, Long n1, char op){
        if(op=='-') return n1-n2;
        if(op=='+') return n1+n2;
        if(op=='*') return n1*n2;
        return 0L;
    }
    
}