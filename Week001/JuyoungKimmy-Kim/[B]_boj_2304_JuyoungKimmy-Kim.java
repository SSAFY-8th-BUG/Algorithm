import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/*
 * low, high의 좌푤를 저장할 Pos Class를 만들고 low가 작은 곳, 큰 곳부터 순차적으로
 * 탐색하기 위해 compareTo override
 * 
 * O(2N)
 * 
 */
class Pos implements Comparable<Pos>{
	int low; int high;
	
	 Pos () {}
	 Pos (int low, int high) {
		this.low=low;
		this.high=high;
	}
	
	@Override
	public int compareTo (Pos p) {
		if (p.low<this.low) return 1;
		return -1;
	}
}


public class B_boj_2304 {

	static int N;
	static Pos[] pos;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=null;
		
		N=Integer.parseInt(br.readLine());
		List<Pos> pos=new ArrayList<>();
		
		for (int i=0; i<N; i++) {
			st=new StringTokenizer (br.readLine());
			int L=Integer.parseInt(st.nextToken());
			int H=Integer.parseInt(st.nextToken());
			pos.add(new Pos(L, H));
		}
		
		
		// #1. low가 작은 곳부터 탐색(왼쪽부터 순차적으로 탐색)
		Collections.sort(pos);
		
		int sum=0;
		int prevLow=pos.get(0).low;
		int maxHigh=pos.get(0).high;
		
		int curLow=pos.get(0).low;
		int curHigh=pos.get(0).high;
		
		// #1-1. 이전에 가장 높았던 높이보다 현재 높이가 더 크거나 같은 경우
		//		 이전까지의 너비 저장
		for (Pos p : pos) {
			curLow=p.low;
			curHigh=p.high;
			
			if (maxHigh<=curHigh) {
				sum+=(curLow-prevLow)*maxHigh;
				maxHigh=curHigh;
				prevLow=curLow;
			}
		}
		
		// 2. low가 큰 곳부터 탐색
		Collections.reverse(pos);
			
		prevLow=pos.get(0).low;
		maxHigh=pos.get(0).high;
		
		curLow=pos.get(0).low;
		curHigh=pos.get(0).high;
		
		// #2-1. 이전의 높이보다 현재 높이가 큰 경우
		for (Pos p : pos ) {
			curLow=p.low;
			curHigh=p.high;
			
			if (maxHigh<curHigh) {
				sum+=(prevLow-curLow)*maxHigh;
				maxHigh=curHigh;
				prevLow=curLow;
			}
		}
		
		// #3. 마지막 제일 높은 한 칸을 더해줌
		sum+=maxHigh;
		
		System.out.println(sum);
	}

}

