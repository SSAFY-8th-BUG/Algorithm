package Week026.moski;
import java.util.*;
public class PG_호텔대실_moski {

    public int solution(String[][] book_time) {
        int answer = 0;

        List<Book> books = new ArrayList<>();

        for(int i=0;i<book_time.length;i++){
            int startTime = changeMinute(book_time[i][0]);
            int endTime = changeMinute(book_time[i][1]);

            books.add(new Book(startTime, endTime + 10));
        }
        Collections.sort(books, (b1, b2) -> b1.startTime == b2.startTime ? b1.endTime - b2.endTime : b1.startTime - b2.startTime);

        HashMap<Integer, Integer> rooms = new HashMap<>();

        for(int i=0;i<books.size();i++){
            boolean ok = false;

            for(int j=0;j<rooms.size();j++){
                if(books.get(i).startTime >= rooms.get(j)){
                    rooms.put(j, books.get(i).endTime);
                    ok = true;
                    break;
                }
            }

            if(!ok){
                rooms.put(answer, books.get(i).endTime);
                answer++;
            }
        }

        return answer;
    }

    static int changeMinute(String time){
        StringTokenizer st = new StringTokenizer(time, ":");

        int hour = Integer.parseInt(st.nextToken());
        int minute = Integer.parseInt(st.nextToken());

        return hour*60 + minute;
    }

    static class Book{
        int startTime;
        int endTime;

        public Book(int startTime, int endTime){
            this.startTime = startTime;
            this.endTime = endTime;
        }
    }
}
