package studygroup.Week039;
import java.util.*;
public class PG_표편집 {
    static class Node{
        boolean removed;
        Node prev;
        Node next;
    }
    public String solution(int n, int k, String[] cmd) {
        Node[] table = new Node[n];

        for(int i=0; i<n; i++){
            table[i] = new Node();
        }

        for(int i=0; i<n; i++){
            if(i==0){
                table[i].prev = null;
                table[i].next = table[i+1];
            }else if(i==n-1){
                table[i].prev = table[i-1];
                table[i].next = null;
            }else{
                table[i].prev = table[i-1];
                table[i].next = table[i+1];
            }
        }
        //undo영역
        Stack<Node> stack = new Stack<>();
        //현재 노드
        Node cur = table[k];
        for(int i=0; i<cmd.length; i++){
            String[] tmp = cmd[i].split(" ");
            char c = tmp[0].charAt(0);
            if(c == 'D'){
                int num = Integer.parseInt(tmp[1]);
                for(int j=0; j<num; j++){
                    cur = cur.next;
                }
            }else if(c == 'U'){
                int num = Integer.parseInt(tmp[1]);
                for(int j=0; j<num; j++){
                    cur = cur.prev;
                }
            }else if(c=='C'){
                stack.push(cur);
                cur.removed = true;
                //이전
                Node prev = cur.prev;
                //다음
                Node next = cur.next;
                //현재 행 바꿔주기
                if(cur.next == null){
                    cur = cur.prev;
                }else{
                    cur = cur.next;
                }
                //이전노드의 다음값을 현재 노드의 다음값으로 연결
                if(prev != null){
                    prev.next = next;
                }
                //다음 노드의 이전 값을 현재 노드의 이전값으로 연결
                if(next != null){
                    next.prev = prev;
                }
            }else{
                Node remove = stack.pop();
                remove.removed = false;
                Node prev = remove.prev;
                Node next = remove.next;

                if(prev != null){
                    prev.next = remove;
                }
                if(next != null){
                    next.prev = remove;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<n; i++){
            if(table[i].removed) sb.append("X");
            else sb.append("O");
        }
        return sb.toString();
    }
}
