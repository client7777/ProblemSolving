package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class boj_20128 {
	static int n;
	static int m;
	static ArrayList<Node>[] graph;
	static long[][] dist;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		dist = new long[2][n+1];
		graph = new ArrayList[n + 1];
		for(int i = 1; i <= n; i++){
			graph[i] = new ArrayList<>();
			dist[0][i] = Long.MAX_VALUE;
			dist[1][i] = Long.MAX_VALUE;
		}

		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			long cost = Long.parseLong(st.nextToken());
			
			// 무방향 그래프
			graph[u].add(new Node(v, cost, 0));
			graph[v].add(new Node(u, cost, 0));
		}

		StringBuilder sb = new StringBuilder();

		dijkstra();
		for(int i = 1; i <= n; i++) {

			sb.append(dist[1][i] == Long.MAX_VALUE ? -1 : dist[1][i]).append(" ")
				.append(dist[0][i] == Long.MAX_VALUE ? -1 : dist[0][i]);
			sb.append("\n");
		}
		
		System.out.print(sb);
	}

	static void dijkstra(){
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(1, 0, 0));

		dist[0][1] = 0;

		while(!pq.isEmpty()){
			Node cur = pq.poll();
			int curNode = cur.node;
			long curCost = cur.cost;
			int curParity = cur.parity;

			if(curCost > dist[curParity][curNode]){
				continue;
			}

			for(Node next : graph[curNode]){
				int nextNode = next.node;
				long nextCost = next.cost;
				int nextParity = (int)(curParity + (nextCost % 2)) % 2; // 비용에 따라 홀짝 여부가 달라짐

				if(dist[nextParity][nextNode] > curCost + nextCost){
					dist[nextParity][nextNode] = curCost + nextCost;
					pq.add(new Node(nextNode, dist[nextParity][nextNode], nextParity));
				}
			}
		}
	}

	static class Node implements Comparable<Node>{
		int node;
		long cost;
		int parity;

		public Node(int node, long cost, int parity) {
			this.node = node;
			this.cost = cost;
			this.parity = parity;
		}

		@Override
		public int compareTo(Node o) {
			return Long.compare(this.cost, o.cost);
		}
	}
}

// 경로의 누적합이 짝수/홀수로 바뀌는 동적 상태를 고려
// 홀짝은 현재 상태의 parity + 간선 비용의 parity로 결정