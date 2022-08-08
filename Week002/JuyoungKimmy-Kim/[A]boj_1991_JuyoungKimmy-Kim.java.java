package recursion;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1991 {
	static int N;
	static char[][] adj;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		adj = new char[N][2];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			char root = st.nextToken().charAt(0);
			char left = st.nextToken().charAt(0);
			char right = st.nextToken().charAt(0);

			adj[root - 'A'][0] = left;
			adj[root - 'A'][1] = right;
		}
		
		preorder(0);
		System.out.println();
		inorder(0);
		System.out.println();
		postorder(0);
	}

	static void preorder(int n) {
		System.out.print((char)('A'+n));
		if (adj[n][0]!='.') preorder (adj[n][0]-'A');
		if (adj[n][1]!='.') preorder (adj[n][1]-'A');
	}

	static void inorder(int n) {
		if (adj[n][0]!='.') inorder (adj[n][0]-'A');
		System.out.print((char)('A'+n));
		if (adj[n][1]!='.') inorder (adj[n][1]-'A');
	}
	
	static void postorder (int n) {
		if (adj[n][0]!='.') postorder (adj[n][0]-'A');
		if (adj[n][1]!='.') postorder (adj[n][1]-'A');
		System.out.print((char)('A'+n));
		
	}

}