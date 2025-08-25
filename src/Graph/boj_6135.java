package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_6135 {
	static final int INF = 987654321;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int t = Integer.parseInt(st.nextToken());

		int[][] graph = new int[n+1][n+1];

		for(int i = 1; i <= n; i++){
			for(int j = 1; j <= n; j++){
				if(i == j){
					continue;
				}
				graph[i][j] = INF;
			}
		}

		for(int i = 0; i < m; i++){
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			graph[u][v] = Math.min(graph[u][v], c);
		}

		for(int k = 1; k <= n; k++){
			for(int i = 1; i <= n; i++){
				for(int j = 1; j <= n; j++){
					graph[i][j] = Math.min(graph[i][j], Math.max(graph[i][k], graph[k][j]));
				}
			}
		}

		StringBuilder sb = new StringBuilder();
		while (t -- > 0){
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());

			sb.append(graph[u][v] == INF ? -1 : graph[u][v]).append("\n");
		}

		System.out.print(sb);
	}
}
