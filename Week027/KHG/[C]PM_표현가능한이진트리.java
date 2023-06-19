import java.util.*;
class Solution {
    
    static boolean isTree;
    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];
        
        for(int i=0; i<numbers.length; i++){
            isTree=true;
            int[] lst = getBinary(numbers[i]);
            int half = lst.length/2;
            if (lst[half]==0){  //루트가 1인지
                answer[i]=0;
                continue;
            }
            if(lst.length==1){  //길이 1인경우는 제외
                answer[i]=1;
                continue;
            }
            
            checkTree(lst, 0, lst.length-1, true); //포화트리인지 재귀로 확인
            if(isTree) answer[i]=1;
        }
        
        return answer;
    }
    
    //이진트리 구하기
    static int[] getBinary(long number){
        int len=getLength(number);
        int[] lst = new int[len];

        for(int i=0; i<len; i++){
            if (number%2L==1)
                lst[i] = 1;
            number = number/2L;
            if(number == 0L)break;
        }
        //System.out.println(Arrays.toString(lst));  //이진트리
        return lst;
    }
    
    //이진트리 사이즈 구하기
    static int getLength(long number){ 
        if(number==1L) return 1;
        int len=2;
        Long bin = 1L;
        while( number > bin-1 ){
            len = len*2;
            bin = pow(len-1);
        }
        return len-1;
    }
    
    
    //포화이진트리 가능한지 재귀로 확인
    static void checkTree(int[] lst, int si, int ei, boolean valid){
        if(!isTree) return;
        int mid = (si+ei)/2;
        if(lst[mid]==0 && valid){ //유효한 서브트리인데 부모가 0
            isTree=false;
            return;
        }
        int left = (si+mid-1)/2; //좌자식
        int right = (mid+1+ei)/2; //우자식
        if(lst[mid]==0 && (lst[left]!=0 || lst[right]!=0)){
            isTree=false;
            return;
        }
        
        if(ei-si == 2){ //리프노드
            return;
        }
        
        boolean nextValid = lst[mid]==0;
        checkTree(lst, si, mid-1, valid&&nextValid); //좌재귀
        checkTree(lst, mid+1, ei, valid&&nextValid); //우재귀
    }
    
    //제곱
    static Long pow(int len){
        Long ret=1L;
        for(int i=0; i<len; i++){
            ret = ret*2L;
        }
        return ret;
    }
}