class Solution {
    static Music[] musics;
    static int N;
    static int maxTime, maxStart, mlen;
    static String answer;
    static String melody;
    public String solution(String m, String[] musicinfos) {
        N = musicinfos.length;
        m=m.replace("C#","c");
        m=m.replace("D#","d");
        m=m.replace("F#","f");
        m=m.replace("G#","g");
        m=m.replace("A#","a");
        melody = m;
        mlen = m.length();
        musics = new Music[N];
        answer = "(None)";
        for(int i=0; i<N; i++){
            String[] info = musicinfos[i].split(",");
            musics[i] = new Music(info[0], info[1], info[2], info[3]);
            musics[i].check();
        }
        
        
        return answer;
    }
    
    class Music{
        int time, start;
        int len;
        String song;
        String name;
        Music(String st, String et, String name, String song){
            String[] stList = st.split(":");
            String[] etList = et.split(":");
            start = Integer.parseInt(stList[0])*60 + Integer.parseInt(stList[1]);
            int end = Integer.parseInt(etList[0])*60 + Integer.parseInt(etList[1]);
            time = end-start;
            this.name=name;
            song=song.replace("C#","c");
            song=song.replace("D#","d");
            song=song.replace("F#","f");
            song=song.replace("G#","g");
            song=song.replace("A#","a");
            this.song=song;
            len = song.length();
        }
        
        void check(){
            int idx=0;
            int cnt=0;
            int totalCnt=0;
            int t=time;
            for(int i=0; i<t; i++){
                //System.out.println(melody.charAt(idx%mlen) +" // " +song.charAt(i%len));
                if(melody.charAt(idx%mlen) == song.charAt(i%len)){
                    idx++;
                    cnt++;
                }else{
                    idx=0;
                    totalCnt+= cnt/mlen;
                    cnt=0;
                    if(melody.charAt(idx%mlen) == song.charAt(i%len)){
                        idx++;
                        cnt++;
                    }
                }
            }
            totalCnt+= cnt/mlen;
            //System.out.println(name + " "+totalCnt);
            if(totalCnt>0){
                if(time>maxTime){
                    answer = name;
                    maxTime=time;
                    maxStart = start;
                }
            }
        }
    }
}