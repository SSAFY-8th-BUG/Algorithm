class Solution
{
    static String str;
    static int len, answer, len2;
    public int solution(String s)
    {
        str = s;
        len = str.length();
        len2 = len/2;
        answer = 1;

        for(int i=0; i<len; i++){
            get_palindrome(i,i);
            get_palindrome(i,i+1);
        }
        
        return answer;
    }
    static void get_palindrome(int l, int r){
        for(int i=0; i<=len2+1; i++){
            if(l<0 || r>=len || str.charAt(l)!=str.charAt(r)){
                answer = Math.max(answer,r-l-1);
                return; 
            }
            l--;
            r++;
            
        }
        
    }
}