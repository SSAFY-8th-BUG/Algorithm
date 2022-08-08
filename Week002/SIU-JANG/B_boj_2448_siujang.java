import java.io.*;

public class Main {
    
    static String[] arr;
    static int n;

    public static void divideAndConquer(int k) {
    	StringBuilder sb = new StringBuilder();
        int end = 3 * (int)Math.pow(2, k);
        int mid = end / 2;

        for(int i = mid; i < end; i++) {  // 아래쪽 라인 절반은 위쪽 라인 절반을 공백을 사이에 두고 두 번 이어붙인 것이다
            arr[i] = arr[i - mid] + " " + arr[i - mid];
        }

        sb.delete(0, sb.length());
        while(sb.length() < mid) {
            sb.append(" ");
        }

        for(int i = 0; i < mid; i++) {  // 위쪽 절반 라인은 양옆에 전체 라인 수의 절반만큼 공백이 추가된다
            arr[i] = sb.toString() + arr[i] + sb.toString();
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(br.readLine());
        arr = new String[n];

        arr[0] = "  *  ";
        arr[1] = " * * ";
        arr[2] = "*****";

        for(int k = 1; 3 * (int)Math.pow(2, k) <= n; k++) {
            divideAndConquer(k);
        }

        sb.delete(0, sb.length());
        for(int i = 0; i < n; i++) {
            sb.append(arr[i]).append("\n");
        }

        System.out.print(sb);
    }
}