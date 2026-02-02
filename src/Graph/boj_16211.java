package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class boj_16211 {
	static final long INF = Long.MAX_VALUE;
	static int n;
	static int m;
	static int k;
	static ArrayList<Node>[] graph;
	static ArrayList<Integer> chaser = new ArrayList<>();
	static ArrayList<Integer> answer = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		graph = new ArrayList[n + 1];
		for (int i = 1; i <= n; i++) {
			graph[i] = new ArrayList<>();
		}

		while (m-- > 0) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken());

			//양방향, 무방향 그래프
			graph[a].add(new Node(b, t));
			graph[b].add(new Node(a, t));
		}

		st = new StringTokenizer(br.readLine());
		while (st.hasMoreTokens()) {

			// 추종자가 사는 지점
			chaser.add(Integer.parseInt(st.nextToken()));
		}

		long[] distFromBaek = dijkstra();
		long[] distFromChaser = dijkstra2();

		for(int i = 2; i <= n; i++){
			if(distFromBaek[i] < distFromChaser[i]){
				answer.add(i);
			}
		}

		if (answer.isEmpty()) {
			System.out.print(0);
		}
		else {
			for(int val : answer){
				System.out.print(val + " ");
			}
		}
	}

	static long[] dijkstra() {

		long[] dist = new long[n + 1];
		for (int i = 1; i <= n; i++) {
			dist[i] = INF;
		}
		dist[1] = 0;

		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(1, 0));

		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			int curNode = cur.node;
			long curTime = cur.time;

			for(Node next : graph[curNode]) {
				int nextNode = next.node;
				long nextTime = next.time;

				if(dist[nextNode] > curTime + nextTime) {
					dist[nextNode] = curTime + nextTime;
					pq.add(new Node(nextNode, dist[nextNode]));
				}
			}
		}

		return dist;
	}

	static long[] dijkstra2() {
		long[] dist = new long[n + 1];
		for (int i = 1; i <= n; i++) {
			dist[i] = INF;
		}

		PriorityQueue<Node> pq = new PriorityQueue<>();

		for(int startPoint : chaser) {
			dist[startPoint] = 0;
			pq.add(new Node(startPoint, 0));
		}

		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			int curNode = cur.node;
			long curTime = cur.time;

			for(Node next : graph[curNode]) {
				int nextNode = next.node;
				long nextTime = next.time;

				if(dist[nextNode] > curTime + nextTime) {
					dist[nextNode] = curTime + nextTime;
					pq.add(new Node(nextNode, dist[nextNode]));
				}
			}
		}

		return dist;
	}

	static class Node implements Comparable<Node> {
		int node;
		long time;

		public Node(int node, long time) {
			this.node = node;
			this.time = time;
		}

		@Override
		public int compareTo(Node o) {
			return Long.compare(time, o.time);
		}
	}
}

// 집이 될 수 있는 곳은 추종자들보다 짧은 시간내에 도착해야됨. 동시에 도착한 것도 잡힌 것으로 간주
// 시작 노드 1, 1번노드에는 집이 없음

