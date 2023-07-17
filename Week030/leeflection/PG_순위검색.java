package studygroup.Week030;
import java.util.*;

public class PG_순위검색 {

        static class Info{
            String stack;
            String pos;
            String career;
            String food;
            int sco;
            public Info(String stack, String pos, String career, String food, int sco){
                this.stack = stack;
                this.pos = pos;
                this.career = career;
                this.food = food;
                this.sco = sco;
            }
        }
        static boolean[] visited;
        static HashMap<String,ArrayList<Integer>> map;
        public int[] solution(String[] info, String[] query) {
            map = new HashMap<>();

            for(int i=0; i<info.length; i++){
                String[] tmp = info[i].split(" ");
                visited = new boolean[4];
                dfs(0,tmp);
            }

            ArrayList<Integer> countList = new ArrayList<>();
            for(ArrayList<Integer> l : map.values()){
                Collections.sort(l);
            }

            for(int i=0; i<query.length; i++){
                String[] tmp = query[i].split(" and ");
                String[] tmp2 = tmp[3].split(" ");

                int sco = Integer.parseInt(tmp2[1]);

                String qu = tmp[0]+tmp[1]+tmp[2]+tmp2[0];

                ArrayList<Integer> infoList = map.get(qu);
                int count = 0;
                if(infoList != null){
                    int lt = 0;
                    int rt = infoList.size()-1;
                    while(lt<=rt){
                        int mid = (rt+lt)/2;
                        if(infoList.get(mid) < sco)lt=mid+1;
                        else rt = mid-1;
                    }
                    countList.add(infoList.size()-lt);
                }else{
                    countList.add(0);
                }

            }

            int[] ans = new int[countList.size()];
            for(int i=0; i<countList.size(); i++){
                ans[i] = countList.get(i);
            }

            return ans;
        }
        public static void dfs(int depth, String[] arr){
            if(depth == 4){
                String s = "";
                for(int i=0; i<4; i++){
                    if(visited[i]){
                        s+=arr[i];
                    }else{
                        s+="-";
                    }
                }
                if(!map.containsKey(s)){
                    ArrayList<Integer> alist = new ArrayList<>();
                    alist.add(Integer.parseInt(arr[4]));
                    map.put(s,alist);
                }else{
                    ArrayList<Integer> alist = map.get(s);
                    alist.add(Integer.parseInt(arr[4]));
                    map.put(s,alist);
                }
                return;
            }
            visited[depth] = true;
            dfs(depth+1, arr);
            visited[depth] = false;
            dfs(depth+1, arr);
        }
    }
}
