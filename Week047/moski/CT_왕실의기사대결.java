import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class CT_왕실의기사대결 {
    static int L, N, Q, ans;
    // 실제 맵
    static int[][] map;
    // 기사들을 맵에 나타낼때
    static int[][]knightMap;
    // 기사가 살아있는지 체크
    static boolean[] isDie;
    // 움직임 여부 체크
    static boolean[] isMove;

    // 행 좌표
    static int[] R;
    // 열 좌표
    static int[] C;
    // 높이
    static int[] H;
    // 너비
    static int[] W;
    // 체력
    static int[] K;
    // 입은 데미지
    static int[] damage;

    // 위 오른 아래 왼
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        L = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        ans = 0;

        // 맵 초기화
        map = new int[L+1][L+1];

        // 맵 값 입력
        for(int i=1;i<=L;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1;j<=L;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        isDie = new boolean[N+1];
        R = new int[N+1];
        C = new int[N+1];
        H = new int[N+1];
        W = new int[N+1];
        K = new int[N+1];
        damage = new int[N+1];
        // 기사 값 입력
        for(int i=1;i<=N;i++){
            st = new StringTokenizer(br.readLine());
            R[i] = Integer.parseInt(st.nextToken());
            C[i] = Integer.parseInt(st.nextToken());
            H[i] = Integer.parseInt(st.nextToken());
            W[i] = Integer.parseInt(st.nextToken());
            K[i] = Integer.parseInt(st.nextToken());
        }

        knightMap = new int[L+1][L+1];
        // 맵에 기사단 배치
        for(int i=1;i<=N;i++){
            for(int j=R[i];j<R[i]+H[i];j++){
                for(int k=C[i];k<C[i]+W[i];k++){
                    // id값으로 맵에 표시
                    knightMap[j][k] = i;
                }
            }
        }

        for(int i=0;i<Q;i++){
            //Q번만큼 게임진행
            st = new StringTokenizer(br.readLine());
            int id = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            // 만약 기사가 살아있다면
            if(!isDie[id]){
                // id 를 가진 기사가 d방향으로 밀기
                start(id, d);
            }

        }

        for(int i=1;i<=N;i++){
            if(isDie[i]) continue;
//            System.out.println("기사id : " + i + " damage : " + damage[i]);
            ans += damage[i];
        }

        System.out.println(ans);

    }

    static void start(int id, int d){
        isMove = new boolean[N+1];
//        System.out.println("start!! id : " + id + " d : " + d);

        if(checkMove(id, d)){
            // 움직이는 것이 가능하다면
            // 움직이기
            for(int i=1;i<=N;i++){
                if(isDie[i]) continue;
                // 움직인 기사만 이동
                if(isMove[i]){

                    R[i] += dy[d];
                    C[i] += dx[d];
//                    System.out.println("move! r : " + R[i] + " c : " + C[i] + " id : " + i);
                }
            }

            knightMap = new int[L+1][L+1];
            // 재배치
            for(int i=1;i<=N;i++){
                if(isDie[i]) continue;
                for(int j=R[i];j<R[i]+H[i];j++){
                    for(int k=C[i];k<C[i]+W[i];k++){
                        // id값으로 맵에 표시
                        knightMap[j][k] = i;
                    }
                }
            }

            // 데미지까지 입히기

            for(int i=1;i<=N;i++){
                if(i == id) continue;
                if(isDie[i]) continue;
                if(isMove[i]){
                    // 트랩 개수
                    int cnt = 0;
                    for(int j=R[i];j<R[i]+H[i];j++){
                        for(int k=C[i];k<C[i]+W[i];k++){
                            if(map[j][k] == 1){
//                                System.out.println("trap! y : " + j + " x : " + k + " id : " + i);
                                cnt++;
                            }
                        }
                    }
                    damage[i] += cnt;
                    //만약 체력보다 데미지가 크다면
                    if(K[i] <= damage[i]) isDie[i] = true;
                }
            }
        }


    }

    static boolean checkMove(int id, int d){
        // d방향으로 미는 것에 막힘이 없는가?
        boolean ok = true;

        Queue<Integer> q = new ArrayDeque<>();
        Set<Integer> set = new HashSet<>();

        q.add(id);

        while(!q.isEmpty()) {
            int idx = q.poll();
            if (!ok) break;
            if (set.contains(idx)) continue;
            set.add(idx);

            int ny = R[idx];
            int nx = C[idx];

            switch (d) {
                // 윗쪽 방향일 때
                case 0:
                    ny = ny - 1;
                    // 만약 위쪽이 범위를 벗어난 경우
                    if (ny <= 0) {
                        ok = false;
                        break;
                    }
                    // 위쪽 방향을 훑는다.
                    for (int i = nx; i < nx + W[idx]; i++) {
                        // 가는길에 벽이 있다면 중지
                        if (map[ny][i] == 2) {
                            ok = false;
                            break;
                        }
                        // 그곳에 기사가 있다면?
                        if (knightMap[ny][i] != 0) {
                            q.add(knightMap[ny][i]);
                        }
                    }
                    break;

                case 1:
                    nx = nx + W[idx];
                    // 만약 오른쪽이 범위를 벗어난 경우
                    if (nx > L) {
                        ok = false;
                        break;
                    }
                    // 오른쪽 방향을 훑는다.
                    for (int i = ny; i < ny + H[idx]; i++) {
                        // 가는길에 벽이 있다면 중지
                        if (map[i][nx] == 2) {
                            ok = false;
                            break;
                        }

                        // 그곳에 기사가 있다면?
                        if (knightMap[i][nx] != 0) {
                            q.add(knightMap[i][nx]);
                        }
                    }
                    break;

                case 2:
                    ny = ny + H[idx];
                    // 만약 아래쪽이 범위를 벗어난 경우
                    if (ny > L) {
                        ok = false;
                        break;
                    }
                    // 아래쪽 방향을 훑는다.
                    for (int i = nx; i < nx + W[idx]; i++) {
                        // 가는길에 벽이 있다면 중지
                        if (map[ny][i] == 2) {
                            ok = false;
                            break;
                        }

                        // 그곳에 기사가 있다면?
                        if (knightMap[ny][i] != 0) {
//                            System.out.println("id ? " + knightMap[ny][i] + " ny : " + ny + " nx : " + nx);
                            q.add(knightMap[ny][i]);
                        }
                    }
                    break;

                case 3:
                    nx = nx - 1;
                    // 만약 왼쪽이 범위를 벗어난 경우
                    if (nx <= 0) {
                        ok = false;
                        break;
                    }
                    // 왼쪽 방향을 훑는다.
                    for (int i = ny; i < ny + H[idx]; i++) {
                        // 가는길에 벽이 있다면
                        if (map[i][nx] == 2) {
                            ok = false;
                            break;
                        }

                        // 그곳에 기사가 있다면?
                        if (knightMap[i][nx] != 0) {
                            q.add(knightMap[i][nx]);
                        }
                    }
                    break;
            }

        }

        for(int idx : set){
            isMove[idx] = true;
        }

        return ok;
    }
}
