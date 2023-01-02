import java.io.*;

public class bj_6137_tableMinPark {

    static int N, front, last;
    static char[] T;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        T = new char[N];
        for (int i = 0; i < N; i++) {
            T[i] = br.readLine().charAt(0);
        }

        StringBuilder sb = new StringBuilder();

        front = 0;
        last = N - 1;
        for (int i = 0; i < N; i++) {
            boolean isFront = true;
            // 어느 것이 우선인지 확인
            for (int f = front, l = last; f < l; f++, l--) {
                if (T[f] == T[l]) continue;
                if (T[f] < T[l]) isFront = true;
                else isFront = false;
                break;
            }

            sb.append(isFront ? T[front++] : T[last--]);

            if (sb.length() == 80) {
                System.out.println(sb.toString());
                sb = new StringBuilder();
            }
        }

        System.out.println(sb.toString());
        br.close();
    }
}