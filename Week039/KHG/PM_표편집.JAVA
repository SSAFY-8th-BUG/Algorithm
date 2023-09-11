import java.util.*;

class Solution {
    static boolean[] deleted;
    static int N;
    static Row cur;
    static Row[] rows; 
    static Deque<Row> stack;
    public String solution(int n, int k, String[] cmd) {
        N =n;
        rows = new Row[n+2];
        for(int i=0; i<n+2; i++){
            rows[i] = new Row(i-1);
        }
        for(int i=1; i<n+1; i++){
            rows[i].next = rows[i+1];
            rows[i].prev = rows[i-1];
        }
        rows[0].next=rows[1];
        rows[n+1].prev=rows[n];
        rows[n+1].idx=-1;
        stack = new ArrayDeque<>();
        deleted = new boolean[n];
        cur = rows[k+1];
        
        for(String c : cmd){
            //System.out.println(" === " + c);
            
            if(c.charAt(0)=='D'){
                int x = Integer.parseInt(c.split(" ")[1]);
                D(x);
            }else if(c.charAt(0) == 'U'){
                int x = Integer.parseInt(c.split(" ")[1]);
                U(x);
            }else if(c.charAt(0) == 'C'){
                C();
            }else{
                Z();
            }
            //System.out.println(cur.idx);
            //System.out.println(Arrays.toString(deleted));
        }
        
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<n; i++){
            if(deleted[i])sb.append("X");
            else sb.append("O");
        }
        return sb.toString();
    }
    
    void U(int x){
        for(int i=0; i<x; i++){
            cur = cur.prev;
        }
    }
    
    void D(int x){
        for(int i=0; i<x; i++){
            cur = cur.next;
        }
    }
    
    void C(){
        int idx = cur.idx;
        deleted[idx]=true;
        
        //위아래 연결
        Row next = cur.next; 
        Row prev = cur.prev;
        next.prev = prev;
        prev.next = next;
        
        stack.add(cur);
        
        //커서이동
        if(cur.next.idx==-1){
            cur = cur.prev;
        }else{
            cur = cur.next;
        }
    }
    
    void Z(){
        Row back = stack.pollLast();
        int idx = back.idx;
        deleted[idx]=false;
        
        //위로 이동하며 연결할거 찾음
        for(int i=idx-1; i>=0; i--){
            if(!deleted[i]){
                Row prev = rows[i+1];
                Row next = prev.next;
                back.prev = prev;
                back.next = next;
                prev.next = back;
                next.prev = back;
                return;
            }
        }
        
        //연결못했으면 밑에거랑 연결
        Row next = rows[0].next;
        Row prev = rows[0];
        back.next = next;
        back.prev = prev;
        next.prev = back;
        prev.next = back;
        
    } 
    
    class Row{
        int idx;
        Row next, prev;
        Row(int idx){
            this.idx = idx;
        }
    }
}