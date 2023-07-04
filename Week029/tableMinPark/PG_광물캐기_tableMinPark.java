import java.util.*;

class PG_광물캐기_tableMinPark {

    static class Node {
        private int diamond;
        private int iron;
        private int stone;

        public Node(int diamond, int iron, int stone) {
            this.diamond = diamond;
            this.iron = iron;
            this.stone = stone;
        }
    }

    static int[][] scoreBoard;
    static List<Node> list;

    public int solution(int[] picks, String[] minerals) {
        int answer = 0;

        scoreBoard = new int[][]{{1, 1, 1}, {5, 1, 1}, {25, 5, 1}};

        int totalPicks = picks[0] + picks[1] + picks[2];
        list = new ArrayList<>();
        for(int i = 0; i < minerals.length; i+=5) {
            if(totalPicks == 0) break;

            int dia = 0, iron = 0, stone = 0;
            for(int j = i; j < i + 5; j++) {
                if(j == minerals.length) break;

                String mineral = minerals[j];
                int val = mineral.equals("iron") ? 1 : mineral.equals("stone") ? 2 : 0;

                dia += scoreBoard[0][val];
                iron += scoreBoard[1][val];
                stone += scoreBoard[2][val];
            }

            list.add(new Node(dia, iron, stone));
            totalPicks--;
        }

        Collections.sort(list, ((o1, o2) -> (o2.stone - o1.stone)));
        for(Node n : list) {
            int dia = n.diamond;
            int iron = n.iron;
            int stone = n.stone;

            if(picks[0] > 0) {
                answer += dia;
                picks[0]--;
                continue;
            }
            if(picks[1] > 0) {
                answer += iron;
                picks[1]--;
                continue;
            }
            if(picks[2] > 0) {
                answer += stone;
                picks[2]--;
                continue;
            }
        }

        return answer;
    }
}