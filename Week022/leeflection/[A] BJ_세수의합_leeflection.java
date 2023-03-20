import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Main {
    static int N;
    static int[] num;
    static ArrayList<Integer> twoSum;
    public static void main(String[] args)throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        num = new int[N];
        twoSum = new ArrayList<>();
        for(int i=0; i<N; i++){
            num[i] = Integer.parseInt(br.readLine());
        }

        for(int i=0; i<N; i++){
            for(int j=i; j<N; j++){
                twoSum.add(num[i]+num[j]);
            }
        }
        Arrays.sort(num);
        Collections.sort(twoSum);
        int answer = 0;

        for(int i=N-1; i>=0; i--){
            for(int j=0; j<i; j++){
                int dif = num[i]-num[j];
                if(binary(dif) && answer < num[i]){
                    answer = num[i];
                }
            }
        }
        System.out.println(answer);
    }

    private static boolean binary(int dif) {
        int lt = 0;
        int rt = twoSum.size()-1;
        while(lt <= rt){
            int mid = (lt+rt)/2;
            if(twoSum.get(mid)<dif){
                lt = mid+1;
            }else if(twoSum.get(mid) > dif){
                rt = mid-1;
            }else{
                return true;
            }
        }
        return false;
    }
}