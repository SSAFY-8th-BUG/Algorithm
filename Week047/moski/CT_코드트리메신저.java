import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class CT_코드트리메신저 {

    static int N, Q;
    static int ans;
    static boolean[] isAlarm;
    static int[] authority;
    // 없으면 -1 로 표기
    static int[] parent;
    static List<Integer>[] children;

    public static void main(String[] args) throws Exception{
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        ans = 0;

        for(int i=0;i<Q;i++){
            st = new StringTokenizer(br.readLine());
            int order = Integer.parseInt(st.nextToken());

            switch (order){
                case 100:
                    int[] parents = new int[N+1];
                    int[] powers = new int[N+1];

                    for(int j=1;j<=N;j++){
                        parents[j] = Integer.parseInt(st.nextToken());
                    }
                    for(int j=1;j<=N;j++){
                        powers[j] = Integer.parseInt(st.nextToken());
                    }

                    init(parents, powers);
                    break;

                case 200:
                    int c = Integer.parseInt(st.nextToken());

                    setAlarm(c);
                    break;

                case 300:
                    int idx = Integer.parseInt(st.nextToken());
                    int power = Integer.parseInt(st.nextToken());

                    setAuthority(idx, power);
                    break;

                case 400:
                    int c1 = Integer.parseInt(st.nextToken());
                    int c2 = Integer.parseInt(st.nextToken());

                    swapParent(c1, c2);
                    break;

                case 500:
                    int c5 = Integer.parseInt(st.nextToken());

                    getRoomCnt(c5);
                    break;
            }
        }
    }

    // (1) 사내 메신저 준비
    static void init(int[] parents, int[] powers){
        // 초기 세팅
        isAlarm = new boolean[N+1];
        authority = new int[N+1];
        parent = new int[N+1];
        // 자식은 최대 2
        children = new List[N+1];

        for(int i=0;i<=N;i++){
            children[i] = new ArrayList<>();
        }

        for (int i = 1; i <= N; i++) {
            authority[i] = powers[i];
        }

        for (int i = 1; i <= N; i++) {
            parent[i] = parents[i];
            // 자식 추가
            children[parents[i]].add(i);
        }

    }

    // (2) 알람망 설정
    static void setAlarm(int c){
        isAlarm[c] = !isAlarm[c];
    }

    // (3) 권한 세기 변경
    static void setAuthority(int idx, int power){
        authority[idx] = power;
    }

    // (4) 부모 채팅방 교환
    static void swapParent(int c1, int c2){
        // 같은 depth임을 가정
        int p1 = parent[c1];
        int p2 = parent[c2];

        if(p1 == p2) return;

        // 부모 교체
        parent[c2] = p1;
        parent[c1] = p2;

        // 자식 교체 - 해당 자식들만 바꾸는거니까.. 다 바꿀 필욘 없네..

        children[p1].removeIf(o -> c1 == o);
        children[p2].removeIf(o -> c2 == o);
        children[p1].add(c2);
        children[p2].add(c1);
    }

    // (5) 알림을 받을 수 있는 채팅방 수 조회
    static void getRoomCnt(int c){
        // c에서 부터 내려간다.
        // 1. 재귀로 풀어본다.(원래 방식) - 헉스 시간초과..!

//        int cnt = checkRoom(c, 1);
//        System.out.println(cnt);

        // 2. queue로도 풀어보자.
        int cnt = 0;
        Queue<int[]> q = new ArrayDeque<>();

        for(int i=0;i<children[c].size();i++){
            q.add(new int[] {children[c].get(i), 1});
        }

        while(!q.isEmpty()){
            int[] room = q.poll();
            int idx = room[0];
            int depth = room[1];
            if(isAlarm[idx]) continue;
            if(authority[idx] >= depth) cnt++;
            for(int i=0;i<children[idx].size();i++){
                q.add(new int[] {children[idx].get(i), depth+1});
            }
        }

        System.out.println(cnt);
    }

//    static int checkRoom(int idx, int depth){
//        int cnt = 0;
//        for(int i=0;i<children[idx].size();i++){
//            int id = children[idx].get(i);
//            // 스모크면 넘기기
//            if(isAlarm[id]) continue;
//            // 힘이 딸리면 넘기기
//            if(authority[id] < depth) {
//                cnt += checkRoom(id, depth+1);
//            }else{
//                cnt += checkRoom(id, depth+1) + 1;
//            }
////            System.out.println("??");
//        }
////        System.out.println("idx : " + idx);
//        return cnt;
//    }

}
