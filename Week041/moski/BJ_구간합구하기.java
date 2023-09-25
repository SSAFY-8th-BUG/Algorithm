import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_구간합구하기 {

    static int N, M, K;

    static int H;
    static long[] tree;
    static long[] data;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        data = new long[N+1];
        for(int i=1;i<=N;i++){
            data[i] = Long.parseLong(br.readLine());
        }

        H = (int) Math.ceil(Math.log(N)/Math.log(2));
        int size = (int) Math.pow(2,H+1);
        tree = new long[size];

        init(1, 1, N);

        for(int i=0;i<M+K;i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());

            // 수 바꾸기
            if(a == 1){
                update(1, 1, N, b, c - data[b]);
                // 업데이트 후 실제로 바꾸기
                data[b] = c;
            }

            // 구간합 구하기
            if(a == 2){
                long s = sum(1, 1, N, b,(int) c);
                System.out.println(s);
            }

        }



    }

    static long init(int node, int start, int end){
        if(start == end){
            return tree[node] = data[start];
        }

        return tree[node] = init(node*2, start, (start+end)/2) + init(node*2+1, (start+end)/2 + 1, end);
    }

    static void update(int node, int start, int end, int idx, long diff){
        // 만약 범위 밖이면 멈추기
        if(idx < start || idx > end) return;

        tree[node] += diff;

        // 리프 노드가 아니면 아래 자식들도 확인
        if(start != end){
            update(node*2, start, (start+end)/2, idx, diff);
            update(node*2+1, (start+end)/2+1, end, idx, diff);
        }
    }

    // start,end : 배열의 시작과 끝
    // left, right : 누적합의 시작과 끝
    static long sum(int node, int start, int end, int left, int right){
        // 구하려는 범위 밖이면 스탑
        if(left > end || right < start){
            return 0;
        }

        // 범위를 완전히 포함하면 바로 더하기
        if(left <= start && right >= end){
            return tree[node];
        }

        // 그 외
        return sum(node*2, start, (start+end)/2, left, right) + sum(node*2+1, (start+end)/2+1, end, left, right);
    }
}
