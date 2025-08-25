import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static final int INF = 987654321;
	static int e;
	static int v;
	static int start;
	static int firstStation;
	static int secondStation;
	static ArrayList<Node>[] graph;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		e = Integer.parseInt(st.nextToken());
		v = Integer.parseInt(st.nextToken());
		start = Integer.parseInt(st.nextToken());
		firstStation = Integer.parseInt(st.nextToken());
		secondStation = Integer.parseInt(st.nextToken());

		graph = new ArrayList[v+1];
		for(int i = 1; i <= v; i++){
			graph[i] = new ArrayList<>();
		}

		for(int i = 0; i< e; i++){
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			graph[a].add(new Node(b,c));
			graph[b].add(new Node(a,c));
		}

		//시작 -> first -> second
		//시작 -> second -> first

		System.out.print(Math.min(dijkstra(start, firstStation) + dijkstra(firstStation, secondStation), dijkstra(start, secondStation) + dijkstra(secondStation, firstStation)));

	}

	static int dijkstra(int start, int end){
		int[] dist = new int[v+1];
		Arrays.fill(dist, INF);
		dist[start] = 0;

		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(start,0));

		while (!pq.isEmpty()){
			Node cur = pq.poll();
			int curNode = cur.node;
			int curDist = cur.dist;

			if(curDist > dist[curNode]){
				continue;
			}

			for(Node next : graph[curNode]){
				int nextNode = next.node;
				int nextDist = next.dist + curDist;

				if(dist[nextNode] > nextDist){
					dist[nextNode] = nextDist;
					pq.add(new Node(nextNode, dist[nextNode]));
				}
			}
		}

		return dist[end];
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