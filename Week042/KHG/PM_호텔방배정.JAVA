import java.util.*;

class Solution {
    static int N;
    static Map<Long,Long> map;
    public long[] solution(long k, long[] room_number) {
        N= room_number.length;
        map = new HashMap<>();
        long[] answer = new long[N];
        
        for(int i=0; i<N; i++){
            long num = room_number[i];
            answer[i] = findRoom(num);
        }
        return answer;
    }
    
    static long findRoom(long num){ //(모든 값 업데이트)
        if(!map.containsKey(num)){
            map.put(num, num+1);
            return num;
        }
        
        long nextNum = map.get(num);
        long emptyNum = findRoom(nextNum);
        map.put(num, emptyNum);
        return emptyNum;
    }
    
    /*static long findRoom(long num){ //시간초과(첫, 마지막 값만 업데이트)
        long _num = num;
        while(true){
            if(!map.containsKey(num))
                break;
            num = map.get(num);
        }
        if(_num!=num)
            map.put(_num, num+1);
        map.put(num, num+1);
        return num;
        
    }*/
    
}