public class PG_쿼드압축후개수세기 {
    static int N, size;
    static int[][] map;
    static boolean[][] checked;

    public int[] solution(int[][] arr) {
        int[] answer = new int[2];

        N = arr.length;
        size = arr.length;
        map = new int[N][N];
        checked = new boolean[N][N];

        // 첫 개수 카운트
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                map[i][j] = arr[i][j];
                if(arr[i][j] == 0){
                    answer[0]++;
                }else{
                    answer[1]++;
                }
            }
        }

        while(N >= 2){
            for(int i=0; i<size; i+=N){
                for(int j=0; j<size; j+=N){
                    if(checked[i][j]) continue;
                    int num = map[i][j];
                    int value = quard(num, i, j);
                    if(value != 0){
                        answer[num] -= value - 1; // 1개는 남겨놔야함
                    }
                }
            }

            // 절반으로 줄이기
            N = N/2;
        }

        return answer;
    }

    static int quard(int num, int y, int x){
        for(int i=y;i<y+N;i++){
            for(int j=x;j<x+N;j++){
                if(map[i][j] != num){
                    return 0;
                }
            }
        }
        // 무사히 통과했다면
        for(int i=y;i<y+N;i++){
            for(int j=x;j<x+N;j++){
                checked[i][j] = true;
            }
        }

        return N*N;
    }
}
