package stude_02;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class A_boj1991_트리순회 {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		
		Tree<Character> tree = new Tree<Character>();
		tree.emptyNodeEl = '.';
		String input;	
		
		for(int i = 0; i < n; i++) {
			input = br.readLine().replace(" ", "");
			tree.addNode(input.charAt(0), input.charAt(1),input.charAt(2));
		}	
		tree.preOrder(tree.root);
		System.out.println();
		tree.inOrder(tree.root);
		System.out.println();
		tree.postOrder(tree.root);
	}

}
class Node<T>{
	T element;
	Node<T> left;
	Node<T> right;
	public Node(T element) {
		super();
		this.element = element;
	}
	@Override
	public String toString() {
		return element.toString();
	}

}
class Tree<T>{
	Node<T> root;
	T emptyNodeEl;

	// 후위순회:  (왼쪽 자식) (오른쪽 자식) (루트)
	public void postOrder(Node node) {
		if(node.left != null)postOrder(node.left);
		if(node.right != null)postOrder(node.right);
		System.out.print(node);
	}
	// 중위순회: (왼쪽 자식) (루트) (오른쪽 자식)
	public void inOrder(Node node) {
		if(node.left != null)inOrder(node.left);
		System.out.print(node);
		if(node.right != null)inOrder(node.right);
	}
	// 전위순회: (루트) (왼쪽 자식) (오른쪽 자식)
	public void preOrder(Node node) {
		System.out.print(node);
		if(node.left != null)preOrder(node.left);
		if(node.right != null)preOrder(node.right);
		
	}
	public void setRoot(Node<T> root) {
		this.root = root;
	}
	// 노드 추가
	public void addNode(T element, T left, T right) {
		if(this.root == null) {
			Node<T> root = new Node<T>(element);
			if(!left.equals(emptyNodeEl)) {
				root.left= new Node<T>(left);
			}
			if(!right.equals(emptyNodeEl)) {
				root.right= new Node<T>(right);
			}	
			this.root = root;
		}else {
			_addNode(this.root, element, left, right);
		}
		
	}
	// 노드  찾아서 left, right 추가함 (recusion)
	private void _addNode(Node<T> node, T element, T left, T right) {
		
		if(node == null) return;	// 종료조건
		if(node.element == element) {

			if(!left.equals(emptyNodeEl)) {
				node.left = new Node<T>(left);
			}
			if(!right.equals(emptyNodeEl)) {
				node.right = new Node<T>(right);
			}
		} else {
			_addNode(node.right, element, left, right);
			_addNode(node.left, element, left, right);
		}
	}
	
}
