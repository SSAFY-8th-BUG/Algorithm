package week009;

import java.util.Scanner;

/*
 * boj 1300 k번째 수
 * 
 * 풀이시간: 2시간+
 * 참고: 다른사람 코드
 * 
 * <풀이>
 * - 배열의 특징을 이용해 현재 수보다 작거나 같은 수의 개수(smallerNumCnt) 계산
 * 
 * - smallerNumCnt가 주어진 인덱스(k)보다 
 * - 작은 경우 현재 mid가 작음
 * - 큰 경우 현재 mid가 크거나 정답임
 * 
 * <삽질목록>
 * - ┌ 모양 파트로 자른 부분이 밑의 파트보다 항상 작을 것으로 생각하고  풀었는데 그렇지 않음
 * 
 * */
public class BBoj_1300_bnuri00 {

	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int k = sc.nextInt();
		
		System.out.println(binarySearch(N, k));
	}
	
	static long binarySearch(int n, int k) {
		
		int left = 1, right = k;	// 답이 k보다 커질 수 없으므로 right = k
		int mid;
		int result=k;
		
		while(left <= right) {
			mid = (left +right)/2;
			
			long smallerNumCnt = 0;
			for(int i=1;i<=n; i++) {
				
				// 중간값을 각 column 번호로 나눈 값 -> 현재 mid보다 작거나 같은 값의 개수
				// 최대 n개임
				smallerNumCnt += Math.min(n, mid/i);	
			}
			
			if(smallerNumCnt >= k) { //현재 mid가 크거나 정답
				right = mid - 1;
				result = Math.min(result, mid);
			}
			else { //현재 mid가 작음
				left = mid + 1;
			}
		}
		return result;
	}
}
