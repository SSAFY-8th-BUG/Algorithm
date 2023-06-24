class Solution {
    static Long D;
    static long D2;
    static long x2;
    public long solution(int k, int d) {
        D=new Long(d);
        D2= D*D;
        long answer = 0;
        for(long x=0; x<=d; x+=k){
            x2 = x*x;
            long y2 = D2-x2;
            long y = (long)Math.sqrt(y2);
            answer+=y/k+1;
            //System.out.println(x + " "+y2 + " "+y + " "+y/k+1);
        }
        return answer;
    }
}