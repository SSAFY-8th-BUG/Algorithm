package studygroup.week26;

public class PG_인사고과 {
    static class Emp{
        int x;
        int y;
        int sum;
        int grade;
        public Emp(int x ,int y){
            this.x=x;
            this.y=y;
            this.sum = x+y;
        }
    }
    public int solution(int[][] scores) {
        ArrayList<Emp> list = new ArrayList<>();
        //원호 객체
        Emp wonho = null;
        for(int i=0; i<scores.length; i++){
            Emp e = new Emp(scores[i][0],scores[i][1]);
            list.add(e);
            if(i==0){
                wonho = e;
            }
        }

        Collections.sort(list,(p1,p2)->{
            return (p2.x==p1.x)?p1.y-p2.y:p2.x-p1.x;
        });

        int max = list.get(0).y;
        for(int i=1; i<list.size(); i++){
            if(list.get(i).y < max){
                if(list.get(i).equals(wonho)) return -1;
                list.remove(list.get(i));
                i--;
            }else{
                max = list.get(i).y;
            }
        }

        Collections.sort(list,(p1,p2)->{
            return p2.sum-p1.sum;
        });

        list.get(0).grade = 1;
        int dup = 1;
        for(int i=1; i<list.size(); i++){
            Emp prev = list.get(i-1);
            Emp curr = list.get(i);
            if(prev.sum == curr.sum){
                curr.grade = prev.grade;
                dup++;
            }else{
                curr.grade = prev.grade+dup;
                dup = 1;
            }
        }

        return wonho.grade;
    }
}
