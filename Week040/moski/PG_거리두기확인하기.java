import java.util.*;
public class PG_거리두기확인하기 {
    static char[][] map;

    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};


    public int[] solution(String[][] places) {
        int[] answer = new int[places.length];

        for(int i=0;i<places.length;i++){
            map = new char[5][5];
            boolean ok = true;
            Queue<int[]> q = new ArrayDeque<>();
            for(int j=0;j<5;j++){
                map[j] = places[i][j].toCharArray();
                for(int k=0;k<5;k++){
                    if(map[j][k] == 'P'){
                        q.add(new int[] {j,k});
                    }
                }
            }

            while(!q.isEmpty()){
                int[] node = q.poll();

                for(int d=0;d<4;d++){
                    int ny = node[0] + dy[d];
                    int nx = node[1] + dx[d];

                    if(ny<0 || nx<0 || ny>=5 || nx>=5) continue;
                    if(map[ny][nx] == 'P'){
                        ok = false;
                        break;
                    }
                    if(map[ny][nx] == 'O'){
                        for(int d1=0;d1<4;d1++){
                            int nny = ny + dy[d1];
                            int nnx = nx + dx[d1];
                            if(nny == node[0] && nnx == node[1]) continue;
                            if(nny<0 || nnx<0 || nny>=5 || nnx>=5) continue;
                            if(map[nny][nnx] == 'P'){
                                ok = false;
                                break;
                            }
                        }

                    }
                }
                if(!ok) break;
            }

            if(ok) answer[i] = 1;

        }

        return answer;
    }
}
