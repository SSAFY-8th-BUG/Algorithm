import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class CT_포탑부수기 {
    static int N, M, K;
    // 터렛이 쏜 타이밍
    static int[][] attackTiming;
    static int[][] damage;
    static boolean[][] visited;
    static boolean[][] isActive;

    static Attacker attacker;
    static Target target;

    // 0~3 : 우하좌상 0~7 모든 부위 파괴
    static int[] dy = {0, 1, 0, -1, 1, -1, 1, -1};
    static int[] dx = {1, 0, -1, 0, 1, 1, -1, -1};
    public static void main(String[] args) throws Exception{
        // 여기에 코드를 작성해주세요.
        //turret
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        attackTiming = new int[N+1][M+1];
        damage = new int[N+1][M+1];

        for(int i=1; i<=N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=M;j++){
                damage[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=1;i<=K;i++){
            isActive = new boolean[N+1][M+1];
            // 공격자 선정
            attacker = selectAttacker();
            if(attacker == null) break;
            // 타겟 선정
            target = selectTarget(attacker.y, attacker.x);
            if(target == null) break;

            // active true
            isActive[attacker.y][attacker.x] = true;
            // 공격자 시점 갱신
            attackTiming[attacker.y][attacker.x] = i;
            // 공격자 공격력 상승
            attacker.damage += N+M;
            damage[attacker.y][attacker.x] += N+M;


            // 레이저 공격 시도
            if(!laserAttack(attacker.y, attacker.x)){
                // 실패시 포탄 공격 시도
                cannonAttack();
            }

            // 아무런 활동하지 않은 포탑은 +1
            for(int j=1;j<=N;j++){
                for(int k=1;k<=M;k++){
                    if(isActive[j][k] || damage[j][k] == 0) continue;
                    damage[j][k]++;
                }
            }

        }
        int ans = 0;
        for(int i=1;i<=N;i++){
            for(int j=1;j<=M;j++){
                ans = Math.max(ans, damage[i][j]);
            }
        }
        System.out.println(ans);

    }

    static Attacker selectAttacker(){
        PriorityQueue<Attacker> attackerPq = new PriorityQueue<>();
        for(int i=1; i<=N; i++){
            for(int j=1; j<=M; j++){
                if(damage[i][j] != 0) attackerPq.add(new Attacker(damage[i][j], i, j, attackTiming[i][j]));
            }
        }

        return attackerPq.poll();
    }

    static Target selectTarget(int y, int x){
        PriorityQueue<Target> targetPq = new PriorityQueue<>();
        for(int i=1; i<=N; i++){
            for(int j=1; j<=M; j++){
                // 공격자의 좌표와 같으면 스킵
                if(i==y && j==x) continue;
                if(damage[i][j] != 0) targetPq.add(new Target(damage[i][j], i, j, attackTiming[i][j]));
            }
        }

        return targetPq.poll();
    }

    static boolean laserAttack(int y, int x){
        //bfs
        visited = new boolean[N+1][M+1];
        visited[y][x] = true;
        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(y, x, null));

        while(!q.isEmpty()){
            Node node = q.poll();

            // 만약 타겟 좌표랑 같아 진다면
            if(node.y == target.y && node.x == target.x){
                // 데미지 주기
                damage[node.y][node.x] = damage[node.y][node.x] <= attacker.damage ? 0 : damage[node.y][node.x] - attacker.damage;
                // 활동 내역 기록
                isActive[node.y][node.x] = true;
                Node preNode = node.preNode;
                int d = attacker.damage / 2;
                // 절반 데미지 주기
                while(preNode.preNode!=null){
                    damage[preNode.y][preNode.x] = damage[preNode.y][preNode.x] <= d ? 0 : damage[preNode.y][preNode.x] - d;
                    isActive[preNode.y][preNode.x] = true;
//                    System.out.println("y : " + preNode.y + " x : "+ preNode.x);
//                    System.out.println("damage : " + damage[preNode.y][preNode.x]);
                    preNode = preNode.preNode;
                }
                return true;
            }
            for(int i=0;i<4;i++){
                int ny = node.y + dy[i];
                int nx = node.x + dx[i];

                if(ny == 0) ny = N;
                if(ny == N+1) ny = 1;
                if(nx == 0) nx = M;
                if(nx == M+1) nx = 1;

//                System.out.println("여기 왜 안돌아 node.y : " + ny + " node.x : " + nx);
                if(visited[ny][nx] || damage[ny][nx] == 0) continue;
                visited[ny][nx] = true;
//                System.out.println("ny : " + ny + " nx : "+ nx);
                q.add(new Node(ny, nx, node));
            }
        }

        return false;
    }

    static void cannonAttack(){
        damage[target.y][target.x] = damage[target.y][target.x] <= attacker.damage ? 0 : damage[target.y][target.x] - attacker.damage;
        isActive[target.y][target.x] = true;
        // 모든 방향 타격
        int d = attacker.damage / 2;
        for(int i=0;i<8;i++){
            int ny = target.y + dy[i];
            int nx = target.x + dx[i];

            if(ny == 0) ny = N;
            if(ny == N+1) ny = 1;
            if(nx == 0) nx = M;
            if(nx == M+1) nx = 1;

            if(damage[ny][nx] == 0) continue;
            if(ny == attacker.y && nx == attacker.x) continue;
            damage[ny][nx] = damage[ny][nx] <= d ? 0 : damage[ny][nx] - d;
            isActive[ny][nx] = true;
        }
    }


    static class Turret{
        int damage;
        int y,x;
        int t;

        public Turret(int damage, int y, int x, int t){
            this.damage = damage;
            this.y = y;
            this.x = x;
            this.t = t;
        }
    }

    static class Attacker extends Turret implements Comparable<Attacker>{

        public Attacker(int damage, int y, int x, int t) {
            super(damage, y, x, t);
        }

        @Override
        public int compareTo(Attacker o) {
            if(this.damage != o.damage) return this.damage - o.damage;
            if(this.t != o.t) return o.t - this.t;
            if(this.y + this.x != o.y + o.x) return (o.y + o.x) - (this.y + this.x);
            return o.x - this.x;
        }
    }

    static class Target extends Turret implements Comparable<Target>{

        public Target(int damage, int y, int x, int t) {
            super(damage, y, x, t);
        }

        @Override
        public int compareTo(Target o) {
            if(this.damage != o.damage) return o.damage - this.damage;
            if(this.t != o.t) return this.t - o.t;
            if(this.y + this.x != o.y + o.x) return (this.y + this.x) - (o.y + o.x);
            return this.x - o.x;
        }
    }

    static class Node{
        int y,x;
        Node preNode;

        public Node(int y, int x, Node preNode){
            this.y = y;
            this.x = x;
            this.preNode = preNode;
        }
    }
}
