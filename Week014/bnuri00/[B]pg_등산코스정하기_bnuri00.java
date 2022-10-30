package week014;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class B_pj_등산코스정하기 {
	static class Node{
		int v, w;

		public Node(int v, int w) {
			super();
			this.v = v;
			this.w = w;
		}

		@Override
		public String toString() {
			return "Node [v=" + v + ", w=" + w + "]";
		}
		
	}

	static List<Node>[] adj;
	
    public static int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
    	
        int[] answer = new int[2];
        
        // 인접리스트 만들기
        adj = new List[n+1];	// 0: dummy
        for (int i = 1; i <= n; i++) {
			adj[i] = new ArrayList<Node>();
		}
        for (int i = 0; i < paths.length; i++) {
        	int s = paths[i][0];
        	int e = paths[i][1];
        	int w = paths[i][2];
        	
        	if(isGate(gates, s) || isSummit(summits, s)) {
        		adj[s].add(new Node(e, w));
        	} else if(isGate(gates, e) || isSummit(summits, s)) {
        		adj[e].add(new Node(s, w));
        	} else {
        		adj[paths[i][0]].add(new Node(paths[i][1], paths[i][2]));
        		adj[paths[i][1]].add(new Node(paths[i][0], paths[i][2]));
        	}
        	
		}
        
        
        return findPath(n, gates, summits);
    }
	private static int[] findPath(int n, int[] gates, int[] summits) {
		ArrayDeque<Node> q = new ArrayDeque<Node>();
		
		int[] intensity = new int[n+1];
		Arrays.fill(intensity, Integer.MAX_VALUE);
		
		for (int gate : gates) {
			q.add(new Node(gate, 0));
			intensity[gate] = 0;	// 시작지점 0으로
		}
		
		
		while(!q.isEmpty()) {
			Node curr = q.poll();
			if(curr.w > intensity[curr.v]) continue;
			
			for (Node node : adj[curr.v]) {
				int dis = Math.max(intensity[curr.v], node.w);
				if(intensity[node.v] > dis) {
					intensity[node.v] = dis;
					q.add(new Node(node.v, dis));
				}
			}
		}
		
		int minSummit = -1;		// 산봉우리 번호
		int min = Integer.MAX_VALUE;	// 최솟값
		
		Arrays.sort(summits);
		
		for (int summit : summits) {
			if(intensity[summit] < min) {
				min = intensity[summit];
				minSummit = summit;
			}
		}
		System.out.println(minSummit + " "+ min);
		return new int[] {minSummit, min};
	}

	private static boolean isGate(int[] gates, int curr) {
		for (int i = 0; i < gates.length; i++) {
			if(gates[i] == curr) return true;
		}
		return false;
	}
	
	private static boolean isSummit(int[] summits, int curr) {
		for (int i = 0; i < summits.length; i++) {
			if(summits[i] == curr) return true;
		}
		return false;
		
	}
	
	public static void main(String[] args) {
//		int n = 6;
//		int[][] paths = {
//				{1, 2, 3}, {2, 3, 5}, {2, 4, 2}, {2, 5, 4}, {3, 4, 4}, {4, 5, 3}, {4, 6, 1}, {5, 6, 1}
//		};
//		int[] gates = {1, 3};
//		int[] summits = {5}; 
		
		int n = 7;
		int[][] paths = {
				{1, 4, 4}, {1, 6, 1}, {1, 7, 3}, {2, 5, 2}, {3, 7, 4}, {5, 6, 6}
		};
		int[] gates = {1};
		int[] summits = {2, 3, 4}; 
		solution(n, paths, gates, summits);
	}
}
