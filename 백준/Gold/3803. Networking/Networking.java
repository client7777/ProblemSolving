import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	static int[] parent;
	static ArrayList<Node> graph;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringBuilder sb = new StringBuilder();
		while (true){
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken()); // 노드의 개수
			if(n == 0) {
				break;
			}
			int m = Integer.parseInt(st.nextToken()); // 간선의 개수
			
			parent = new int[n+1];
			for(int i = 1; i <= n; i++){
				parent[i] = i;
			}
			
			graph = new ArrayList<>();
			for(int i = 0; i < m; i++){
				st = new StringTokenizer(br.readLine());
				int u = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				int cost = Integer.parseInt(st.nextToken());

				graph.add(new Node(u,v,cost));
			}

			Collections.sort(graph);

			sb.append(kruskal()).append('\n');

			st = new StringTokenizer(br.readLine());
		}

		System.out.println(sb);
	}

	static int kruskal(){

		int totalCost = 0;

		for(Node edge : graph){
			int from = edge.from;
			int to = edge.to;

			if(union(from, to)){
				totalCost += edge.cost;
			}
		}

		return	totalCost;
	}

	static int find(int x){
		if(x == parent[x]){
			return x;
		}

		return parent[x] = find(parent[x]);
	}

	static boolean union(int x,int y){
		int rootX = find(x);
		int rootY = find(y);

		if(rootX == rootY){
			return false;
		}

		parent[rootY] = rootX;
		return true;
	}

	static class Node implements Comparable<Node>{
		int from;
		int to;
		int cost;

		public Node(int from, int to, int cost) {
			this.from = from;
			this.to = to;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.cost, o.cost);
		}
	}
}