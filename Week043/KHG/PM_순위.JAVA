import java.util.*;

class Solution {
    static Map<String, Map> map;
    static String[] lst1, lst2;
    static int[] answer;
    static int N;
    public int[] solution(String[] infos, String[] querys) {
        answer = new int[querys.length];
        map = new HashMap<>();

        //그래프 만들기
        for(String info : infos){
            String[] lst = info.split(" ");
            Map<String,Map> cur =map;
            Map<String,Score> cur3= new HashMap<>();
            for(int i=0; i<3; i++){//언어,직군,경력 부분 수행
                if(cur.containsKey(lst[i])){
                    if(i<2){
                        cur = cur.get(lst[i]);
                    }
                    else{
                        cur3 =cur.get(lst[i]);
                    }
                    
                }else{
                    if(i<2){
                        Map<String, Map> next = new HashMap<>();
                        cur.put(lst[i], next);
                        cur = next;
                    }else{
                        Map<String, Score> next = new HashMap<>();
                        cur.put(lst[i], next);
                        cur3=next;
                    }
                }
            }
            //음식과 점수 부분수행
            if(cur3.containsKey(lst[3])){
                cur3.get(lst[3]).add(Integer.parseInt(lst[4]));
            }
            else{
                Score score = new Score();
                Integer num = Integer.parseInt(lst[4]);
                score.add(num);
                cur3.put(lst[3], score);
            }
        }
        
 
        //자격맞는지 확인
        for(int i=0; i<querys.length; i++){
            String query = querys[i];
            N=i;
            lst1 = query.split(" and ");
            lst2 = lst1[3].split(" ");

            rec1(map,0);
        }
        return answer;
    }
    
    
    void rec1(Map<String, Map> map, int idx){
        String key =lst1[idx];
        if(key.equals("-")){
            for(String nkey : map.keySet()){
                if(idx<2) //언어,직군으로 찾기
                    rec1(map.get(nkey),idx+1);
                else //경력이면 rec2수행
                    rec2(map.get(nkey));
            }
            
        }
        else if(!map.containsKey(key)) {
            return;
        }   
        else{
            if(idx<2){
                Map<String, Map> next = map.get(key);
                rec1(next, idx+1);    
            }else{
                Map<String, Score> next = map.get(key);
                rec2(next);
            }
        }
    }
    
    void rec2(Map<String, Score> map){ //음식으로 점수 찾기
        String key = lst2[0];
        int requireScore = Integer.parseInt(lst2[1]);
        if(key.equals("-")){
            for(String nkey : map.keySet()){
                answer[N]+=map.get(nkey).getCount(requireScore);
            }
        }else if(!map.containsKey(key)){
            return;
        }else{
            answer[N]+=map.get(key).getCount(requireScore);
        }
    }
    
    class Score{
        List<Integer> lst;
        int[] arr;
        Score(){
            lst = new ArrayList<>();
        }

        void add(int num){
            lst.add(num);
        }

        int getCount(int score){
            if(arr==null){//배열로 정렬하기
                arr = new int[lst.size()];
                for(int i=0; i<lst.size(); i++){
                    arr[i] = lst.get(i);
                }
                Arrays.sort(arr);
            }
            if(score<=arr[0]) 
                return arr.length;
            else if(score>arr[arr.length-1])
                return 0;
            int idx = Arrays.binarySearch(arr, score);
            if(idx<0) 
                return arr.length +idx +1;
            else 
                return arr.length-idx;
        }
    }
    
}