import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CT_메이즈러너 {
    static int N, M, K, ans;
    // 회전 시킬 사이즈
    static int sx, sy, size;
    static int[][] player;
    static int[] exit;
    static int[][] map;
    static int[][] nextMap;

    public static void main(String[] args) throws Exception{
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        exit = new int[2];

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        ans = 0;
        sx = 0;
        sy = 0;
        size = 0;

        map = new int[N+1][N+1];
        nextMap = new int[N+1][N+1];

        // 0: y좌표 1: x좌표
        player = new int[M][2];

        for(int i=1;i<=N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1;j<=N;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            player[i][0] = Integer.parseInt(st.nextToken());
            player[i][1] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        exit[0] = Integer.parseInt(st.nextToken());
        exit[1] = Integer.parseInt(st.nextToken());

        for(int i=0;i<K;i++){
            // 이동하고
            movePlayer();
            // 모두가 탈출했는지 체크
            boolean isAllEscaped = true;
            for(int j=0;j<M;j++){
                if(!(player[j][0] == exit[0] && player[j][1] == exit[1])){
                    isAllEscaped = false;
                    break;
                }
            }

            // 모두 탈출했다면 종료
            if(isAllEscaped) break;
            // 사각형 찾기
            findMinBox();
            // 돌리고
            rotateBox();
            rotatePlayerAndExit();
        }

        System.out.println(ans);
        System.out.println(exit[0] + " " + exit[1]);


    }

    static void movePlayer(){
        for(int i=0;i<M;i++){
            // 이미 출구에 있는 경우 스킵
            if(player[i][0] == exit[0] && player[i][1] == exit[1]){
                continue;
            }

            // 행이 다른 경우 행을 이동
            if(player[i][0] != exit[0]){
                int ny = player[i][0];
                int nx = player[i][1];

                if(exit[0] > ny) ny++;
                else ny--;

                // 벽 여부 체크
                if(map[ny][nx] == 0){
                    player[i][0] = ny;
                    player[i][1] = nx;
                    ans++;
                    // 이동했다면 다음 플레이어 진행
                    continue;
                }
            }

            // 열이 다른 경우 열 이동
            if(player[i][1] != exit[1]){
                int ny = player[i][0];
                int nx = player[i][1];

                if(exit[1] > nx) nx++;
                else nx--;

                // 벽 여부 체크
                if(map[ny][nx] == 0){
                    player[i][0] = ny;
                    player[i][1] = nx;
                    ans++;
                    // 이동했다면 다음 플레이어 진행
                    continue;
                }
            }
        }
    }

    // 플레이어와 출구를 포함한 가장 작은 정사각형 찾기
    static void findMinBox(){
        for(int i=2;i<=N;i++){
            for(int r=1;r<=N;r++){
                for(int c=1;c<=N;c++){
                    int nr = r + i - 1;
                    int nc = c + i - 1;

                    // 출구가 정사각형 안에 없으면 스킵
                    if(!(r <= exit[0] && exit[0] <= nr && c <= exit[1] && exit[1] <= nc)){
                        continue;
                    }

                    // 참가자 여부 확인
                    boolean isPlayer = false;
                    for(int j=0; j<M;j++){
                        if(r <= player[j][0] && player[j][0] <= nr && c <= player[j][1] && player[j][1] <= nc){
                            // 출구에 있는 경우 제외
                            if(player[j][0] == exit[0] && player[j][1] == exit[1]) continue;
                            isPlayer = true;
                        }
                    }

                    // 만약 사각형 안에 플레이어가 있다면
                    // sx, sy, size 정보를 갱신하고 종료
                    if(isPlayer){
                        sy = r;
                        sx = c;
                        size = i;

                        return;
                    }
                }
            }
        }
    }

    // 사각형 회전
    static void rotateBox(){
        // 우선 정사각형 안에 벽들을 1 감소
        for(int i=sy; i<sy+size;i++){
            for(int j=sx;j<sx+size;j++){
                if(map[i][j] > 0) map[i][j]--;
            }
        }

        // 사각형 회전 시계방향 90도
        for(int i = sy; i<sy+size ; i++){
            for(int j = sx; j<sx+size; j++){
                // (sy,sx)를 (0,0)으로 옮겨주는 변환을 진행
                int ny = i - sy;
                int nx = j - sx;
                // 변환된 상태에서는 회전 이후 좌표가 (y, x) -> (x, size - y - 1)이 됨. <= 지린다.
                int ry = nx;
                int rx = size - ny - 1;
                // 변환된 좌표에 다시 sy, sx를 더해준다.
                nextMap[ry + sy][rx + sx] = map[i][j];
            }
        }

        // nextMap 값을 현재 map에 동기화 시킴
        for(int i = sy; i < sy + size ; i++) {
            for (int j = sx; j < sx + size; j++) {
                map[i][j] = nextMap[i][j];
            }
        }
    }

    // 출구 및 플레이어 회전
    static void rotatePlayerAndExit(){
        // 플레이어
        for(int i=0;i<M;i++){
            int y = player[i][0];
            int x = player[i][1];
            // 해당 참가자가 사각형 안에 포함되는지 확인
            if(sy <= y && y < sy+size && sx <= x && x < sx+size){
                // 위와 동일한 회전 과정을 거침
                int ny = y - sy;
                int nx = x - sx;
                int ry = nx;
                int rx = size - ny - 1;

                player[i][0] = ry + sy;
                player[i][1] = rx + sx;

            }
        }

        // 출구
        int y = exit[0];
        int x = exit[1];

        if(sy <= y && y < sy+size && sx <= x && x < sx+size){
            // 위와 동일한 회전 과정을 거침
            int ny = y - sy;
            int nx = x - sx;
            int ry = nx;
            int rx = size - ny - 1;

            exit[0] = ry + sy;
            exit[1] = rx + sx;

        }
    }
}
