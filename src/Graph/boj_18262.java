package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class boj_18262 {
	static int n;
	static int m;
	static ArrayList<Node>[] graph;
	static ArrayList<Integer> flowList = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
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
			int c = Integer.parseInt(st.nextToken());
			int f = Integer.parseInt(st.nextToken());

			flowList.add(f);

			graph[u].add(new Node(v,c,f));
			graph[v].add(new Node(u,c,f));
		}

		flowList.sort(Comparator.comparingInt(o->o));

		double maxRatio = Double.MIN_VALUE;
		for(int f : flowList){

			//모든 flow를 최소 flow로 지정해서 비율의 최댓값 도출
			double minCost = dijkstra(f);
			if(minCost == -1){
				continue;
			}

			double ratio = f / minCost;

			maxRatio = Math.max(maxRatio, ratio);
		}

		System.out.print((long)(maxRatio * 1_000_000));
	}

	static double dijkstra(int minFlow){
		double[] cost = new double[n+1];
		Arrays.fill(cost, Double.MAX_VALUE);
		cost[1] = 0;

		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.add(new Edge(1, 0));

		while(!pq.isEmpty()){
			Edge cur = pq.poll();
			int curNode = cur.node;
			double curCost = cur.cost;

			if(curCost > cost[curNode]){
				continue;
			}

			for(Node next : graph[curNode]){
				int nextNode = next.node;
				double nextCost = next.cost;
				int nextFlow = next.flow;

				if(minFlow > nextFlow){
					continue;
				}

				if(cost[nextNode] > curCost + nextCost){
					cost[nextNode] = curCost + nextCost;
					pq.add(new Edge(nextNode, cost[nextNode]));
				}
			}
		}

		return cost[n] == Double.MAX_VALUE ? -1 : cost[n];
	}

	static class Node{
		int node;
		int cost;
		int flow;

		public Node(int node, int cost, int flow) {
			this.node = node;
			this.cost = cost;
			this.flow = flow;
		}
	}

	static class Edge implements Comparable<Edge>{
		int node;
		double cost;

		public Edge(int node, double cost) {
			this.node = node;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge o) {
			return Double.compare(this.cost, o.cost);
		}
	}
}