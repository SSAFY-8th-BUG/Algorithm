package studygroup.Week031;

public class PG_행렬테두리회전 {
    static int[][] map;
    static int[] dx = {0,1,0,-1};
    static int[] dy = {1,0,-1,0};
    public int[] solution(int rows, int columns, int[][] queries) {
        map = new int[rows][columns];
        int k = 1;
        for(int i=0; i<rows; i++){
            for(int j=0; j<columns; j++){
                map[i][j] = k;
                k++;
            }
        }
        int[] arr = new int[queries.length];
        for(int i=0; i<queries.length; i++){
            arr[i] = turn(queries[i][0]-1,queries[i][1]-1,queries[i][2]-1,queries[i][3]-1);
        }
        return arr;
    }
    public static int turn(int x, int y, int x1, int y1){
        int start = map[x][y];
        int min = start;
        for(int i=x; i<x1; i++){
            map[i][y] = map[i+1][y];
            min = Math.min(min,map[i][y]);
        }
        for(int i=y; i<y1; i++){
            map[x1][i] = map[x1][i+1];
            min = Math.min(min,map[x1][i]);
        }
        for(int i=x1; i>x; i--){
            map[i][y1] = map[i-1][y1];
            min = Math.min(min,map[i][y1]);
        }
        for(int i=y1; i>y; i--){
            map[x][i] = map[x][i-1];
            min = Math.min(min,map[x][i]);
        }
        map[x][y+1] = start;

        return min;
    }
}
