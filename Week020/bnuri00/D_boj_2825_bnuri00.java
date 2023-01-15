package Week020.bnuri00;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// 블로그 보고함
public class D_boj_2825_bnuri00 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader( new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[] arr = new int[1<<10];
        while (N-->0) {
            String cur = br.readLine();
            int groupNum = 0;
            for (int i = 0; i < cur.length(); i++)
                groupNum |= 1<<(cur.charAt(i) - '0');
            arr[groupNum]++;
        }

        long answer = 0;
        for (int i = 1; i < arr.length; i++) {
            for (int j = i+1; j < arr.length; j++)
                if ((i&j)!=0)   answer += 1l * arr[i] * arr[j];
            if (arr[i] >= 2)
                answer += 1l * arr[i] * (arr[i]-1) / 2;
        }

        System.out.println(answer);
		
	}

}
