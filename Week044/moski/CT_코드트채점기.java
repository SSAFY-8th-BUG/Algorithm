import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
public class CT_코드트채점기 {

    static final int MAX_D = 300;
    static final int MAX_N = 50000;
    static final int INF = 1987654321;
    static int Q, N;

    // 해당 도메인에서 해당 문제 id가 레디큐에 있는지 관리
    static TreeSet[] isInReadyQ = new TreeSet[MAX_D+1];
    // 현재 쉬고 있는 채점기들을 관리
    static PriorityQueue<Integer> restJudger = new PriorityQueue<>();
    // 각 채점기들이 채점할 때, 도메인의 인덱스를 저장
    static int[] judgingDomain = new int[MAX_N +  1];

    // 각 도메인별 start, gap, end(채점이 가능한 최소 시간)을 관리
    static int[] s = new int[MAX_D + 1];
    static int[] g = new int[MAX_D + 1];
    static int[] e = new int[MAX_D + 1];

    // 도메인을 관리하기 위해 cnt를 이용
    // 도메인 문자열을 int로 변환해주는 map을 관리
    static TreeMap<String, Integer> domainToIdx = new TreeMap<>();
    static int cnt;

    // 현재 채점 대기 큐에 있는 task의 개수를 관리
    static int ans;

    // 각 도메인별로 priority queue를 만들어
    // 우선순위가 가장 높은 url을 뽑아줌
    static PriorityQueue<Task>[] taskPq = new PriorityQueue[MAX_D + 1];

    public static void main(String[] args) throws Exception{
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Q = Integer.parseInt(br.readLine());

        for(int i=1;i<=MAX_D;i++){
            isInReadyQ[i] = new TreeSet<>();
            taskPq[i] = new PriorityQueue<>((o1, o2) -> o1.p == o2.p ? o1.t - o2.t : o1.p - o2.p);
        }

        for(int i=0; i<Q; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());
            switch (type){
                case 100:
                    int n = Integer.parseInt(st.nextToken());
                    String u = st.nextToken();
                    init(n, u);
                    break;
                case 200:
                    int t = Integer.parseInt(st.nextToken());
                    int p = Integer.parseInt(st.nextToken());
                    u = st.nextToken();
                    markTask(t, p, u);
                    break;
                case 300:
                    t = Integer.parseInt(st.nextToken());
                    tryTask(t);
                    break;
                case 400:
                    t = Integer.parseInt(st.nextToken());
                    int J_id = Integer.parseInt(st.nextToken());
                    stopTask(t, J_id);
                    break;
                case 500:
                    t = Integer.parseInt(st.nextToken());
                    System.out.println(finish(t));

            }
        }

    }

    static void init(int n, String u){
        N = n;
        cnt = 0;
        ans = 0;
        for(int i=1;i<=n;i++){
            restJudger.add(i);
        }
        // url에서 도메인 부분과 숫자부분을 나누어줌
        StringTokenizer st = new StringTokenizer(u, "/");

        String domain = st.nextToken();
        int id = Integer.parseInt(st.nextToken());

        // 만약 도메인이 처음 나온 도메인이면 domainToIdx 갱신
        if(!domainToIdx.containsKey(domain)){
            cnt++;
            domainToIdx.put(domain, cnt);
        }
        int domainIdx = domainToIdx.get(domain);

        // 만약 숫자 부분이 이미 레디큐에 있으면 중복되므로 패스
        if(isInReadyQ[domainIdx].contains(id)){
            return;
        }

        // 도메인 번호에 맞는 레디큐에 숫자부분을 넣음
        isInReadyQ[domainIdx].add(id);

        // 새로 들어온 task을 도메인에 맞춰 pq에 넣음
        Task task = new Task(0, 1, id);
        taskPq[domainIdx].add(task);

        // 채점 대기 큐에 값이 추가됐으므로 개수를 1추가
        ans++;
    }

    // 새로운 url을 입력받아 레디큐에 넣기
    static void markTask(int t, int p, String u){
       // url에서 도메인과 id 나누기
        StringTokenizer st = new StringTokenizer(u, "/");
        String domain = st.nextToken();
        int id = Integer.parseInt(st.nextToken());

        // 만약 도메인이 처음 나온 도메인이면 domainToIdx 갱신
        if(!domainToIdx.containsKey(domain)){
            cnt++;
            domainToIdx.put(domain, cnt);
        }
        int domainIdx = domainToIdx.get(domain);

        // 만약 숫자 부분이 이미 레디큐에 있다면 중복이므로 넘어갑니다.
        if(isInReadyQ[domainIdx].contains(id)){
            return;
        }

        // 도메인 id에 맞는 레디큐에 id 값 넣기
        isInReadyQ[domainIdx].add(id);

        // 새로 들어온 url을 도메인에 맞춰 pq에 넣기
        Task task = new Task(t, p, id);
        taskPq[domainIdx].add(task);

        // 채점 대기 큐에 값이 추가됐으므롤 개수를 추가
        ans++;
    }

    // 채점 시도
    static void tryTask(int t){
        // 모든  채점기가 돌아가고 있다면 리턴
        if(restJudger.isEmpty()) return;

        // 우선순위가 높은 task를 탐색
        int minDomainIdx = 0;
        Task minTask = new Task(0, INF, 0);

        for(int i=1; i <= cnt; i++){
            // 만약 채점중이거나, 현재 시간에 이용할 수 없다면 패스
            if(e[i] > t) continue;

            // 만약 i번 도메인에 해당하는 url이 존재한다면
            // 해당 도메인에서 가장 우선순위가 높은 url을 뽑고 갱신
            if(!taskPq[i].isEmpty()){
                Task task = taskPq[i].peek();

                if(minTask.p > task.p || (minTask.p == task.p && minTask.t > task.t)){
                    minTask = task;
                    minDomainIdx = i;
                }
            }
        }

        // 만약 가장 우선순위가 높은 url이 존재하면
        // 해당 도메인과 쉬고 잇는 가장 낮은 번호의 채점기를 연결함
        if(minDomainIdx > 0){
            int judgerIdx = restJudger.poll();

            // 해당 도메인의 가장 우선순위가 높은 task 지우기
            taskPq[minDomainIdx].poll();

            // 도메인의 start, end 갱신
            s[minDomainIdx] = t;
            e[minDomainIdx] = INF;

            // judgerIdx번 채점기가 채점하고 있는 도메인 번호 갱신
            judgingDomain[judgerIdx] = minDomainIdx;

            // 레디큐에서 해당 task의 숫자를 지움
            isInReadyQ[minDomainIdx].remove(minTask.id);

            // 채점 대기 큐에서 값이 지워졌으므로 개수 감소
            ans--;
        }
    }

    static void stopTask(int t, int J_id){
        // 만약 id번 채점기가 채점 중이 아닐 경우 스킵
        if(judgingDomain[J_id] == 0) return;

        // id번 채점기를 마무리함
        restJudger.add(J_id);
        int domainIdx = judgingDomain[J_id];
        judgingDomain[J_id] = 0;

        // 해당 도메인의 gap, end 갱신
        g[domainIdx] = t - s[domainIdx];
        e[domainIdx] = s[domainIdx] + 3 * g[domainIdx]; // 이시간까지 쉬는 도메인
    }

    static int finish(int t){
        return ans;
    }

    static class Task{
        int t;
        // 우선순위
        int p;
        int id;

        public Task(int t, int p, int id){
            this.t = t;
            this.p = p;
            this.id = id;
        }

    }
}
