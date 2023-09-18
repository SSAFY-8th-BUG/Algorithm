import java.util.*;
public class PG_표편집 {
    static StringBuilder sb;
    public String solution(int n, int k, String[] cmd) {
        int[] pre = new int[n];
        int[] next = new int[n];

        sb = new StringBuilder("O".repeat(n));

        // n만큼
        for(int i=0;i<n;i++){
            pre[i] = i - 1;
            next[i] = i + 1;
        }

        next[n-1] = -1;

        Deque<Node> back = new ArrayDeque<>();

        for(int i=0;i<cmd.length;i++){
            char op = cmd[i].charAt(0);
            if(op == 'U'){
                int move = Integer.valueOf(cmd[i].substring(2));
                while(move-- > 0){
                    k = pre[k];
                }
            }else if(op == 'D'){
                int move = Integer.valueOf(cmd[i].substring(2));
                while(move-- > 0){
                    k = next[k];
                }
            }else if(op == 'C'){
                back.add(new Node(pre[k], k, next[k]));
                if(pre[k] != -1) next[pre[k]] = next[k];
                if(next[k] != -1) pre[next[k]] = pre[k];
                sb.setCharAt(k, 'X');

                if(next[k] != -1) k = next[k];
                else k = pre[k];
            }else if(op == 'Z'){
                Node node = back.pollLast();
                if(node.pre != -1) next[node.pre] = node.data; //연결 정보 복구
                if(node.next != -1) pre[node.next] = node.data;
                sb.setCharAt(node.data, 'O');

            }
            // System.out.println(list.point.data);
        }

        return sb.toString();
    }


    static class Node{
        int data;
        int pre;
        int next;

        public Node(int pre, int data, int next){
            this.data = data;
            this.pre = pre;
            this.next = next;
        }

    }
}

/* 효율성 실패작
* import java.util.*;

class Solution {
    static StringBuilder sb;
    public String solution(int n, int k, String[] cmd) {
        sb = new StringBuilder("O".repeat(n));
        LinkedList list = new LinkedList();
        // n만큼
        for(int i=0;i<n;i++){
            list.addData(i);
        }

        list.setPoint(k);

        for(int i=0;i<cmd.length;i++){
            char op = cmd[i].charAt(0);
            if(op == 'U'){
                int move = Integer.valueOf(cmd[i].substring(2));
                list.moveUp(move);
            }else if(op == 'D'){
                int move = Integer.valueOf(cmd[i].substring(2));
                list.moveDown(move);
            }else if(op == 'C'){
                list.removeData();
            }else if(op == 'Z'){
                list.restoreData();

            }
            // System.out.println(list.point.data);
        }

        // 작업 끝났을 때
        // Node node = list.head;
        // for(int i=0;i<n;i++){
        //     if(node.data == i){
        //         sb.append("O");
        //         // answer += "O";
        //         node = node.next;
        //     }else{
        //         sb.append("X");
        //         // answer += "X";
        //     }
        // }
        return sb.toString();
    }

    static class LinkedList{
        Node head;
        Node point;
        Deque<Node> back;

        public LinkedList(){
            this.head = null;
            this.point = null;
            this.back = new ArrayDeque<>();
        }

        public void setPoint(int data){
            Node tmp = head;
            while(tmp.next != null){
                if(tmp.data == data){
                    point = tmp;
                    break;
                }

                tmp = tmp.next;
            }
        }

        // 맨마지막에 데이터 추가
        public void addData(int data){
            Node node = new Node(data);

            if(head == null){
                this.head = node;
                return;
            }

            Node tmp = head;
            while(tmp.next != null){
                tmp = tmp.next;
            }

            tmp.next = node;
            node.pre = tmp;
        }

        public void moveUp(int move){
            Node tmp = point;

            for(int i=0;i<move;i++){
                if(tmp.pre == null) break;
                tmp = tmp.pre;
            }
            point = tmp;
        }

        public void moveDown(int move){
            Node tmp = point;
            // System.out.println(move);
            for(int i=0;i<move;i++){
                // System.out.println(tmp.next == null ? null : tmp.next.data);
                if(tmp.next == null) break;
                tmp = tmp.next;
            }
            // System.out.println(tmp.data);
            // System.out.println(point.data);
            // this.point = tmp;
            point = tmp;

        }

        public void removeData(){
            Node tmp = point;

            // 삭제 후 backup 해두기
            back.add(tmp);
            if(tmp.pre != null){
                tmp.pre.next = tmp.next;
            }
            if(tmp.next != null){
                tmp.next.pre = tmp.pre;
                if(tmp.pre == null){
                    // 맨 처음인 경우
                    head = tmp.next;
                }
            }

            if(tmp.next == null){
                point = tmp.pre;
            }else{
                point = tmp.next;
            }

        }

        public void restoreData(){
            Node tmp = back.pollLast();
            sb.setCharAt(tmp.data, 'O');
            if(tmp.next != null){
                tmp.next.pre = tmp;
                if(tmp.pre == null){
                    head = tmp;
                }
            }
            if(tmp.pre != null){
                tmp.pre.next = tmp;
            }

        }

    }

    static class Node{
        int data;
        Node pre;
        Node next;

        public Node(int data){
            this.data = data;
            this.pre = null;
            this.next = null;
        }

    }
}
* */