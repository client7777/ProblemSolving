import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] d;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        d = new int[n][m];
        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        d[0][0] = map[0][0];

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(i + 1 < n){
                    d[i+1][j] = Math.max(d[i+1][j], d[i][j] + map[i+1][j]);

                }

                if(j + 1 < m){
                    d[i][j+1] = Math.max(d[i][j+1], d[i][j] + map[i][j+1]);
                }

                if(i + 1 < n && j + 1 < m){
                    d[i+1][j+1] = Math.max(d[i+1][j+1], d[i][j] + map[i+1][j+1]);
                }
            }
        }

        System.out.print(d[n-1][m-1]);
    }
}
