package studygroup.Week031;
import java.util.*;
public class PG_베스트앨범 {
    static HashMap<String,Integer> map;
    static class Music{
        String gen;
        int play;
        int index;
        public Music(String gen,int play, int index){
            this.gen = gen;
            this.play = play;
            this.index = index;
        }
    }
    public int[] solution(String[] genres, int[] plays) {
        int[] answer = {};
        map = new HashMap<>();
        ArrayList<Music> list = new ArrayList<>();
        for(int i=0; i<genres.length; i++){
            map.put(genres[i],map.getOrDefault(genres[i],0)+plays[i]);
            Music m = new Music(genres[i],plays[i],i);
            list.add(m);
        }
        Collections.sort(list,(p1,p2)->{
            if(map.get(p1.gen) == map.get(p2.gen)){
                if(p1.play == p2.play){
                    return p1.index - p2.index;
                }else{
                    return p2.play - p1.play;
                }
            }else{
                return map.get(p2.gen) - map.get(p1.gen);
            }
        });

        int count = 0;
        ArrayList<Integer> ans = new ArrayList<>();

        ArrayList<String> ord = new ArrayList<>(map.keySet());
        Collections.sort(ord,(o1,o2)->{
            return map.get(o2) - map.get(o1);
        });

        for(String s : ord){
            for(Music m : list){
                if(count == 2){
                    break;
                }
                if(m.gen.equals(s)){
                    ans.add(m.index);
                    count++;
                }
            }
            count =0;
        }

        int[] realAns = new int[ans.size()];
        for(int i=0; i<ans.size(); i++){
            realAns[i] = ans.get(i);
        }

        return realAns;
    }
}
