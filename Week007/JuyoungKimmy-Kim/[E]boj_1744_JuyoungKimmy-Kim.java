package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
 * Greedy한 접근 방법으로 array를 sorting 해서 큰 수에서 부터 2개씩 쌍을 이루면 될 것 이라 생각
 * -> 1인 경우, (음수, 0), (0, 양수), (음수, 양수)인 경우를 모두 따져 주어야 함
 */
public class BOJ1744 {
	
	static int N;
	static int[] arr;
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		N=Integer.parseInt(br.readLine());
		arr=new int[N];
		
		for (int i=0; i<N; i++) 
			arr[i]=Integer.parseInt(br.readLine());
		
		Arrays.sort(arr);
		int sum=0;
		
		
		//#1. 양수 계산
		int idx=N-1;
		while (idx>=0 && arr[idx]>0) {

			//#1-2. 마지막꺼, 뒤에오는 거 0->현재꺼만 더해줌
			if (idx-1<0 || arr[idx-1]<=0) {
				sum+=arr[idx--];
			}
			//1-3. 1인 경우
			else if (arr[idx]==1 || arr[idx-1]==1) {
				sum+=arr[idx--];
			}
			//#1-4. 그 외에 두 수 곱해서 더해줌
			else {
				sum+=arr[idx--]*arr[idx--];
			}
		}
		
		
		//#2. 음수 계산
		int pIdx=0;
		while (pIdx<=idx) {
			if (pIdx==idx) 
				sum+=arr[pIdx++];
			else {
				sum+=arr[pIdx++]*arr[pIdx++];
			}
		}
		
		System.out.println(sum);
	}
}
