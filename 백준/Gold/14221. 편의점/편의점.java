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
	static ArrayList<Node>[] graph;
	static ArrayList<Integer> houseCandidate = new ArrayList<>();
	static ArrayList<Integer> isTarget = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		graph = new ArrayList[n+1];
		for(int i=1; i<=n; i++){
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
		for(int i = 0; i < p; i++){
			int candidate = Integer.parseInt(st.nextToken());
			houseCandidate.add(candidate);
		}

		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < q; i++){
			int target = Integer.parseInt(st.nextToken());
			isTarget.add(target);
		}

		dijkstra();
	}

	static void dijkstra(){
		int[] dist = new int[n+1];
		Arrays.fill(dist, INF);

		PriorityQueue<Node> pq = new PriorityQueue<>();

		for(int t : isTarget){
			dist[t] = 0;
			pq.add(new Node(t, 0)); // 여러 target에서 출발
		}

		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			if (cur.dist > dist[cur.node]) continue;

			for(Node next : graph[cur.node]) {
				if(dist[next.node] > cur.dist + next.dist){
					dist[next.node] = cur.dist + next.dist;
					pq.add(new Node(next.node, dist[next.node]));
				}
			}
		}

		// houseCandidate 중 가장 가까운 후보 찾기
		int answer = n+1;
		int minDist = INF;
		for(int candidate : houseCandidate){
			if(dist[candidate] < minDist || (dist[candidate] == minDist && candidate < answer)){
				minDist = dist[candidate];
				answer = candidate;
			}
		}
		
		System.out.println(answer);
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
			return Integer.compare(this.node, o.node);
		}
	}
}
