package Week020.bnuri00;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 참고: 블로그 코드(열심히 봣음)
 * */
public class B_boj_2046_bnuri00 {
	public static void main(String[] args) throws Exception{
		 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		 StringTokenizer st;
		 
		 int n = Integer.parseInt(br.readLine());
		 
		 int[] ipArr = new int[n];
		 
		 for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), ".");
			int ipTmp = 0;
			for (int j = 0; j < 4; j++) {
				int part = Integer.parseInt(st.nextToken());
				ipTmp <<= 8;
				ipTmp += part;
			}
			
			ipArr[i] = ipTmp;
		}
		 
		int netMask = 0;
		int netAddr = 0;
		
		for (int i = 31; i >= 0; i--) {
			int bit = 1 << i;
			boolean check = false;
			
			for (int j = 1; j < n; j++) {
				if((ipArr[0] & bit) != (ipArr[j] & bit)) {
					check = true;
					break;
				}
			}
			if(check) break;
			else netMask |= bit;
		}
		
		// 넷주소 구하기
		netAddr = ipArr[0]&netMask;
		
		//인트 - 스트링 형변환
        String address = "";
        String mask = "";

        // 각 파트 별 숫자 찾기 변수
        int check = (1<<8)-1; // 모든 자리가 1이 되어있는 파트

        int k = 3; //제일 위의 파트부터 구하면서 내려올것.
        while(true){
            int anum = netAddr>>(8*k) & check;
            int mnum = netMask>>(8*k) & check;

            address += String.valueOf(anum);
            mask += String.valueOf(mnum);

            netAddr &= ((1<<(8*k))-1); //8*k번째 원소 왼쪽은 다 0으로 만들기
            netMask &= ((1<<(8*k))-1);
            k--;

            if(k == -1){
                break;
            }
            address += ".";
            mask += ".";
        }

        System.out.println(address);
        System.out.println(mask);
	}
}
