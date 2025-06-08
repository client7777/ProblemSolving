import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int n;
	static int k;
	static int[] apple;
	static int[] dist;
	static ArrayList<Integer>[] graph;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		graph = new ArrayList[n];
		for(int i = 0; i < n; i++){
			graph[i] = new ArrayList<>();
		}

		for(int i = 0; i < n-1; i++){
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());

			graph[u].add(v);
		}

		apple = new int[n];

		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++){
			apple[i] = Integer.parseInt(st.nextToken());
		}

		dist = new int[n];
		Arrays.fill(dist, -1);

		bfs();

		int answer = 0;

		for(int i = 0; i < n; i++){
			if(dist[i] <= k){
				answer += apple[i];
			}
		}

		System.out.print(answer);
	}

	static void bfs(){
		Queue<Integer> q = new LinkedList<>();
		q.add(0);
		dist[0] = 0;

		while (!q.isEmpty()){
			int curNode = q.poll();

			for(int next : graph[curNode]){
				if(dist[next] == -1){
					dist[next] = dist[curNode] + 1;
					q.add(next);
				}
			}
		}
	}
}