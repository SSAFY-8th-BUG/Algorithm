package studygroup.Week043;

public class PG_연속된부분수열의합 {
    public int[] solution(int[] sequence, int k) {
        int[] arr = sequence;

        int lt = 0;
        int rt = 0;
        int sum = 0;
        int min = Integer.MAX_VALUE;
        int lans = 0;
        int rans = 0;
        while(lt<=rt && rt<arr.length){
            sum += arr[rt];
            if(sum == k){
                if(rt-lt < min){
                    min = rt-lt;
                    lans = lt;
                    rans = rt;
                }
            }else if(sum > k){
                while(true){
                    sum -= arr[lt++];
                    if(sum < k) break;
                    if(sum == k){
                        if(rt-lt < min){
                            min = rt-lt;
                            lans = lt;
                            rans = rt;
                        }
                    }
                }
            }
            rt++;
        }
        return new int[]{lans,rans};
    }
}
