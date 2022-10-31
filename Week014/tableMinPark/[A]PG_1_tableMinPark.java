public class PG_1_tableMinPark {
    public static void main(String[] args) {
        String[] survey = {"TR", "RT", "TR"};
        int[] choices = {7, 1, 3};

        System.out.println(solution(survey, choices));
    }

    static String solution(String[] survey, int[] choices) {
        int[][] count = new int[4][2];

        StringBuilder answer = new StringBuilder();

        for (int i = 0; i < survey.length; i++){            
            int score = choices[i];            
            if (score == 4) continue;
            else if (score < 4) {
                int[] idx = getIdx(survey[i].charAt(0));
                count[idx[0]][idx[1]] += Math.abs(score - 4);
            } else {
                int[] idx = getIdx(survey[i].charAt(1));
                count[idx[0]][idx[1]] += score - 4;
            }
        }

        for (int i = 0; i < 4; i++){
            if (count[i][0] >= count[i][1]) answer.append(alp[i][0]);
            else answer.append(alp[i][1]);
        }

        return answer.toString();
    }

    static char[][] alp = {{'R', 'T'}, {'C', 'F'}, {'J', 'M'}, {'A', 'N'}};
    static int[] getIdx(char c){
        if (c == 'R') return new int[]{0, 0};
        else if (c == 'T') return new int[]{0, 1};
        else if (c == 'C') return new int[]{1, 0};
        else if (c == 'F') return new int[]{1, 1};
        else if (c == 'J') return new int[]{2, 0};
        else if (c == 'M') return new int[]{2, 1};
        else if (c == 'A') return new int[]{3, 0};
        else return new int[]{3, 1};
    }
}