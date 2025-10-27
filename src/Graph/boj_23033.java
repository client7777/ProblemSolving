package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class boj_23033 {
	static ArrayList<Node>[] graph;
	static long[] time;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		graph = new ArrayList[n+1];
		for(int i = 1; i <= n; i++){
			graph[i] = new ArrayList<Node>();
		}

		for(int i = 0; i < m; i++){
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			graph[a].add(new Node(b,t,w));
		}

		time = new long[n + 1];
		Arrays.fill(time, Long.MAX_VALUE);
		dijkstra();
		System.out.print(time[n]);
	}

	static void dijkstra(){
		time[1] = 0;

		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(1,0,0));

		while(!pq.isEmpty()){
			Node cur = pq.poll();
			int curNode = cur.node;
			int curTime = cur.time;

			if(curTime > time[curNode]){
				continue;
			}

			for(Node next : graph[curNode]){
				int nextNode = next.node;
				int nextWait = next.wait;

				int waitTime = 0;

				if(curTime % nextWait != 0){
					waitTime = next.wait - (curTime % nextWait);
				}

				int nextTime = curTime + waitTime + next.time;

				if(time[nextNode] > nextTime){
					time[nextNode] = nextTime;
					pq.add(new Node(nextNode, nextTime, next.wait));
				}
			}
		}
	}

	static class Node implements Comparable<Node>{
		int node;
		int time;
		int wait;

		public Node(int node, int time, int wait) {
			this.node = node;
			this.time = time;
			this.wait = wait;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.time, o.time);
		}
	}
}
