package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class boj_16167 {
	static final int INF = 987654321;
	static int n;
	static int r;
	static ArrayList<Node>[] graph;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());

		graph = new ArrayList[n+1];
		for(int i = 1; i <= n; i++){
			graph[i] = new ArrayList<>();
		}

		for(int i = 0; i < r; i++){
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()); // 시작 거점
			int b = Integer.parseInt(st.nextToken()); // 도착 거점
			int c = Integer.parseInt(st.nextToken()); // 기본 요금
			int d = Integer.parseInt(st.nextToken()); // 1분당 추가 요금
			int e = Integer.parseInt(st.nextToken()); // 걸리는 시간

			if(e > 10){
				c += (e - 10) * d;
			}

			graph[a].add(new Node(b, c, 0));
		}

		dijkstra();
		System.out.print(sb.toString());
	}

	static void dijkstra(){
		int[] cost = new int[n+1];
		int[] path = new int[n+1];

		for(int i = 1; i <= n; i++){
			cost[i] = INF;
			path[i] = INF;
		}

		cost[1] = 0;

		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(1,0, 1));

		while(!pq.isEmpty()){
			Node cur = pq.poll();

			if(cost[cur.node] > cur.cost){
				continue;
			}

			for(Node next : graph[cur.node]){
				int nextNode = next.node;
				int nextCost = cur.cost + next.cost;
				int nextCnt = cur.cnt + 1;

				if(cost[nextNode] > nextCost){
					cost[nextNode] = nextCost;
					path[nextNode] = nextCnt;

					path[nextNode] = nextCnt;
					pq.add(new Node(nextNode, nextCost, nextCnt));
				}
				else if(cost[nextNode] == nextCost){
					path[nextNode] = Math.min(path[nextNode], nextCnt);
				}
			}
		}

		if(cost[n] == INF){
			sb.append("It is not a great way.");
			return;
		}

		sb.append(cost[n]).append(" ").append(path[n]);
	}
	static class Node implements Comparable<Node>{
		int node;
		int cost;
		int cnt;

		public Node(int node, int cost, int cnt) {
			this.node = node;
			this.cost = cost;
			this.cnt = cnt;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.cost, o.cost);
		}
	}
}
