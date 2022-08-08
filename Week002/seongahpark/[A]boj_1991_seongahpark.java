import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static class Node{
		char ch;
		Node left;
		Node right;
		
		public Node(char ch) {
			this.ch = ch;
		}
	}
	
	static int n;
	static Node[] arr;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		arr = new Node[n];
		for(int i=0; i<n; i++) {
			arr[i] = new Node((char)(i+'A'));
		}
		
		char c, l, r;
		for(int i=0; i<n; i++) {
			String str = br.readLine();
			c = str.charAt(0);
			l = str.charAt(2);
			r = str.charAt(4);
			
			if(l != '.') arr[c-'A'].left = arr[l-'A'];
			if(r != '.') arr[c-'A'].right = arr[r-'A'];
		}
		
		pre(arr[0]);
		sb.append(System.lineSeparator());
		
		in(arr[0]);
		sb.append(System.lineSeparator());
		
		post(arr[0]);
		sb.append(System.lineSeparator());
	
		br.close();
		
		System.out.println(sb);
	}
	
	static void pre(Node node) {
		sb.append(node.ch);
		if(node.left != null) pre(node.left);
		if(node.right != null) pre(node.right);
	}

	static void in(Node node) {
		if(node.left != null) in(node.left);
		sb.append(node.ch);
		if(node.right != null) in(node.right);
	}
	
	static void post(Node node) {
		if(node.left != null) post(node.left);
		if(node.right != null) post(node.right);
		sb.append(node.ch);
	}
}
