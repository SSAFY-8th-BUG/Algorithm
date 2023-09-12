import java.util.*;

class Solution {
    static int N, ii;
    static Node[] nodes;
    static int[][] answer;
    public int[][] solution(int[][] nodeinfo) {
        N = nodeinfo.length;
        nodes = new Node[N];
        answer = new int[2][N];
        for(int i=0; i<N; i++){
            int x = nodeinfo[i][0];
            int y = nodeinfo[i][1];
            nodes[i] = new Node(i+1, x,y);
        }
        Arrays.sort(nodes, (o1,o2)->o1.y==o2.y? o1.x-o2.x : o2.y-o1.y); //정렬
        makeTree(nodes[0],0,-1,100001); //트리생성
        
        ii=0;
        jeon(nodes[0]); //전위순회
        ii=0;
        hoo(nodes[0]); //후위순회
        
        return answer;
    }
    
    static void jeon(Node cur){
        answer[0][ii++]=cur.idx;
        if(cur.left!=null){
            jeon(cur.left);
        }
        if(cur.right!=null){
            jeon(cur.right);
        }
    }
    
    static void hoo(Node cur){
        if(cur.left!=null){
            hoo(cur.left);
        }
        if(cur.right!=null){
            hoo(cur.right);
        }
        answer[1][ii++]=cur.idx;
    }
    
    static void makeTree(Node cur, int idx, int lx, int rx){
        boolean isLeft=false, isRight=false;
        for(int i=idx+1; i<N; i++){
            if(isLeft && isRight)break;
            Node next = nodes[i];
            if(next.y<cur.y){
                if(next.x<cur.x){ //왼쪽
                    if(next.x>lx && next.x<rx && !isLeft){ //범위내
                        isLeft = true;
                        cur.left = next;
                        next.parent = cur;
                        makeTree(next, i, lx, cur.x);    
                    }
                }else{ //오른쪽
                    if(next.x<rx  && next.x>lx && !isRight){ //범위내
                        isRight = true;
                        cur.right = next;
                        next.parent = cur;
                        makeTree(next, i, cur.x, rx);    
                    }
                }
            }
        }
        
    }
    
    class Node{
        int idx;
        int x,y;
        Node parent, left, right;
        Node(int idx, int x, int y){
            this.idx = idx;
            this.x=x;
            this.y=y;
        }
        public String toString(){
            return idx+" "+x+" "+y;
        }
    }
}