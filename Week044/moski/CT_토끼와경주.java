import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class CT_토끼와경주 {
    static final int MAX_N = 2000;

    static int Q, P, N, M;
    static long totalSum;

    static int[] p = new int[MAX_N+1];

    static int[] d = new int[MAX_N+1];

    static int[] jump = new int[MAX_N+1];

    static long[] result = new long[MAX_N+1];

    static Node[] point = new Node[MAX_N+1];

    static HashMap<Integer, Integer> pToIdx = new HashMap<>();

    static boolean[] isMove = new boolean[MAX_N+1];


    // 토끼 정보를 저장할 배열
    static Rabbit[] rabbits;

    static PriorityQueue<Rabbit> rabbitPq = new PriorityQueue<>((o1, o2)->{
        if(o1.cnt != o2.cnt){
            return o1.cnt - o2.cnt;
        }
        if(o1.y + o1.x != o2.y + o2.x){
            return (o1.y + o1.x) - (o2.y + o2.x);
        }
        if(o1.y != o2.y){
            return o1.y - o2.y;
        }
        if(o1.x != o2.x){
            return o1.x - o2.x;
        }
        return o1.p - o2.p;
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

                    totalSum = 0;

                    rabbits = new Rabbit[P+1];

                    for(int j=1;j<=P;j++){
                        int pp = Integer.parseInt(st.nextToken());
                        int dd = Integer.parseInt(st.nextToken());
//                        rabbits[j] = new Rabbit(pp, dd, 0, 0, 1, 1);
                        p[j] = pp;
                        d[j] = dd;
                        pToIdx.put(p[j], j);
                        point[j] = new Node(1,1);
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

        rabbitPq.clear();

//        for(int i=1;i<=P;i++){
//            rabbits[i].isMove = false;
//            rabbitPq.add(rabbits[i]);
//        }

        for(int i=1;i<=P;i++){
            isMove[i] = false;
            rabbitPq.add(new Rabbit(p[i], jump[i], point[i].y, point[i].x));
        }

        // K번 반복
        for(int i=0;i<k;i++){
            // 우선순위가 높은 토끼 차출
            Rabbit rabbit = rabbitPq.poll();

            // 우선 순위를 뽑아줄 nodePq 초기화
            nodePq.clear();
             nodePq.add(new Node(0, 0));
//            Node node = new Node(0, 0);
            // 상하좌우 순
            for(int j=0;j<4;j++){
                int ny = rabbit.y;
                int nx = rabbit.x;
//                int nd = rabbit.d;
                int nd = d[pToIdx.get(rabbit.p)];
                switch (j){
                    case 0: // 상
                        nd %= (N * 2 - 2);
                        // 이동 거리가 벽에 닿이지 않는 경우
                        if(nd >= ny - 1) {
                            nd -= ny - 1;
                            ny = 1;
                        }else{
                            ny += nd * dy[j];
                            nd = 0;
                        }
                        if(nd >= N - ny){ // 한번 방향 튼 경우
                            nd -= N - ny;
                            ny = N;
                        }else{
                            ny += nd * dy[j+1];
                            nd = 0;
                        }
                        ny += nd * dy[j];
                        break;
                    case 1: // 하
                        nd %= (N * 2 - 2);
                        // 이동 거리가 벽에 닿이지 않는 경우
                        if(nd >= N - ny){
                            nd -= N - ny;
                            ny = N;
                        }else{
                            ny += nd * dy[j];
                            nd = 0;
                        }
                        if(nd >= ny - 1){ // 한번 방향 튼 경우
                            nd -= ny - 1;
                            ny = 1;
                        }else{
                            ny += nd * dy[j-1];
                            nd = 0;
                        }
                        ny += nd * dy[j];
                        break;
                    case 2:
                        nd %= (M * 2 - 2);
                        // 이동 거리가 벽에 닿이지 않는 경우
                        if(nd >= nx - 1) {
                            nd -= nx - 1;
                            nx = 1;
                        }else{
                            nx += nd * dx[j];
                            nd = 0;
                        }
                        if(nd >= M - nx){ // 한번 방향 튼 경우
                            nd -= M - nx;
                            nx = M;
                        }else{
                            nx += nd * dx[j+1];
                            nd = 0;
                        }
                        nx += nd * dx[j];
                        break;
                    case 3:
                        nd %= (M * 2 - 2);
                        // 이동 거리가 벽에 닿이지 않는 경우
                        if(nd >= M - nx){
                            nd -= M - nx;
                            nx = M;
                        }else{
                            nx += nd * dx[j];
                            nd = 0;
                        }
                        if(nd >= nx - 1){ // 한번 방향 튼 경우
                            nd -= nx - 1;
                            nx = 1;
                        }else{ // 두번 방향 튼 경우
                            nx += nd * dx[j-1];
                            nd = 0;
                        }
                        nx += nd * dx[j];
                        break;
                }
                Node newNode = new Node(ny, nx);
                 nodePq.add(newNode);
//                if(cmp(node, newNode)) node = newNode;
            }
            // 우선순위가 높은 칸 꺼내기
             Node node = nodePq.poll();
            // 업데이트
            rabbit.y = node.y;
            rabbit.x = node.x;

            rabbit.cnt++;
            rabbitPq.add(rabbit);

//            rabbit.score -= node.x + node.y;
//            totalSum += node.x + node.y;
//
//            rabbit.cnt++;
//            rabbit.isMove = true;
//            rabbitPq.add(rabbit);

            // 실제 값 갱신
            int nexIdx = pToIdx.get(rabbit.p);
            point[nexIdx] = new Node(rabbit.y, rabbit.x);
            jump[nexIdx]++;

            isMove[nexIdx] = true;

            result[nexIdx] -= node.x + node.y;
            totalSum += node.x + node.y;
        }
        // 보너스 점수 받을 토끼 선정
//        Rabbit bonus = new Rabbit(0, 0, 0, 0, 0, 0);
//
//        while(!rabbitPq.isEmpty()){
//            Rabbit rabbit = rabbitPq.poll();
//
//            if(!rabbit.isMove) continue;
//
//            if(cmp(bonus, rabbit)) bonus = rabbit;
//
//        }
//
//        bonus.score += s;
        Rabbit bonus = new Rabbit(0, 0, 0, 0);

        while(!rabbitPq.isEmpty()){
            Rabbit rabbit = rabbitPq.poll();

            if(!isMove[pToIdx.get(rabbit.p)]) continue;

            if(cmp(bonus, rabbit)) bonus = rabbit;

        }

        result[pToIdx.get(bonus.p)] += s;
    }

    public static boolean cmp(Rabbit a, Rabbit b) {
        if(a.x + a.y != b.x + b.y) return a.x + a.y < b.x + b.y;
        if(a.y != b.y) return a.y < b.y;
        if(a.x != b.x) return a.x < b.x;
        return a.p < b.p;
    }

    public static boolean cmp(Node a, Node b) {
        if(a.x + a.y != b.x + b.y) return a.x + a.y < b.x + b.y;
        if(a.y != b.y) return a.y < b.y;
        return a.x < b.x;
    }

    static void updateLength(int p, int L){
//        for(int i=1;i<=P;i++){
//            if(rabbits[i].p == p) rabbits[i].d *= L;
//            return;
//        }
        int idx = pToIdx.get(p);
        d[idx] *= L;
    }

    static void finish(){
        long ans = 0;
        for(int i=1; i<=P; i++){
//            ans = Math.max(ans, rabbits[i].score + totalSum);
            ans = Math.max(ans, result[i] + totalSum);
        }
        System.out.println(ans);
    }

//    static class Rabbit{
//        // 고유번호
//        int p,d;
//        // 이동횟수
//        int cnt;
//        long score;
//        int y,x;
//
//        boolean isMove;
//
//        public Rabbit(int p, int d, int cnt, long score, int y, int x){
//            this.p = p;
//            this.d = d;
//            this.cnt = cnt;
//            this.score = score;
//            this.y = y;
//            this.x = x;
//            this.isMove = false;
//        }
//    }

    static class Rabbit implements Comparable<Rabbit>{
        // 고유번호
        int p;
        // 이동횟수
        int cnt;
        int y,x;


        public Rabbit(int p, int cnt, int y, int x){
            this.p = p;
            this.cnt = cnt;
            this.y = y;
            this.x = x;

        }

        @Override
        public int compareTo(Rabbit o) {
            if(this.cnt != o.cnt){
                return this.cnt - o.cnt;
            }
            if(this.y + this.x != o.y + o.x){
                return (this.y + this.x) - (o.y + o.x);
            }
            if(this.y != o.y){
                return this.y - o.y;
            }
            if(this.x != o.x){
                return this.x - o.x;
            }
            return this.p - o.p;
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
