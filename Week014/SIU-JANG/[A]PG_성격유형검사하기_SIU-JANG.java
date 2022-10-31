import java.util.*;

class Solution {
    public String solution(String[] survey, int[] choices) {
        String answer = "";
        Map<Character, Integer> map = new HashMap<>();
        
        map.put('R', 0);
        map.put('T', 0);
        map.put('C', 0);
        map.put('F', 0);
        map.put('J', 0);
        map.put('M', 0);
        map.put('A', 0);
        map.put('N', 0);
        
        for (int i = 0; i < survey.length; i++) {
            String s = survey[i];
            int n = choices[i];
            
            switch(n) {
                case 1:
                    map.put(s.charAt(0), map.get(s.charAt(0)) + 3);
                    break;
                case 2:
                    map.put(s.charAt(0), map.get(s.charAt(0)) + 2);
                    break;
                case 3:
                    map.put(s.charAt(0), map.get(s.charAt(0)) + 1);
                    break;
                case 5:
                    map.put(s.charAt(1), map.get(s.charAt(1)) + 1);
                    break;
                case 6:
                    map.put(s.charAt(1), map.get(s.charAt(1)) + 2);
                    break;
                case 7:
                    map.put(s.charAt(1), map.get(s.charAt(1)) + 3);
                    break;
            }
        }
        
        if (map.get('R') >= map.get('T')) {
            answer += "R";
        } else {
            answer += "T";
        }
        
        if (map.get('C') >= map.get('F')) {
            answer += "C";
        } else {
            answer += "F";
        }
        
        if (map.get('J') >= map.get('M')) {
            answer += "J";
        } else {
            answer += "M";
        }
        
        if (map.get('A') >= map.get('N')) {
            answer += "A";
        } else {
            answer += "N";
        }
        
        return answer;
    }
}