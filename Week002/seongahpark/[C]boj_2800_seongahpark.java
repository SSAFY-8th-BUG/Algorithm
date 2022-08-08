import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
import java.util.TreeSet;

public class Main {
	
	static char[] arr;
	static boolean[] visited;
	static boolean isFirst = true;
	static ArrayList<Integer []> bracket = new ArrayList<>();
	static Stack<Integer> s = new Stack<>();
	static TreeSet<String> set = new TreeSet<>();
	// 왜 set으로 중복을 제거해야되는가?
	// (((0))) 이렇게 되어있는 경우 괄호를 어느걸 제거하더라도 같은 결과 ((0))이 나온다
	// set에는 HashSet과 TreeSet이 있는데 HashSet을 사용하려면 정렬 후 출력해야 함

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		arr = br.readLine().toCharArray();
		
		for(int i=0; i<arr.length; i++) {
			char c = arr[i];
			// stack을 통해 괄호를 짝지어준다
			if( c == '(') s.push(i);
			else if(c == ')') bracket.add(new Integer[] {s.pop(), i});
		}
		
		visited = new boolean[arr.length];
		Arrays.fill(visited,  true);
		
		dfs(0);
		
		for(String s : set) {
			sb.append(s).append(System.lineSeparator());
		}
		
		System.out.println(sb);
		br.close();

	}
	
	// 백트래킹
	public static void dfs(int x) {
		if(x == bracket.size()) {
			if(isFirst) isFirst = false;
			else {
				StringBuilder sb = new StringBuilder();
				for(int i=0; i<arr.length; i++) {
					if(visited[i]) sb.append(arr[i]);
				}
				set.add(sb.toString());
			}	
			return;
		}
		
		visited[bracket.get(x)[0]] = true;
		visited[bracket.get(x)[1]] = true;
		dfs(x+1);
		
		visited[bracket.get(x)[0]] = false;
		visited[bracket.get(x)[1]] = false;
		dfs(x+1);
	}
}
