import java.io.*;
import java.util.Arrays;

// 도저히 안풀려서 규칙블로그에서 참고
public class boj_2448_tableMinPark {

    static char[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        map = new char[N][2 * N - 1];
        for (int i = 0; i < N; i++) Arrays.fill(map[i], ' ');

        solve(0, N - 1, N);

        StringBuilder sb = new StringBuilder();
        for (int y = 0 ; y < N; y++){
            for (int x = 0; x < 2 * N - 1; x++){
                sb.append(map[y][x]);
            }
            sb.append("\n");
        }

        System.out.println(sb);
        br.close();
    }

    static void solve(int y, int x, int n){
        if (n == 3){
            map[y][x] = '*';
            map[y + 1][x + 1] = map[y + 1][x - 1] = '*';            
            map[y + 2][x - 2] = map[y + 2][x - 1] = map[y + 2][x] = map[y + 2][x + 1] = map[y + 2][x + 2] = '*';
        } else {
            // 작은 단위로 쪼개기
            int c = n / 2;
            solve(y, x, c);                     // 현재자리
            solve(y + (n / 2), x - (n / 2), c); // 왼쪽
            solve(y + (n / 2), x + (n / 2), c); // 오른쪽
        }
    }
}
