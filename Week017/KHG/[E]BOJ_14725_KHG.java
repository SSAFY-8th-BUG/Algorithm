import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;




public class Main { 
	
	static int N;
	static Map<String, Map> graph;
	static StringBuilder sb;
	public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stn;
        sb=new StringBuilder();
        N = Integer.parseInt(br.readLine());
        
        graph = new HashMap<>();
        for(int i=0; i<N; i++) {
        	stn = new StringTokenizer(br.readLine());
        	int n = Integer.parseInt(stn.nextToken());
        	add(n,stn);
        }
        
        print(graph,0);
        System.out.println(sb);
        
    
        
    }
	
	
	static void add(int n, StringTokenizer stn) { //graph에 추가
		Map<String,Map> now=graph;
    	for(int j=0; j<n; j++) {
    		String str = stn.nextToken();
    		if(now.containsKey(str)) {
    			now=now.get(str);
    		}else {
    			Map<String,Map> next = new HashMap<>();
    			now.put(str, next);
    			now=next;
    		}
    	}
	}
	
	static void print(Map<String,Map> now, int d) {
		if(now.isEmpty()) return;
		
		List<String> keyList = new ArrayList<>();
		for(String str : now.keySet()) {
			keyList.add(str);
		}
		Collections.sort(keyList); //key 정렬
		
		for(String key : keyList) { //dfs 들어가기
			for(int i=0; i<d; i++)
				sb.append("--");
			sb.append(key+"\n");
			print(now.get(key), d+1);
		}
		
	}
	
	
	
}