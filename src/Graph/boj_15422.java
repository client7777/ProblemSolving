package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class boj_15422 {
	static ArrayList<Node>[] graph;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int f = Integer.parseInt(st.nextToken());
		int s = Integer.parseInt(st.nextToken());
		int t = Integer.parseInt(st.nextToken());

		graph = new ArrayList[n];
		for(int i = 0; i < n; i++){
			graph[i] = new ArrayList<>();
		}

		for(int i = 0; i < m; i++){
			st = new StringTokenizer(br.readLine());

			// 양방향 간선
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			graph[u].add(new Node(v, c));
			graph[v].add(new Node(u, c));
		}

		long[] startFromS = dijkstra(n, s);
		long[] startFromT = dijkstra(n, t);

		long minCost = Long.MAX_VALUE;

		for(int i = 0; i < f; i++){
			st = new StringTokenizer(br.readLine());
			//항공편은 단방향
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());

			minCost = Math.min(minCost, startFromS[u] + startFromT[v]);
		}

		System.out.println(minCost);
	}

	static long[] dijkstra(int n, int start){
		long[] dist = new long[n];
		Arrays.fill(dist, Long.MAX_VALUE);
		dist[start] = 0;

		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(start, 0));

		while(!pq.isEmpty()){
			Node cur = pq.poll();
			int curNode = cur.node;
			long curCost = cur.cost;

			if(curCost > dist[curNode]){
				continue;
			}

			for(Node next : graph[curNode]){
				int nextNode = next.node;
				long nextCost = next.cost;

				if(dist[nextNode] > nextCost + curCost){
					dist[nextNode] = nextCost + curCost;
					pq.add(new Node(nextNode, dist[nextNode]));
				}
			}
		}

		return dist;
	}

	static class Node implements Comparable<Node>{
		int node;
		long cost;

		public Node(int node, long cost) {
			this.node = node;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node o) {
			return Long.compare(cost, o.cost);
		}
	}
}
