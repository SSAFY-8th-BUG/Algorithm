import java.util.*;

class PG_과제진행하기_tableMinPark {

    static class Node {
        String name;
        int start;
        int time;

        public Node(String name, int start, int time){
            this.name = name;
            this.start = start;
            this.time = time;
        }
    }

    public String[] solution(String[][] plans) {
        List<String> answer = new ArrayList<>();
        List<Node> nodes = new ArrayList<>();
        Stack<Node> stack = new Stack<>();
        HashMap<Integer, Node> info = new HashMap<>();

        for (String[] plan : plans){
            String[] temp = plan[1].split(":");
            String name = plan[0];
            int start = Integer.parseInt(temp[0]) * 60 + Integer.parseInt(temp[1]);
            int time = Integer.parseInt(plan[2]);
            info.put(start, new Node(name, start, time));
            nodes.add(new Node(name, start, time));
        }

        nodes.sort((n1, n2) -> n1.start != n2.start ? n1.start - n2.start : n1.time - n2.time);

        int time = nodes.get(0).start;
        int cnt = 0;

        while(cnt < plans.length){
            if (!stack.isEmpty()) {
                stack.peek().time -= 1;
                if (stack.peek().time == 0) {
                    answer.add(stack.peek().name);
                    stack.pop();
                    cnt++;
                }
            }

            if (info.containsKey(time)) {
                stack.add(info.get(time));
            }
            time++;
        }

        return answer.toArray(new String[answer.size()]);
    }
}