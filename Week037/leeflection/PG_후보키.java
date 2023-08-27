package studygroup.Week032;
import java.util.*;
public class PG_후보키 {
    static boolean[] target;
    static String[][] rel;
    static int count;
    static ArrayList<boolean[]> list;
    public int solution(String[][] relation) {
        int answer = 0;
        target = new boolean[relation[0].length];
        list = new ArrayList<>();
        rel = relation;
        sub(0);
        return count;
    }
    public static void sub(int depth){
        HashSet<String> set = new HashSet<>();
        if(depth == target.length){
            for(int i=0; i<list.size(); i++){
                int c = 0;
                int count = 0;
                for(int j=0; j<target.length; j++){
                    if(list.get(i)[j]) c++;
                }
                for(int j=0; j<target.length; j++){
                    if(list.get(i)[j] && target[j]) count++;
                }
                if(c == count) return;
            }

            for(int i=0; i<rel.length; i++){
                String str = "";
                for(int j=0; j<rel[0].length; j++){
                    if(target[j]){
                        str += rel[i][j];
                    }
                }
                set.add(str);
            }
            if(set.size()==rel.length){
                list.add(target.clone());
                count++;
            }
            return;
        }
        target[depth] = false;
        sub(depth+1);
        target[depth] = true;
        sub(depth+1);

    }
}
