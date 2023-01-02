import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Iterator;


public class Main { 
	
	static int N;
	static String func;
	static boolean flag;
	static Deque<String> que;
	public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for(int tc=1; tc<=T; tc++) {
        	func=br.readLine();
        	N = Integer.parseInt(br.readLine());
        	String lstStr=br.readLine();
        	que = new ArrayDeque<>();

        	if(!lstStr.equals("[]")) {
        		String[] lst=lstStr.substring(1, lstStr.length()-1).split(",");
            	for(String st: lst) {
            		que.add(st);
            	}
        	}
        	
        	
        	flag=true; //true면 안뒤집힘
        	boolean error=false;
        	for(int i=0; i<func.length(); i++) {
        		char f = func.charAt(i);
        		if(!run(f)) { //에러면 break
        			error=true;
        			break;
        		}
        	}
        	
            
        	if(error) {
        		sb.append("error\n");
        	}else {
        		sb.append("[");
        		if(flag) { //순서대로 읽기
	        		for(String st: que) {
	        			sb.append(st).append(",");
	        		}
        		}else {//거꾸로 읽기
        			Iterator<String> it= que.descendingIterator();
        			for(int i=0; i<que.size(); i++) {
        				String st=it.next();
        				sb.append(st).append(",");
        			}
        			
        		}
        		if(!que.isEmpty())
        			sb.deleteCharAt(sb.length()-1);
        		sb.append("]\n");
        		
        	}
        	
        }
        System.out.println(sb);
        
    }
	
	static boolean run(char f) {
		if(f=='R') {
			flag=!flag;
		}
		else {
			if(que.size()==0)return false;
			if(flag) {
				que.pollFirst();
			}else {
				que.pollLast();
			}
		}
		
		return true;
	}
	
}