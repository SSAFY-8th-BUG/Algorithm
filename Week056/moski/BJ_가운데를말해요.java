import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BJ_가운데를말해요 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        PriorityQueue<Integer> ascPq = new PriorityQueue<>();
        PriorityQueue<Integer> descPq = new PriorityQueue<>((o1, o2) -> o2 - o1);

        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());
            if(ascPq.size() == descPq.size()){
                descPq.add(num);
            }else{
                ascPq.add(num);
            }

            if(!ascPq.isEmpty() && !descPq.isEmpty()){
                if(ascPq.peek() < descPq.peek()) {
                    int tmp = ascPq.poll();
                    ascPq.add(descPq.poll());
                    descPq.add(tmp);
                }
            }
            sb.append(descPq.peek()).append("\n");
        }
        System.out.println(sb);
    }
}
