import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main { 
	
	static int N;
	static char[] str;
	public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N= Integer.parseInt(br.readLine());
        str=new char[N];
        for(int i=0; i<N; i++) {
        	str[i] = br.readLine().charAt(0);
        }
        
        
        StringBuilder sb = new StringBuilder();
        int len=0;
        int i1=0, i2=N-1; //앞쪽 커서, 뒤쪽 커서
        while(i1<=i2) {
        	char c1=str[i1];
        	char c2=str[i2];
        	if(c1<c2) {
        		sb.append(c1);
        		i1++;
        	}else if(c1>c2) {
        		sb.append(c2);
        		i2--;
        	}else { //앞뒤가 같은 문자면
        		
    			int n1=i1+1;
    			int n2=i2-1;
    			int min=0;
    			while(n1<=n2) {
    				if(str[n1]==str[n2]) {
    					n1++;
    					n2--;
    				}else if(str[n1]<str[n2]) {
    					min=1;
    					break;
    				}else {
    					min=2;
    					break;
    				}
    			}
    			if(min<=1) {
    				sb.append(c1);
            		i1++;
    			}else if(min==2) {
    				sb.append(c2);
            		i2--;
    			}
    		
        	}
        	
        	if(++len%80==0) {
        		sb.append("\n");
        	}
        }
        System.out.println(sb);
        
	}
	
	
	
}