package studygroup.Week038;
import java.util.*;
public class PG_혼자놀기의달인 {
        static boolean[] visited;
        static ArrayList<Integer> list;
        static int[] card;
        public int solution(int[] cards) {
            /**
             카드 더미 100장
             2이상 100이하 자연수 하나 정한다.
             그 수보다 작거나 같은 숫자카드 준비
             준비한 카드의 수만큼 상자 준비
             카드를 한장씩 넣고 상자 나열

             임의 상자 선택 -> 카드 까보기 -> 카드에 적힌 숫자(인덱스) 상자 확인 .. -> 이미 열린 상자가 생길 때 까지
             숫자 그룹 나누기 -> 그룹 구성카드 숫자로 정렬 -> 마지막 인덱스 * 마지막 인덱스 -1
             **/
            list = new ArrayList<>();
            visited = new boolean[cards.length];
            card = cards;
            for(int i=0; i<cards.length; i++){
                if(!visited[i]){
                    list.add(bfs(i));
                }
            }
            Collections.sort(list);
            if(list.size()==1){
                return 0;
            }
            return list.get(list.size()-1) * list.get(list.size()-2);
        }
        public static int bfs(int n){
            Queue<int[]> q = new ArrayDeque<>();
            q.add(new int[]{n,1});
            visited[n] = true;
            int ret = 0;
            while(!q.isEmpty()){
                int[] tmp = q.poll();
                ret = tmp[1];
                int idx = card[tmp[0]] - 1;
                if(!visited[idx]){
                    visited[idx] = true;
                    q.add(new int[]{idx,tmp[1]+1});
                }
            }
            return ret;
        }
}
