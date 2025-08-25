import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static int n;
	static int[] color;
	static boolean[] visited;
	static ArrayList<Integer>[] graph;
	static int answer = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());

		color = new int[n+1];
		visited = new boolean[n+1];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= n; i++){
			color[i] = Integer.parseInt(st.nextToken());
		}

		graph = new ArrayList[n+1];
		for(int i = 1; i <= n; i++){
			graph[i] = new ArrayList<>();
		}

		for(int i = 0; i < n-1; i++){
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());

			graph[u].add(v);
			graph[v].add(u);
		}


		dfs(1, 0);
		System.out.println(answer);
	}

	static void dfs(int node, int parentColor) {
		if (color[node] != parentColor) {
			answer++;
		}
		visited[node] = true;
		for (int next : graph[node]) {
			if (!visited[next]) {
				dfs(next, color[node]);
			}
		}
	}
}