import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {
	
	static int[][] mat;
	static int[] counts;
	static int R,C,answer;
	public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stn = new StringTokenizer(br.readLine());
        R=Integer.parseInt(stn.nextToken());
        C=Integer.parseInt(stn.nextToken());
        mat=new int[R][C];
        counts = new int[C];
        
        for(int y=0; y<R; y++) {
        	String row = br.readLine();
        	for(int x=0; x<C; x++) {
        		mat[y][x]=row.charAt(x)-'a';
        		if(y!=0) counts[x]+=mat[y][x]; //x축별 문자들 합
        	}
        }
        
        answer=0;
        
        for(int y=0; y<R-1; y++) {
        	if(check(y)) {
        		break;
        	}else { //같은게 없으면
        		for(int x=0; x<C; x++) {
        			counts[x]-=mat[y+1][x];
        		}
        		answer++;
        	}
        }
        System.out.println(answer);
    }
	
	static boolean check(int y) {
		for(int x1=0; x1<C-1; x1++) {
			for(int x2=x1+1; x2<C; x2++) {
				if(counts[x1]==counts[x2]) { //문자열 합이 같으면
					if(matchCheck(y,x1,x2)) {
						return true;
					}
				}
			}
    	}
		
		return false;
	}
	
	static boolean matchCheck(int yi, int x1, int x2) { //문자열이 같은지
		for(int y=yi+1; y<R; y++) {
			if(mat[y][x1] != mat[y][x2]) {
				return false;
			}
		}
		return true;
	}
	
	
}