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
	static final int INF = 987654321;
	static ArrayList<Node>[] graph;
	static ArrayList<Integer> house = new ArrayList<>();
	static ArrayList<Integer> conv = new ArrayList<>();
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
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			graph[a].add(new Node(b,c));
			graph[b].add(new Node(a,c));
		}

		st = new StringTokenizer(br.readLine());
		int p = Integer.parseInt(st.nextToken());
		int q = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < p; i++){
			//집의 후보지
			house.add(Integer.parseInt(st.nextToken()));
		}

		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < q; i++){
			//편의점의 위치
			conv.add(Integer.parseInt(st.nextToken()));
		}

		dijkstra();
	}

	static void dijkstra(){
		int[] dist = new int[n+1];
		Arrays.fill(dist, INF);

		PriorityQueue<Node> pq = new PriorityQueue<>();

		for(int start : conv){
			dist[start] = 0;
			pq.add(new Node(start, 0));
		}

		while (!pq.isEmpty()){
			Node cur = pq.poll();
			int curNode = cur.node;
			int curDist = cur.dist;

			if(curDist > dist[curNode]){
				continue;
			}

			for(Node next : graph[curNode]){
				int nextNode = next.node;
				int nextDist = next.dist;

				if(dist[nextNode] > curDist + nextDist){
					dist[nextNode] = curDist + nextDist;
					pq.add(new Node(nextNode, dist[nextNode]));
				}
			}
		}

		int answer = n+1;
		int minDist = INF;

		for(int candidate : house){
			if(dist[candidate] < minDist || dist[candidate] == minDist && candidate < answer){
				answer = candidate;
				minDist = dist[candidate];
			}
		}

		System.out.print(answer);
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