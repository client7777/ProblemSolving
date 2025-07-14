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

        d = new int[n+1][m+1];

        map = new int[n+1][m+1];
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= m; j++){
                d[i][j] = Math.max(d[i-1][j], d[i][j-1]) + map[i][j];
            }
        }

        System.out.print(d[n][m]);
    }
}
