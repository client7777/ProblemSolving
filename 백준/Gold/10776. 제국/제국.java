import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int k;
	static int n;
	static int m;
	static ArrayList<Node>[] graph;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		k = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		graph = new ArrayList[n+1];
		for(int i = 1; i <= n; i++){
			graph[i] = new ArrayList<>();
		}

		for(int i = 0; i < m; i++){
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken());
			int h = Integer.parseInt(st.nextToken());

			graph[a].add(new Node(b,t,h));
			graph[b].add(new Node(a,t,h));
		}

		st = new StringTokenizer(br.readLine());
		int startPosition = Integer.parseInt(st.nextToken());
		int endPosition = Integer.parseInt(st.nextToken());

		System.out.print(dijkstra(startPosition, endPosition));
	}

	static int dijkstra(int start, int end){
		int[][] dist = new int[n+1][k+1];
		for(int i = 1; i <= n; i++){
			Arrays.fill(dist[i], Integer.MAX_VALUE);
		}

		dist[start][k] = 0;

		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(start,0,k));

		while(!pq.isEmpty()){
			Node cur = pq.poll();

			if(cur.time > dist[cur.node][cur.cost]){
				continue;
			}

			for(Node next : graph[cur.node]){
				int nextNode = next.node;
				int nextTime = cur.time + next.time;
				int nextCost = cur.cost - next.cost;

				if(nextCost <= 0){
					continue;
				}

				if(dist[nextNode][nextCost] > nextTime){
					dist[nextNode][nextCost] = nextTime;
					pq.add(new Node(nextNode, nextTime, nextCost));
				}
			}
		}

		int min = Integer.MAX_VALUE;
		for(int i = 1; i <= k; i++){
			min = Math.min(min, dist[end][i]);
		}

		return min == Integer.MAX_VALUE ? -1 : min;
	}

	static class Node implements Comparable<Node>{
		int node;
		int time;
		int cost;

		public Node(int node, int time, int cost) {
			this.node = node;
			this.time = time;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.time, o.time);
		}
	}
}