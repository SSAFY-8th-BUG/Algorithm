import java.util.*;
public class PG_길찾기게임 {
    static int[][] answer;
    static int idx;

    public int[][] solution(int[][] nodeinfo) {

        Node[] node = new Node[nodeinfo.length];

        for(int i=0;i<nodeinfo.length;i++){
            node[i] = new Node(nodeinfo[i][0], nodeinfo[i][1], i+1, null, null);
        }
        // y가 큰순서대로 같으면 x가 작은 순서대로
        Arrays.sort(node, (n1, n2) -> n1.y == n2.y ? n1.x - n2.x : n2.y - n1.y);

        Node root = node[0];
        for(int i=1;i<node.length;i++){
            insertNode(root, node[i]);
        }

        answer = new int[2][nodeinfo.length];
        idx = 0;
        preorder(root);
        idx = 0;
        postorder(root);

        return answer;

    }

    static void insertNode(Node parent, Node child){
        if(parent.x > child.x){
            if(parent.left == null){
                parent.left = child;
            }else{
                insertNode(parent.left, child);
            }
        }else{
            if(parent.right == null){
                parent.right = child;
            }else{
                insertNode(parent.right, child);
            }
        }
    }

    static void preorder(Node root){
        if(root != null){
            answer[0][idx++] = root.data;
            preorder(root.left);
            preorder(root.right);
        }
    }

    static void postorder(Node root){
        if(root != null){
            postorder(root.left);
            postorder(root.right);
            answer[1][idx++] = root.data;
        }
    }

    static class Node {
        int y;
        int x;
        int data;
        Node left;
        Node right;

        public Node(int x, int y, int data, Node left, Node right){
            this.x = x;
            this.y = y;
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }
}
