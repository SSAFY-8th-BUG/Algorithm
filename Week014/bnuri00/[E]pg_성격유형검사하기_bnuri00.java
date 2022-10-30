package week014;

class Solution {
    static char[] type = {'R', 'T', 'C', 'F', 'J', 'M', 'A', 'N'};
	static int[] scoreBoard = new int[8];
	
    public String solution(String[] survey, int[] choices) {
        String answer = "";
        
        int n = choices.length;
        for (int i = 0; i < n; i++) {
			int score = choices[i] - 4;
			int absScore = Math.abs(score);
			if(score == 0) continue;
			else if(score < 0) scoreBoard[getTypeIdx(survey[i].charAt(0))]+=absScore;
			else scoreBoard[getTypeIdx(survey[i].charAt(1))]+=absScore;
		}
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++) {
        	int tmp = i*2;
        	char c = (scoreBoard[tmp]>=scoreBoard[tmp+1] )?type[tmp] : type[tmp+1]; 
        	sb.append(c);
		}
        
        return sb.toString();
    }
	static int getTypeIdx(char c) {
		for (int i = 0; i < 8; i++) {
			if(type[i]==c) return i;
		}
		return -1;
	}
}