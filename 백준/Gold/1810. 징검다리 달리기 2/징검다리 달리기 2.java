import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static double INF = Double.MAX_VALUE;
	static int n;
	static int f;
	static Node[] nodeInfo;
	static ArrayList<Integer> targetNode = new ArrayList<>();
	static ArrayList<Edge>[] graph;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		f = Integer.parseInt(st.nextToken());

		nodeInfo = new Node[n+1];
		nodeInfo[0] = new Node(0,0);

		for(int i = 1; i <= n; i++){
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			if(y == f){
				targetNode.add(i);
			}

			nodeInfo[i] = new Node(x,y);
		}

		graph = new ArrayList[n+1];
		for(int i = 0; i <= n; i++){
			graph[i] = new ArrayList<>();
		}

		Map<String, Integer> grid = new HashMap<>();
		for (int i = 0; i <= n; i++) {
			String key = nodeInfo[i].x + "," + nodeInfo[i].y;
			grid.put(key, i);
		}

		// 주변 5x5 탐색
		for (int i = 0; i <= n; i++) {
			for (int dx = -2; dx <= 2; dx++) {
				for (int dy = -2; dy <= 2; dy++) {
					if (dx == 0 && dy == 0) continue;
					int nx = nodeInfo[i].x + dx;
					int ny = nodeInfo[i].y + dy;
					String key = nx + "," + ny;
					if (grid.containsKey(key)) {
						int j = grid.get(key);
						double dist = getDist(nodeInfo[i].x, nodeInfo[i].y, nodeInfo[j].x, nodeInfo[j].y);
						graph[i].add(new Edge(j, dist));
						graph[j].add(new Edge(i, dist));
					}
				}
			}
		}

		dijkstra();
	}

	static void dijkstra(){
		double[] dist = new double[n+1];
		Arrays.fill(dist, INF);
		dist[0] = 0;

		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.add(new Edge(0, 0));

		while(!pq.isEmpty()){
			Edge cur = pq.poll();
			int curNode = cur.node;
			double curDist = cur.dist;

			for(Edge next : graph[curNode]){
				int nextNode = next.node;
				double nextDist = curDist + next.dist;

				if(dist[nextNode] > nextDist){
					dist[nextNode] = nextDist;
					pq.add(new Edge(nextNode, nextDist));
				}
			}
		}

		double answer = INF;
		for(int target : targetNode){
			if(dist[target] < answer){
				answer = dist[target];
			}
		}

		System.out.print(answer == INF ? -1 : Math.round(answer));
	}

	static double getDist(int x1, int y1, int x2, int y2){
		return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
	}

	static class Node{
		int x;
		int y;

		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static class Edge implements Comparable<Edge>{
		int node;
		double dist;

		public Edge(int node, double dist) {
			this.node = node;
			this.dist = dist;
		}

		@Override
		public int compareTo(Edge o) {
			return Double.compare(this.dist, o.dist);
		}
	}
}
