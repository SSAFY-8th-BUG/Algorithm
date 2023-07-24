import java.util.*;

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        int[] answer;
        Map<String, Genre> map = new HashMap<>();
        
        //장르별 모으기
        for(int i=0; i<genres.length; i++){
            String name = genres[i];
            int play = plays[i];
            if(map.containsKey(name)){
                map.get(name).add(i, play);
            }else{
                map.put(name, new Genre(i, play));
            }
        }
        
        //정렬
        List<Integer> ans = new ArrayList<>();
        List<Genre> genreLst = new ArrayList<>();
        for(String key : map.keySet()){
            genreLst.add(map.get(key));
        }
        genreLst.sort((o1,o2)->o2.cnt-o1.cnt);
        
        //2개씩 넣기
        for(Genre genre : genreLst){
            
            if(genre.idxs.size()>1){
                for(int i=0; i<2; i++){
                    ans.add(genre.idxs.poll()[0]);
                }
            }else{
                ans.add(genre.idxs.poll()[0]);
            }
        }
        answer = new int[ans.size()];
        for(int i=0; i<ans.size(); i++){
            answer[i] = ans.get(i);
        }
        
        return answer;
    }
    
    static class Genre{
        int cnt;
        PriorityQueue<int[]> idxs;
        
        Genre(int idx,int cnt){
            this.cnt= cnt;
            idxs = new PriorityQueue<int[]>((o1,o2)->o1[1]==o2[1]? o1[0]-o2[0] : o2[1]-o1[1]);
            idxs.add(new int[]{idx, cnt});
        }
        
        void add(int idx, int cnt){
            idxs.add(new int[]{idx, cnt});
            this.cnt +=cnt;
        }
    }
}