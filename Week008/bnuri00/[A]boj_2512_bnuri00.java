import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 풀이시간: 30분
 * 참고: X
 * 
 * 15248KB		168ms
 * 
 * 
 * */
public class Aboj_2512_bnuri00 {
	static int N, M, total, max = 0;	// 지방 수, 국가 예산, 요청예산 합, 요청예산 중 가장 큰 값
	static int[] req;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		req = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int input = Integer.parseInt(st.nextToken());
			req[i] = input;
			total += input;
			if(max < input) max = input;
		}
		M = Integer.parseInt(br.readLine());
		
		
		
		if(total <= M) {	// 모든 요청 배정될 수 있는 경우
			System.out.println(max);
		} else {	// 모든 요청 배정될 수 없는 경우
			int left = 0;
			int right = max-1;
			int mid = 0;
			
			int result = 0;
			
			while(left<=right) {
				mid = (left+right)/2;
				
				if(checkBudget(mid)) {
					result = mid;
					left = mid+1;
				} else {
					right = mid-1;
				}
			}
			System.out.println(result);
		}
		
		
	}

	static boolean checkBudget(int mid) {
		int sum = 0;
		for (int i = 0; i < req.length; i++) {
			int reqBudget = req[i];
			if(reqBudget < mid) sum += reqBudget;
			else sum += mid;
		}	
		
		return sum <= M;
	}

}
