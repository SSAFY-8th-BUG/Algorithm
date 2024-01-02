package Week049.moski;

import java.util.Arrays;
public class 무지의먹방라이브 {
    static public class Food{
        public int amount;
        public int index;
    }

    public int solution(int[] food_times, long k) {
        int answer = 0;
        long length = food_times.length;
        long ssum = 0;

        Food[] food = new Food[(int)length];
        for(int i=0;i<length;i++) {
            ssum += food_times[i];
            food[i] = new Food();
            food[i].amount = food_times[i];
            food[i].index = i+1;
        }
        if(ssum<=k) return -1;
        Arrays.sort(food, (o1, o2) -> o1.amount == o2.amount ? o1.index - o2.index : o1.amount - o2.amount);
        long minV = 0;
        long sum = 0;

        for(int i=0;i<length;i++){
            if(minV < food[i].amount){
                sum = (food[i].amount-minV)*(length-i);
                minV = food[i].amount;

                if(k>=sum){
                    k -= sum;
                }else {
                    Arrays.sort(food,i,(int)length,(o1, o2)->o1.index - o2.index);
                    answer = food[i+(int) (k % (length-i))].index;

                    break;
                }
            }
        }
        return answer;
    }
}
