import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static final int INF = 987654321;
	static int n;
	static int m;
	static int x;
	static ArrayList<Node>[] graph;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());

		graph = new ArrayList[n+1];
		for(int i = 1; i <= n; i++){
			graph[i] = new ArrayList<Node>();
		}

		while (m -- > 0){
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());

			graph[u].add(new Node(v,d));
			graph[v].add(new Node(u,d));
		}

		int[] distFromX = dijkstra();

		int answer = 0;
		for(int i = 1; i <= n; i++){
			answer = Math.max(answer, distFromX[i]);
		}

		System.out.print(2 * answer);
	}

	static int[] dijkstra(){
		int[] dist = new int[n+1];
		Arrays.fill(dist, INF);
		dist[x] = 0;

		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(x, 0));

		while (!pq.isEmpty()){
			Node cur = pq.poll();
			int curNode = cur.node;
			int curDist = cur.dist;

			if(curDist > dist[curNode]){
				continue;
			}

			for(Node next : graph[curNode]){
				int nextNode = next.node;
				int nextDist = curDist + next.dist;

				if(dist[nextNode] > nextDist){
					dist[nextNode] = nextDist;
					pq.add(new Node(nextNode, dist[nextNode]));
				}
			}
		}
		return dist;
	}

	static class Node implements Comparable<Node>{
		int node;
		int dist;

		public Node(int node, int dist) {
			this.node = node;
			this.dist = dist;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.dist, o.dist);
		}
	}
}
