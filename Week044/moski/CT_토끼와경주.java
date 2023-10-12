import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class CT_토끼와경주 {
    static final int MAX_P = 10_000_000;
    static int Q, P, N, M;
    static int maxScore;

    // 맵 배열
    static int[][] map;

    // 토끼 정보를 저장할 배열
    static Rabbit[] rabbits;

    static PriorityQueue<Rabbit> rabbitPq = new PriorityQueue<>((o1, o2)->{
        if(o1.cnt == o2.cnt){
            if(o1.y + o1.x == o2.y + o2.x){
                if(o1.y == o2.y){
                    if(o1.x == o2.x){
                        return o1.p - o2.p;
                    }else{
                        return o1.x - o2.x;
                    }
                }else{
                    return o1.y - o2.y;
                }
            }else{
                return (o1.y + o1.x) - (o2.y + o2.x);
            }
        }
        return o1.cnt - o2.cnt;
    });

    static PriorityQueue<Rabbit> bonusRabbit = new PriorityQueue<>((o1, o2) -> {
        if((o1.y+o1.x) != (o2.y+o2.x)) {
            return (o2.y + o2.x) > (o1.y + o1.x) ? 1 : -1;
        }
        if(o2.y != o1.y) {
            return o2.y > o1.y ? 1 : -1;
        }

        if(o2.x != o1.x) {
            return o2.x > o1.x ? 1 : -1;
        }

        return o2.p > o1.p ? 1 : -1 ;

    });

    static PriorityQueue<Node> nodePq = new PriorityQueue<>();
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception{
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Q = Integer.parseInt(br.readLine());
        for(int i=0; i<Q; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());

            switch (type){
                case 100:
                    N = Integer.parseInt(st.nextToken());
                    M = Integer.parseInt(st.nextToken());
                    P = Integer.parseInt(st.nextToken());

                    maxScore = 0;
                    map = new int[N+1][M+1];

                    rabbits = new Rabbit[P+1];

                    for(int j=1;j<=P;j++){
                        int p = Integer.parseInt(st.nextToken());
                        int d = Integer.parseInt(st.nextToken());
                        rabbits[j] = new Rabbit(p, d, 0, 0, 1, 1);
                        rabbitPq.add(rabbits[j]);
                        bonusRabbit.add(rabbits[j]);
                    }
                    break;
                case 200:
                    int K = Integer.parseInt(st.nextToken());
                    int S = Integer.parseInt(st.nextToken());
                    start(K, S);
                    break;
                case 300:
                    int p = Integer.parseInt(st.nextToken());
                    int L = Integer.parseInt(st.nextToken());
                    updateLength(p,L);
                    break;
                case 400:
                    finish();
                    break;
            }
        }
    }

    static void start(int k, int s){
        // K번 반복
        for(int i=0;i<k;i++){
            // 우선순위가 높은 토끼 차출
            Rabbit rabbit = rabbitPq.poll();

            int d = rabbit.d;

            // 우선 순위를 뽑아줄 nodePq 초기화
            nodePq.clear();
            // 상하좌우 순
            for(int j=0;j<4;j++){
                int ny = rabbit.y;
                int nx = rabbit.x;
                int nd = d;
                switch (j){
                    case 0: // 상
                        nd = d % (N * 2 - 2);
                        // 이동 거리가 벽에 닿이지 않는 경우
                        if(nd <= ny - 1){
                            ny = ny + nd * dy[j];
                        }else if(nd <= ny + N - 2){ // 한번 방향 튼 경우
                            ny = 1 + (nd - (ny - 1)) * dy[j+1];
                        }else{ // 두번 방향 튼 경우
                            ny = N + (nd -(ny + N - 2)) * dy[j];
                        }
                        break;
                    case 1: // 하
                        nd = d % (N * 2 - 2);
                        // 이동 거리가 벽에 닿이지 않는 경우
                        if(nd <= N - ny){
                            ny = ny + nd * dy[j];
                        }else if(nd <= N * 2 - ny - 1){ // 한번 방향 튼 경우
                            ny = N + (nd - (N - ny)) * dy[j-1];
                        }else{ // 두번 방향 튼 경우
                            ny = 1 + (nd -(N * 2 - ny - 1)) * dy[j];
                        }
                        break;
                    case 2:
                        nd = d % (M * 2 - 2);
                        // 이동 거리가 벽에 닿이지 않는 경우
                        if(nd <= nx - 1){
                            nx = nx + nd * dx[j];
                        }else if(nd <= nx + M - 2){ // 한번 방향 튼 경우
                            nx = 1 + (nd - (nx - 1)) * dx[j+1];
                        }else{ // 두번 방향 튼 경우
                            nx = M + (nd -(nx + M - 2)) * dx[j];
                        }
                        break;
                    case 3:
                        nd = d % (M * 2 - 2);
                        // 이동 거리가 벽에 닿이지 않는 경우
                        if(nd <= M - nx){
                            nx = nx + nd * dx[j];
                        }else if(nd <= M * 2 - nx - 1){ // 한번 방향 튼 경우
                            nx = M + (nd - (M - nx)) * dx[j-1];
                        }else{ // 두번 방향 튼 경우
                            nx = 1 + (nd -(M * 2 - nx - 1)) * dx[j];
                        }
                        break;
                }
                nodePq.add(new Node(ny, nx));
            }
            // 우선순위가 높은 칸 꺼내기
            Node node = nodePq.poll();
            // 업데이트
            rabbit.y = node.y;
            rabbit.x = node.x;

            int rabbitIdx = rabbit.p;
            for(int j=1;j<=P;j++){
                if(rabbits[j].p == rabbitIdx) continue;
                rabbits[j].score += node.x + node.y;
                if(rabbits[j].score > maxScore){
                    maxScore = rabbits[j].score;
                }
            }
            rabbit.cnt++;
            rabbitPq.add(rabbit);

            System.out.println("p: " + rabbit.p + " cnt : "  + rabbit.cnt + " score : " + rabbit.score);
            System.out.println("y : "  + node.y + " x : " + node.x);

        }
        // 보너스 점수 받을 토끼 선정
        Rabbit bonus = bonusRabbit.poll();
        Rabbit bonus2 = bonusRabbit.poll();
        bonus.score += s;
        bonusRabbit.add(bonus);
        bonusRabbit.add(bonus2);
        System.out.println("p : " + bonus.p + " score : " + bonus.score);
        System.out.println("y : "  + bonus.y + " x : " + bonus.x);
        System.out.println("p : " + bonus2.p + " score : " + bonus2.score);
        System.out.println("y : "  + bonus2.y + " x : " + bonus2.x);
        if(bonus.score > maxScore){
            maxScore = bonus.score;
        }
    }

    static void updateLength(int p, int L){
        for(int j=1;j<=P;j++){
            if(rabbits[j].p == p) {
                rabbits[j].d *= L;
                return;
            }
        }
    }

    static void finish(){
        System.out.println(maxScore);
    }

    static class Rabbit{
        // 고유번호
        int p;
        // 이동거리
        int d;
        // 점프 횟수
        int cnt;
        int score;
        int y,x;

        public Rabbit(int p, int d, int cnt, int score, int y, int x){
            this.p = p;
            this.d = d;
            this.cnt = cnt;
            this.score = score;
            this.y = y;
            this.x = x;
        }
    }

    static class Node implements Comparable<Node>{
        int y;
        int x;

        public Node(int y, int x){
            this.y = y;
            this.x = x;
        }

        @Override
        public int compareTo(Node o){
            if(this.y+this.x == o.y+o.x){
                if(this.y == o.y){
                    return o.x - this.x;
                }else{
                    return o.y - this.y;
                }
            }
            return (o.y+o.x) - (this.y+this.x);
        }

    }

}
