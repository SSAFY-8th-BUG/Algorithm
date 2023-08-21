class PG_가장긴팰린드롬 {
    public int solution(String s)
    {
        int answer = 1;
        int N = s.length();
        char[] arr = s.toCharArray();


        LOOP:   for (int w = N; w > 0; w--) {
            for (int start = 0; start <= N - w; start++) {
                if (isPalindrome(start, start + w - 1, arr)) {
                    answer = w;
                    break LOOP;
                }
            }
        }
        return answer;
    }

    static boolean isPalindrome(int start, int end, char[] arr) {
        for (int left = start, right = end; left <= right; left++, right--) {
            if (arr[left] != arr[right]) {
                return false;
            }
        }
        return true;
    }
}