import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static int n;
	static int m;
	static ArrayList<Integer>[] graph;
	static boolean[] visited;
	// static int[] parent;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int test_case = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		while (test_case-- > 0) {
			n = Integer.parseInt(br.readLine());
			m = Integer.parseInt(br.readLine());

			// parent = new int[n+1];
			// for(int i = 1; i <= n; i++){
			// 	parent[i] = i;
			// }

			graph = new ArrayList[n+1];
			for(int i = 1; i <= n; i++){
				graph[i] = new ArrayList<>();
			}

			StringTokenizer st;
			for(int i = 0; i < m; i++){
				st = new StringTokenizer(br.readLine());
				int u = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());

				graph[u].add(v);
				graph[v].add(u);
			}

			visited = new boolean[n+1];
			int connected = 0;

			for(int i = 1; i <= n; i++){
				if(visited[i]){
					continue;
				}

				connected++;
				dfs(i);
			}

			sb.append(m == n-1 &&  connected == 1 ? "tree" : "graph").append("\n");
		}

		System.out.print(sb);
	}

	static void dfs(int node){
		visited[node] = true;

		for(int next : graph[node]){
			if(!visited[next]){
				dfs(next);
			}
		}
	}

	// static int find(int x){
	// 	if(x == parent[x]) return x;
	// 	return parent[x] = find(parent[x]);
	// }
	//
	// static void union(int x,int y){
	// 	int rootX = find(x);
	// 	int rootY = find(y);
	//
	// 	if(rootX != rootY){
	// 		parent[rootY] = rootX;
	// 	}
	// }
}