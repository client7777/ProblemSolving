package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class boj_10652 {
	static int b;
	static int e;
	static int p;
	static int n;
	static int m;
	static ArrayList<Integer>[] graph;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		b = Integer.parseInt(st.nextToken());
		e = Integer.parseInt(st.nextToken());
		p = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		graph = new ArrayList[n+1];
		for(int i = 1; i <= n; i++){
			graph[i] = new ArrayList<>();
		}

		for(int i = 0; i < m; i++){
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());

			graph[u].add(v);
			graph[v].add(u);
		}

		int[] distB = dijkstra(1, b);
		int[] distE = dijkstra(2, e);
		int[] distP = dijkstra(n, p);

		int minDist = Integer.MAX_VALUE;

		//가능한 모든 지점 i에서 만나서 같이 가는 경우 계산
		for(int i = 1; i <= n; i++){
			minDist = Math.min(minDist, distB[i] + distE[i] + distP[i]);
		}

		System.out.println(minDist);
	}

	static int[] dijkstra(int start,int cost){
		int[] dist = new int[n+1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[start] = 0;

		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(start, 0));

		while(!pq.isEmpty()){
			Node cur = pq.poll();
			int curNode = cur.node;
			int curCost = cur.cost;

			for(int next : graph[curNode]){
				int nextCost = curCost + cost;

				if(dist[next] > nextCost){
					dist[next] = nextCost;
					pq.add(new Node(next, nextCost));
				}
			}
		}

		return dist;
	}

	static class Node implements Comparable<Node>{
		int node;
		int cost;

		public Node(int node, int cost) {
			this.node = node;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.cost, o.cost);
		}
	}
}
