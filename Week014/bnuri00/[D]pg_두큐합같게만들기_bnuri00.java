package week014;

import java.util.*;

public class D_pg_두큐합같게만들기 {
    public int solution(int[] queue1, int[] queue2) {
        
        long sum1 = 0;
        long sum2 = 0;

        for(int i = 0 ; i < queue1.length ; i ++){
            sum1 += (long) queue1[i];
            sum2 += (long) queue2[i];
        }
        
        int n = queue1.length;

        if(sum1 == sum2) return 0;
        else if((sum1 + sum2) % 2 != 0) return -1;
        else if(sum2 > sum1) {
        	long target = (sum1 + sum2) / 2;
        	return logic(queue2, queue1, target, n, sum2);
        } else {
        	long target = (sum1 + sum2) / 2;
	        return logic(queue1, queue2, target, n, sum1);
        }


    }
    
    // 합이 큰 큐의 원소를 옮긴다
	private int logic(int[] queue1, int[] queue2, long target, int n, long sum) {
		List<Long> list = new ArrayList<>();
        for(int i = 0 ; i < n; i ++) list.add((long)queue1[i]);
        for(int i = 0 ; i < n; i ++) list.add((long)queue2[i]);
        for(int i = 0 ; i < n; i ++) list.add((long)queue1[i]);
        for(int i = 0 ; i < n; i ++) list.add((long)queue2[i]);
        
        
		int i = 0;
        int j = n;
        int cnt = 0;
        while(i < n * 2 && j < n*4){
            while(sum > target && i < n * 2){
                sum -= list.get(i++);
                cnt++;
            } 

            while(sum < target && j < list.size()){
                sum += list.get(j++);
                cnt++;
            } 

            if(sum == target) return cnt;
        }

        return -1;
	}
}