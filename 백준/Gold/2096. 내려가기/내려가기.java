import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		int[][] map = new int[n+1][3];

		for(int i = 1; i <= n; i++){
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 3; j++){
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int[][] d_max = new int[n+1][3];
		int[][] d_min = new int[n+1][3];

		for(int i = 1; i <= n; i++){
			d_max[i][0] = map[i][0] + Math.max(d_max[i-1][0], d_max[i-1][1]);
			d_max[i][1] = map[i][1] + Math.max(d_max[i-1][0], Math.max(d_max[i-1][1], d_max[i-1][2]));
			d_max[i][2] = map[i][2] + Math.max(d_max[i-1][1], d_max[i-1][2]);

			d_min[i][0] = map[i][0] + Math.min(d_min[i-1][0], d_min[i-1][1]);
			d_min[i][1] = map[i][1] + Math.min(d_min[i-1][0], Math.min(d_min[i-1][1], d_min[i-1][2]));
			d_min[i][2] = map[i][2] + Math.min(d_min[i-1][1], d_min[i-1][2]);
		}
		
		int max = Math.max(d_max[n][0], Math.max(d_max[n][1], d_max[n][2]));
		int min = Math.min(d_min[n][0], Math.min(d_min[n][1], d_min[n][2]));

		System.out.print(max + " " + min);
	}
}