import java.util.*;

class Solution {
    static Map<String,Integer> map;
    static Map<String,Integer> idxMap;
    static boolean[]courses;
    static int[] maxs;
    public String[] solution(String[] orders, int[] course) {
        List<String> ans = new ArrayList<>();
        courses = new boolean[11];
        maxs = new int[11];
        for(int c : course){
            courses[c]=true;
        }
        for(int i=0; i<orders.length; i++){
            char[] chars = orders[i].toCharArray();
            Arrays.sort(chars);
            orders[i] = new String(chars);
        }
        map = new HashMap<>();
        idxMap = new HashMap<>();
        
        
        for(int i=0; i<orders.length-1; i++){
            for(int j=i+1; j<orders.length; j++){
                match(i, orders[i], orders[j]);
            }
        }
        for(String key : map.keySet()){
            if(maxs[key.length()] == map.get(key)){
                ans.add(key);
            }
        }
        String[] answer = new String[ans.size()];
        for(int i=0; i<ans.size(); i++){
            answer[i]=ans.get(i);
        }
        Arrays.sort(answer);
        return answer;
    }
    
    //교집합 구하기
    static void match(int ii, String str1, String str2){
        int i1=0, i2=0;
        StringBuilder sb = new StringBuilder();
        while(i1<str1.length() && i2<str2.length()){
            if(str1.charAt(i1) == str2.charAt(i2)){
                sb.append(str1.charAt(i1));
                i1++;
                i2++;
            }else{
                if(str1.charAt(i1)<str2.charAt(i2)){
                    i1++;
                }else{
                    i2++;
                }
            }
        }
        add(sb.toString(),"",0,ii);
    }
    
    //교집합 원소들의 갯수세기
    static void add(String str, String course, int idx, int ii){
        for(int i=idx; i<str.length(); i++){
            String course2 = course + str.charAt(i);
            if(courses[course2.length()]){
                if(!map.containsKey(course2)){
                    map.put(course2, 2);
                    idxMap.put(course2,ii);
                    maxs[course2.length()] = Math.max(maxs[course2.length()],2);
                }else{
                    if(idxMap.get(course2)==ii){
                        map.put(course2,map.get(course2)+1);    
                        maxs[course2.length()] = Math.max(maxs[course2.length()],map.get(course2));
                    }
                }    
            }
            add(str, course2, i+1,ii);
        }
    }
}