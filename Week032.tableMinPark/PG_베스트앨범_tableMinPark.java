import java.util.*;

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        int[] answer = {};

        HashMap<String, Integer> gen = new HashMap<String, Integer>();

        for (int i = 0; i < genres.length; i++){
            gen.put(genres[i], gen.getOrDefault(genres[i], 0) + plays[i]);
        }

        List<String> genList = new ArrayList<>(gen.keySet());
        Collections.sort(genList, (o1, o2) -> gen.get(o2).compareTo(gen.get(o1)));

        List<Integer> ans = new ArrayList<>();

        for (String g : genList){
            List<Music> mList = new ArrayList<>();
            for (int i = 0; i < genres.length; i++){
                if (g.equals(genres[i])){
                    mList.add(new Music(g, plays[i], i));
                }
            }
            Collections.sort(mList, new Comparator<Music>(){
                @Override
                public int compare(Music m1, Music m2){
                    if (m1.play == m2.play){
                        return m1.idx - m2.idx;
                    }
                    else return m2.play - m1.play;
                }
            });

            ans.add(mList.get(0).idx);
            if (mList.size() > 1) ans.add(mList.get(1).idx);
        }

        answer = ans.stream().mapToInt(Integer::intValue).toArray();

        return answer;
    }
}
class Music{
    String genre;
    int play;
    int idx;

    Music (String genre, int play, int idx){
        this.genre = genre;
        this.play = play;
        this.idx = idx;
    }
}
