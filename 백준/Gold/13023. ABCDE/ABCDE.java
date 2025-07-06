import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static int n;
	static int m;
	static boolean[] visit;
	static ArrayList<Integer>[] graph;
	static int answer = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		visit = new boolean[n];

		graph = new ArrayList[n];
		for(int i = 0; i < n; i++){
			graph[i] = new ArrayList<>();
		}

		for(int i = 0; i < m; i++){
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			graph[a].add(b);
			graph[b].add(a);
		}

		for(int i = 0; i < n; i++){

			visit[i] = true;
			dfs(i, 0);
			visit[i] = false;
			
			if(answer == 1){
				break;
			}
		}
		
		System.out.print(answer);
	}

	static void dfs(int node, int depth){

		if(depth == 4){
			answer = 1;
			return;
		}

		for(int next : graph[node]){
			if(!visit[next]){
				visit[next] = true;
				dfs(next, depth + 1);
				visit[next] = false;
			}
		}
	}
}
