package week017;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

// boj 2866 문자열 잘라내기
public class C_boj_2866_bnuri00 {
	static int count = 0;
	static int R, C;
	static ArrayList<String> list;
	static HashSet<String> set;
	public static void main(String[] args) throws Exception {
		list = new ArrayList<String>();
		set = new HashSet<String>();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb;
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i < R; i++) list.add(br.readLine());
		for (int i = 0; i < C; i++) {
			sb = new StringBuilder();
			for (int j = 1; j < R; j++) {
				sb.append(list.get(j).charAt(i));
			}
			set.add(sb.toString());
		}
		
		
		while(set.size() == C) {
			HashSet<String> tmpSet = new HashSet<String>();
			set.forEach((s) -> tmpSet.add(s.substring(1)));
			set.clear();
			set.addAll(tmpSet);
			count++;
		}
		System.out.println(count);
	}

}
