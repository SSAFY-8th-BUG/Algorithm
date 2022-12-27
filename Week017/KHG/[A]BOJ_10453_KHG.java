import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T=Integer.parseInt(br.readLine());
        for(int t=1; t<=T; t++) {
	        String st = br.readLine();
	        
	        String[] strs = st.split(" ");
	        String A = strs[0];
	        String B = strs[1];
	        
	        int len=A.length();
	        int len2=(int)len/2;
	        int[] lstA = new int[len2]; //A에서 b의 인덱스들
	        int[] lstB = new int[len2]; //B에서 b의 인덱스들
	        
	        int ai=-1;
	        int bi=-1;
	        for(int i=0; i<len; i++) {
	        	if(A.charAt(i)=='b') {
	        		lstA[++ai]=i;
	        	}
	        	if(B.charAt(i)=='b') {
	        		lstB[++bi]=i;
	        	}
	        	if(ai==len2 && bi==len2) break;
	        }
	        //System.out.println(Arrays.toString(lstA));
	        //System.out.println(Arrays.toString(lstB));
	        int answer=0;
	        for(int i=0; i<len2; i++) {
	        	answer+=Math.abs(lstA[i]-lstB[i]);
	        }
	        System.out.println(answer);
	        
        }
        
    }
	
}