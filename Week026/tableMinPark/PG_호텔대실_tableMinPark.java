import java.util.*;

class PG_호텔대실_tableMinPark {

    static class T implements Comparable{
        int sh;
        int sm;
        int eh;
        int em;
        public T(int sh, int sm, int eh, int em) {
            this.sh = sh;
            this.sm = sm;
            this.eh = eh;
            this.em = em;
        }

        @Override
        public int compareTo(Object o) {
            T t = (T) o;
            if (this.eh == t.eh) {
                return this.em - t.em;
            }
            return this.eh - t.eh;
        }
    }

    public int solution(String[][] book_time) {
        int answer = 0;

        List<T> arr = new ArrayList<>();

        for (String[] time : book_time){
            String[] s = time[0].split(":");
            int sh = Integer.parseInt(s[0]);
            int sm = Integer.parseInt(s[1]);

            String[] e = time[1].split(":");
            int eh = Integer.parseInt(e[0]);
            int em = Integer.parseInt(e[1]);

            arr.add(new T(sh, sm, eh, em));
        }

        Collections.sort(arr);

        return answer;
    }
}