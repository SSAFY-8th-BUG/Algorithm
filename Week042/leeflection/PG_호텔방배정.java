package studygroup.Week042;
import  java.util.*;
public class PG_호텔방배정 {
        static HashMap<Long, Long> roomInfo = new HashMap<>();

        public long[] solution(long k, long[] room_number) {
            long[] answer = new long[room_number.length];

            int len = room_number.length;

            for (int i = 0; i < len; i++) {
                answer[i] = findEmptyRoom(room_number[i]);
            }

            return answer;
        }

        public long findEmptyRoom(long wantRoom) {
            if (!roomInfo.containsKey(wantRoom)) {
                roomInfo.put(wantRoom, wantRoom + 1);
                return wantRoom;
            }

            long next_room = roomInfo.get(wantRoom);
            long empty_room = findEmptyRoom(next_room);
            roomInfo.put(wantRoom, empty_room);
            return empty_room;
        }
}
