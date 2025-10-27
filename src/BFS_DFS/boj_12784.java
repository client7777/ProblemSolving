package BFS_DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class boj_12784 {
	static ArrayList<Node>[] graph;
	static boolean[] visit;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine());

		while (testCase-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());

			graph = new ArrayList[n+1];
			for(int i = 1; i <=n; i++){
				graph[i] = new ArrayList<Node>();
			}

			for(int i = 0; i < m; i++){
				st = new StringTokenizer(br.readLine());
				int u = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());

				graph[u].add(new Node(v,c));
				graph[v].add(new Node(u,c));
			}

			visit = new boolean[n+1];
			System.out.println(dfs(1, 0));
		}
	}

	static int dfs(int node, int edgeCost){
		visit[node] = true;
		boolean isLeaf = true;

		int cost = 0;

		for(Node next : graph[node]){
			int nextNode = next.node;
			int nextCost = next.cost;

			if(visit[nextNode]){
				continue;
			}

			isLeaf = false;

			cost += Math.min(nextCost, dfs(nextNode, nextCost));
		}

		if(isLeaf){
			return edgeCost;
		}

		return cost;
	}

	static class Node{
		int node;
		int cost;

		public Node(int node, int cost) {
			this.node = node;
			this.cost = cost;
		}
	}
}
