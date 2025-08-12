import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int n;
	static int m;
	static int k;
	static int s;
	static int t;
	static ArrayList<Node>[] graph;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		s = Integer.parseInt(st.nextToken());
		t = Integer.parseInt(st.nextToken());

		graph = new ArrayList[n + 1];
		for(int i = 1; i <= n; i++){
			graph[i] = new ArrayList<Node>();
		}

		for(int i = 0; i < m; i++){
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			//단방향 그래프
			graph[u].add(new Node(v, w));
		}

		dijkstra();
	}

	static void dijkstra(){
		//노드 번호, 현재까지의 총 비용을 k로 나눈 나머지
		int[][] cost = new int[n+1][k];
		for(int i = 1; i <= n; i++){
			Arrays.fill(cost[i], Integer.MAX_VALUE);
		}
		cost[s][0] = 0;
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(s, 0));

		while(!pq.isEmpty()){
			Node cur = pq.poll();
			int curNode = cur.node;
			int curCost = cur.cost;
			int curDiv = curCost % k;

			if(curCost > cost[curNode][curDiv]){
				continue;
			}

			for(Node next : graph[curNode]){
				int nextNode = next.node;
				int nextCost = next.cost;

				int newCost = curCost + nextCost;
				int newDiv = (curDiv + nextCost) % k;

				if(cost[nextNode][newDiv] > newCost){
					cost[nextNode][newDiv] = newCost;
					pq.add(new Node(nextNode, newCost));
				}
			}
		}

		System.out.print(cost[t][0] == Integer.MAX_VALUE ? "IMPOSSIBLE" : cost[t][0]);
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