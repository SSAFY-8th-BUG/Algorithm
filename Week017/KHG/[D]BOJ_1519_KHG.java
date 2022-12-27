import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;



public class Main { 
	
	static int[] dp;
	public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String N=br.readLine();
    
        if(N.length()==1) {
        	System.out.println(-1);
        	return;
        }
        Set<Integer> startSet = new HashSet<Integer>();
        makeSet(N, startSet); //시작 진부분집합
        
        List<Integer> starts=new ArrayList<>();
        for(int n :startSet) {
        	starts.add(n);
        }
        Collections.sort(starts,(o1,o2)->o1-o2);//시작 진부분 집합을 오름차순 정렬함
        //System.out.println(starts); 
        
        dp=new int[Integer.parseInt(N)+1];
        for(int i=0; i<=9; i++) { //-1이면 패, 0이면 모름, 1이면 승
        	dp[i]=-1;
        }
        
        int answer=0;
        int num=Integer.parseInt(N);
        for(int pick : starts) {
        	String numStr=Integer.toString(num-pick);
        	check(numStr); //pick으로 시작할 때 이길수 있는지 체크
        	if(dp[num-pick]==-1) { //상대가 지는경우(내가 이김)
        		dp[num]=1;
        		answer=pick;
        		break;
        	}
        	
        }
        if(dp[num]<=0) {
        	answer=-1;
        }
        /*for(int i=1; i<=num; i++) {
        	System.out.println(i+":"+dp[i]);
        }*/
        System.out.println(answer);
    }
	
	static void makeSet(String N, Set<Integer> set) { //진부분집합 생성
		int ln=N.length();
		for(int i=0; i<ln; i++) {
			if(N.charAt(i)=='0') continue;
			String numStr="";
			for(int j=i; j<ln; j++) {
				numStr += N.charAt(j);
				if(i==0 && j==ln-1) continue;
				set.add(Integer.parseInt(numStr));
			}
		}
	}
	
	static void check(String numStr) { //num일때 이길 수 있는지 확인
		int num=Integer.parseInt(numStr);
		if(num<10) {
			return;
		}
		Set<Integer> set= new HashSet<>();
		makeSet(numStr, set); //num의 진부분집합
		int ret=-1;
		for(int pick : set) {
			int num2=num-pick; //상대의 숫자
			if(dp[num2]==0) { //상대의 승패를 모르는 경우
				String numStr2=Integer.toString(num2);
				check(numStr2); //승패를 여부 구함
			}
			if(dp[num2]==-1) ret=1; //상대가 지게하는 수가 있을때
		}
		dp[num]=ret;
	}
	
	
	
	
}