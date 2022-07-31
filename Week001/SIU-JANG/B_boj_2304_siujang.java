package a;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

class Pair implements Comparable<Pair> {
	int x;
	int height;
	
	Pair(int x, int height) {
		this.x = x;
		this.height = height;
	}
	
	@Override
	public int compareTo(Pair p) {
		if (this.x > p.x) {
			return 1;
		} else {
			return -1;
		}
	}
}

public class B_boj_2304_siujang {
	
	static int N, maxHeightX, maxHeight, ans, leftStop, rightStop;
	static Stack<Pair> st;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		Pair[] array = new Pair[N];
		st = new Stack<>();
		maxHeightX = -1;
		maxHeight = 0;
		ans = 0;
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int height = Integer.parseInt(st.nextToken());
			
			maxHeight = maxHeight < height ? height : maxHeight;
			
			array[i] = new Pair(x, height);
		}
		
		Arrays.sort(array);
		
		for (int i = 0; i < N; i++) {			
			if (st.isEmpty()) {
				st.add(array[i]);
			} else {
				int prevHeight = st.peek().height;
				if (array[i].height >= prevHeight) {
					ans += ((array[i].x - st.peek().x) * prevHeight);
					st.pop();
					st.add(array[i]);
				}
			}
			
			if (array[i].height == maxHeight) {
				leftStop = array[i].x;
				break;
			}
		}
		
		st.clear();
		
		for (int i = array.length - 1; i >= 0; i--) {			
			if (st.isEmpty()) {
				st.add(array[i]);
			} else {
				int prevHeight = st.peek().height;
				if (array[i].height >= prevHeight) {
					ans += ((st.peek().x - array[i].x) * prevHeight);
					st.add(array[i]);
				}
			}
			
			if (array[i].height == maxHeight) {
				rightStop = array[i].x;
				break;
			}
		}
		
		ans += (rightStop - leftStop + 1) * maxHeight;
		
		System.out.println(ans);
	}
}