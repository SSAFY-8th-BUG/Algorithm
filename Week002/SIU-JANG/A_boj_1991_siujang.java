import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Node {
	char Self;
	char leftChild;
	char rightChild;
	
	public Node() {}
	public Node(char Self, char leftChild, char rightChild) {
		this.Self = Self;
		this.leftChild = leftChild;
		this.rightChild = rightChild;
	}
}

public class Main {
	
	static int N;
	static List<Node> list;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = null;
		list = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			list.add(new Node(st.nextToken().charAt(0), st.nextToken().charAt(0), st.nextToken().charAt(0)));
		}
		
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).Self == 'A') {
				preOrder(i);
				System.out.println();
				inOrder(i);
				System.out.println();
				postOrder(i);
				break;
			}
		}
	}
	
	static void preOrder(int index) {
		System.out.print(list.get(index).Self);
		
		for (int i = 0; i < list.size(); i++) {
			if (list.get(index).leftChild == list.get(i).Self) {
				preOrder(i);
			}
		}
		
		for (int i = 0; i < list.size(); i++) {
			if (list.get(index).rightChild == list.get(i).Self) {
				preOrder(i);
			}
		}
	}
	
	static void inOrder(int index) {		
		for (int i = 0; i < list.size(); i++) {
			if (list.get(index).leftChild == list.get(i).Self) {
				inOrder(i);
			}
		}
		
		System.out.print(list.get(index).Self);
		
		for (int i = 0; i < list.size(); i++) {
			if (list.get(index).rightChild == list.get(i).Self) {
				inOrder(i);
			}
		}
	}
	
	static void postOrder(int index) {		
		for (int i = 0; i < list.size(); i++) {
			if (list.get(index).leftChild == list.get(i).Self) {
				postOrder(i);
			}
		}
		
		for (int i = 0; i < list.size(); i++) {
			if (list.get(index).rightChild == list.get(i).Self) {
				postOrder(i);
			}
		}
		
		System.out.print(list.get(index).Self);
	}
}