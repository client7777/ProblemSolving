import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
	static ArrayList<Integer>[] graph;
	static int[] answer;
	static boolean[] visit;
	static boolean flag = true;
	static int idx = 1;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		visit = new boolean[n + 1];

		graph = new ArrayList[n+1];
		for(int i = 1; i <= n; i++){
			graph[i] = new ArrayList<>();
		}
		
		StringTokenizer st;
		for(int i = 0; i < n-1; i++){
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			
			// 무방향 그래프
			graph[u].add(v);
			graph[v].add(u);
		}

		answer = new int[n+1];

		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++){
			answer[i] = Integer.parseInt(st.nextToken());
		}

		if(answer[0] != 1){
			System.out.print(0);
			return;
		}

		dfs(1);
		System.out.print(flag ? 1 : 0);
	}

	static void dfs(int node){
		if(visit[node]){
			return;
		}

		visit[node] = true;

		HashSet<Integer> set = new HashSet<>();
		for(int next : graph[node]){
			if(!visit[next]){
				set.add(next);
			}
		}

		if(set.isEmpty()){
			return;
		}

		if(set.contains(answer[idx])){
			dfs(answer[idx++]);
		}
		else{
			flag = false;
		}
	}
}