import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static int n;
	static char[][] arr;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		arr = new char[n][2*n-1];
		
		// 이거 공백문자로 초기화 안해주면 틀렸다고 뜸 -> 왜?
		for(int i=0; i<n; i++) {
			for(int j=0; j<2*n-1; j++) {
				arr[i][j] = ' ';
			}
		}
		
		star(0, n-1, n);
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<2*n-1; j++) {
				sb.append(arr[i][j]);
			}
			sb.append(System.lineSeparator());
		}
		
		System.out.print(sb);
	}
	
	static void star(int r, int c, int n) {
		// 가장 작은 단위
		if(n == 3) {
			arr[r][c] = '*';
			arr[r+1][c-1] = arr[r+1][c+1] = '*';
			arr[r+2][c-2] = arr[r+2][c-1] = arr[r+2][c] = arr[r+2][c+1] = arr[r+2][c+2] = '*';
			return;
		}
		
		// 작은 단위가 아니라면 반으로 뽀각
		// 가장 위의 꼭짓점을 기준으로 호출
		int len = n/2;
		star(r, c, len);
		star(r+len, c-len, len);
		star(r+len, c+len, len);
		
	}

}
