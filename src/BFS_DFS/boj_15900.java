package BFS_DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class boj_15900 {
	static ArrayList<Integer>[] graph;
	static int cnt = 0;
	static boolean[] visit;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		visit = new boolean[n+1];

		graph = new ArrayList[n+1];
		for(int i = 1; i <= n; i++){
			graph[i] = new ArrayList<>();
		}

		StringTokenizer st;
		for(int i = 0; i < n-1; i++){
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			graph[a].add(b);
			graph[b].add(a);
		}

		dfs(1, 0);
		System.out.print(cnt % 2 == 0 ? "No" : "Yes");
	}

	static void dfs(int node, int depth){
		visit[node] = true;

		boolean isLeaf = true;

		for(int next : graph[node]){
			if(!visit[next]){
				dfs(next, depth+1);
				isLeaf = false;
			}
		}

		if(isLeaf){
			cnt += depth;
		}
	}
}
