package studygroup.Week032;

public class PG_문자열압축 {
    public int solution(String s) {
        int min = Integer.MAX_VALUE;
        for(int i=1; i<s.length(); i++){
            StringBuilder sb = new StringBuilder();
            String base = s.substring(0,i);
            int count = 1;
            for(int j=i; j<s.length(); j+=i){
                String tmp = "";
                if(j+i >= s.length()){
                    tmp = s.substring(j,s.length());
                }else{
                    tmp = s.substring(j,j+i);
                }

                if(base.equals(tmp)){
                    count++;
                    //달라지는 순간
                }else{
                    if(count == 1){
                        sb.append(base);
                    }else{
                        sb.append(count);
                        sb.append(base);
                    }
                    base = tmp;
                    count = 1;
                }
                if(j+i>=s.length()){
                    if(count == 1){
                        sb.append(base);
                    }else{
                        sb.append(count);
                        sb.append(base);
                    }
                }
            }
            min = Math.min(min,sb.toString().length());
            sb = new StringBuilder();
        }
        if(s.length() == 1){
            return 1;
        }
        return min;
    }
}
