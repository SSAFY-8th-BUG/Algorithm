package studygroup.Week039;
import java.util.*;

public class PG_길찾기게임 {
    static class Node{
        int idx,x,y;
        Node left,right;
        public Node(int idx,int x,int y){
            this.idx = idx;
            this.x = x;
            this.y = y;
        }
    }
    static ArrayList<Node> list;
    static int[][] ans;
    static int idx;
    public int[][] solution(int[][] nodeinfo) {
        list = new ArrayList<>();
        for(int i=0; i<nodeinfo.length; i++){
            list.add(new Node(i+1,nodeinfo[i][0],nodeinfo[i][1]));
        }

        Collections.sort(list,(o1,o2)->{
            if(o1.y == o2.y){
                return o1.x - o2.x;
            }else{
                return o2.y - o1.y;
            }
        });
        Node root = list.get(0);

        for(int i=1; i<list.size(); i++){
            makeTree(list.get(i),root);
        }
        ans = new int[2][list.size()];

        preorder(root);
        idx = 0;
        postorder(root);
        return ans;
    }
    public void makeTree(Node cur, Node root){
        if(root.x > cur.x){
            if(root.left == null){
                root.left = cur;
            }else{
                makeTree(cur, root.left);
            }
        }
        if(root.x < cur.x){
            if(root.right == null){
                root.right = cur;
            }else{
                makeTree(cur, root.right);
            }
        }
    }
    public void preorder(Node root){
        if(root != null){
            ans[0][idx] = root.idx;
            idx++;
            preorder(root.left);
            preorder(root.right);
        }
    }
    public void postorder(Node root){
        if(root != null){
            postorder(root.left);
            postorder(root.right);
            ans[1][idx] = root.idx;
            idx++;
        }
    }
}
