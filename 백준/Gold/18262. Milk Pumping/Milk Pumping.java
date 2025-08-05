import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int n;
	static int m;
	static ArrayList<Node>[] graph;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		graph = new ArrayList[n+1];
		for(int i = 1; i <= n; i++){
			graph[i] = new ArrayList<>();
		}

		ArrayList<Integer> flowList = new ArrayList<>();

		for(int i = 0; i < m; i++){
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int f = Integer.parseInt(st.nextToken());

			flowList.add(f);

			graph[u].add(new Node(v,c,f));
			graph[v].add(new Node(u,c,f));
		}

		flowList.sort(Comparator.comparingInt(o->o));

		double max = 0.0;
		for(int f : flowList){
			long minCost = dijkstra(f);

			if(minCost == -1){
				continue;
			}

			double ratio = (double)f / minCost;
			max = Math.max(max, ratio);
		}

		System.out.print((long)(max * 1_000_000));
	}

	static long dijkstra(int minFlow){
		long[] cost = new long[n+1];
		for(int i = 1; i <= n; i++){
			cost[i] = Long.MAX_VALUE;
		}

		cost[1] = 0;

		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(1, 0, 0));

		while (!pq.isEmpty()){

			Node cur = pq.poll();
			int curNode = cur.node;
			long curCost = cur.cost;

			if(curCost > cost[curNode]){
				continue;
			}

			for(Node next : graph[curNode]){
				int nextNode = next.node;
				long nextCost = next.cost;
				int nextFlow = next.flow;

				if(nextFlow < minFlow){
					continue;
				}

				if(cost[nextNode] > curCost + nextCost){
					cost[nextNode] = curCost + nextCost;
					pq.add(new Node(nextNode, cost[nextNode], 0));
				}
			}
		}

		return cost[n] == Long.MAX_VALUE ? -1 : cost[n];
	}

	static class Node implements Comparable<Node>{
		int node;
		long cost;
		int flow;

		public Node(int node, long cost, int flow) {
			this.node = node;
			this.cost = cost;
			this.flow = flow;
		}

		@Override
		public int compareTo(Node o) {
			return Long.compare(this.cost, o.cost);
		}
	}
}