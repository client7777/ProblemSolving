import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static final int INF = 987654321;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int d = Integer.parseInt(br.readLine());

        int[][] map = new int[n][n];

        for(int i = 0; i < n; i++){
            String str = br.readLine();
            for(int j = 0; j < n; j++){
                if(i == j) continue;
                map[i][j] = str.charAt(j) == 'Y' ? 1 : INF;
            }
        }

        for(int k = 0; k < n; k++){
            for(int i = 0; i < n; i++){
                for(int j = 0; j < n; j++){
                    map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
                }
            }
        }

        int maxDist = -INF;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(i == j) continue;
                maxDist = Math.max(maxDist, map[i][j]);
            }
        }

        System.out.print(maxDist == INF ? -1 : maxDist * d);
    }
}
