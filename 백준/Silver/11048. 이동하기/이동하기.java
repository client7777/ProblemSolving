import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int[] dx = {1,0,1};
    static int[] dy = {0,1,1};
    static int[][] map;
    static int[][] d;
    static int n;
    static int m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        d = new int[n][m];
        for(int i = 0; i < n; i++){
            Arrays.fill(d[i], -1);
        }

        map = new int[n][m];
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.print(dfs(0,0));
    }

    static int dfs(int x,int y){
        if(x == n-1 && y == m-1){
            return map[x][y];
        }

        if(d[x][y] != -1){
            return d[x][y];
        }

        int max = 0;
        for(int dir=0; dir<3; dir++){
            int nX = x + dx[dir];
            int nY = y + dy[dir];

            if(OOB(nX,nY)){
                continue;
            }

            max = Math.max(max, dfs(nX,nY));
        }

        d[x][y] = max + map[x][y];
        return d[x][y];
    }

    static boolean OOB(int x,int y){
        return x >= n || y >= m;
    }
}
