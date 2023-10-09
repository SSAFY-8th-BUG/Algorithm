package studygroup.Week042;
import java.util.*;
public class PG_기둥과보설치 {
    static class Struct{
        int x,y,type;
        public Struct(int x, int y, int type){
            this.x = x;
            this.y = y;
            this.type = type;
        }
        public boolean equals(Object o){
            Struct s = (Struct) o;
            if(this.x == s.x && this.y == s.y && this.type == s.type){
                return true;
            }
            return false;
        }
        public int hashCode(){
            String s = String.valueOf(this.x) + String.valueOf(this.y) + String.valueOf(this.type);
            return Objects.hash(s);
        }
    }
    static HashSet<Struct> set = new HashSet<>();
    public int[][] solution(int n, int[][] build_frame) {
        //기둥 -> 바닥 위 or 보의 끝 or 다른 기둥 위
        //보 -> 한쪽 끝 부분 기둥 or 양쪽 끝 부분 동시 연결
        // 양쪽 끝 부분 동시연결이란
        // ㅡ  ㅡ 사이를 채우는 조건인듯
        //이렇게 되면 기둥없이 보의 길이가 3을 넘을 수 없음

        int len = build_frame.length;

        for(int i=0; i<len; i++){
            int x = build_frame[i][0];
            int y = build_frame[i][1];
            int a = build_frame[i][2];
            int b = build_frame[i][3];
            // 설치 할때
            if(b == 1){
                if(canBuild(build_frame[i])){
                    set.add(new Struct(x,y,a));
                    System.out.println(x+" "+y+" "+a);
                }
            }
            // 삭제 할때
            else{
                Struct tmp = new Struct(x,b,a);
                set.remove(tmp);
                if(!canRemove()){
                    set.add(tmp);
                }else{
                    System.out.println("remove : "+x+" "+y+" "+a);
                }
            }
        }
        ArrayList<Struct> list = new ArrayList<>(set);
        Collections.sort(list,(o1,o2)->{
            if(o1.x == o2.x){
                if(o1.y == o2.y){
                    return o1.type - o2.type;
                }
                return o1.y - o2.y;
            }else{
                return o1.x - o2.x;
            }
        });
        int[][] ans = new int[list.size()][3];
        int idx = 0;
        for(Struct s : list){
            ans[idx][0] = s.x;
            ans[idx][1] = s.y;
            ans[idx][2] = s.type;
            idx++;
        }
        return ans;
    }
    public static boolean canRemove(){
        for(Struct s : set){
            if(!canBuild(new int[]{s.x,s.y,s.type})){
                return false;
            }
        }
        return true;
    }
    public static boolean canBuild(int[] info){
        int x = info[0];
        int y = info[1];
        int a = info[2];
        if(a == 0){
            if(y == 0 || set.contains(new Struct(x,y-1,0)) ||
                    set.contains(new Struct(x-1,y,1)) ||
                    set.contains(new Struct(x,y,1))
            ){
                return true;
            }
        }else{
            if(set.contains(new Struct(x,y-1,0)) || set.contains(new Struct(x+1,y-1,0)) ||
                    (set.contains(new Struct(x-1,y,1)) && set.contains(new Struct(x+1,y,1)))
            ){
                return true;
            }
        }
        return false;
    }

}
