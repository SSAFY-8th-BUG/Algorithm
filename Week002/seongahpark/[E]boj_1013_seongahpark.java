import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String vega = "(100+1+|01)+";
		int t = Integer.parseInt(br.readLine());
		
		// 정규 표현식 문제
		/*
		 * str.matches(regex)함수: str이 정규 표현식인 regex와 일치하는지 확인.
			() :  괄호 안의 문자를 하나의 문자로 인식
			| : 패턴 안에서 or연산 수행
			+ : 바로 앞 문자가 하나 이상
		 */
		
		for(int i=0; i<t; i++) {
			String str = br.readLine();
			if(str.matches(vega)) System.out.println("YES");
			else System.out.println("NO");
		}
		
	}
}
