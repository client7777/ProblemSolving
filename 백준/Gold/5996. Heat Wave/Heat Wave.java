import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int t;
	static int c;
	static int start;
	static int end;
	static ArrayList<Node>[] graph;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		t = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());

		graph = new ArrayList[t+1];
		for(int i = 1; i <= t; i++) {
			graph[i] = new ArrayList<>();
		}

		for(int i = 0; i < c; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());

			graph[u].add(new Node(v, cost));
			graph[v].add(new Node(u, cost));
		}

		dijkstra(start, end);
	}

	static void dijkstra(int start, int end) {
		int[] dist = new int[t+1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[start] = 0;

		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(start, 0));

		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			int curNode = cur.node;
			int curDist = cur.dist;

			if(curDist > dist[curNode]) {
				continue;
			}

			for(Node next : graph[curNode]) {
				int nextNode = next.node;
				int nextDist = curDist + next.dist;

				if(dist[nextNode] > nextDist) {
					dist[nextNode] = nextDist;
					pq.add(new Node(nextNode, nextDist));
				}
			}
		}

		System.out.print(dist[end]);
	}

	static class Node implements Comparable<Node> {
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