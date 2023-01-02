import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


public class Main { 
	
	static int W,H, by;
	static char[] start, end, top, bot, answer;
	static char[][] mat;
	public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        W= Integer.parseInt(br.readLine());
        H= Integer.parseInt(br.readLine());
        
        bot=br.readLine().toCharArray();
        mat=new char[H][W-1];
        
        for(int y=0; y<H; y++) {
        	String row=br.readLine();
        	for(int x=0; x<W-1; x++) {
        		mat[y][x]=row.charAt(x);
        		
        	}
        	if (mat[y][0]=='?') by=y;
        }
        
        top=new char[W];
        for(int i=0; i<W; i++) {
        	top[i]=(char) ('A'+i);
        }
        
        if(by==0) {
        	start=top;
        }else{
        	goDown(); //위에서 내려가며 start만들기
        }
        
        if(by==H-1) {
        	end=bot;
        }else {
        	goUp(); //밑에서 올라가며 end 만들기
        }
        
        StringBuilder sb = new StringBuilder();
        if(compare()) { //start를 end로 만들 수 있으면
        	for(int i=0; i<W-1; i++) {
        		sb.append(answer[i]);
        	}
        }else { //start를 end로 만들 수 없으면
        	for(int i=0; i<W-1; i++) {
        		sb.append('x');
        	}
        }
        System.out.println(sb);
	}	
	
	static void goDown() {
		start=new char[W];
		for(int i=0; i<W; i++) {
			int x=i;
			for(int y=0; y<by; y++) {
				if(x==0) { //제일 왼쪽
					if(mat[y][x]=='-') {
						x++;
					}
				}else if(x==W-1) { //제일 오른쪽
					if(mat[y][x-1]=='-') {
						x--;
					}
						
				}else {
					if(mat[y][x]=='-') {
						x++;
					}else if(mat[y][x-1]=='-') {
						x--;
					}
				}
			}
			start[x]=top[i];
		}
		//System.out.println(Arrays.toString(start));
	}
	
	static void goUp() {
		end=new char[W];
		for(int i=0; i<W; i++) {
			int x=i;
			for(int y=H-1; y>by; y--) {
				if(x==0) { //제일 왼쪽
					if(mat[y][x]=='-') {
						x++;
					}
				}else if(x==W-1) { //제일 오른쪽
					if(mat[y][x-1]=='-') {
						x--;
					}
						
				}else {
					if(mat[y][x]=='-') {
						x++;
					}else if(mat[y][x-1]=='-') {
						x--;
					}
				}
			}
			end[x]=bot[i];
		}
		//System.out.println(Arrays.toString(end));
		
	}

	static boolean compare() {
		answer=new char[W-1];
		Arrays.fill(answer, '*');
		int idx=0;
		while(idx<W) {
			if(start[idx]==end[idx]) {
				idx++;
			}else {
				if(idx==W-1) return false;
				if(start[idx]==end[idx+1] && start[idx+1]==end[idx]) {
					answer[idx]='-';
					idx+=2;
				}else {
					return false;
				}
			}
		}
			
		return true;
	}
}