import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
	static ArrayList<Node> graph;
	static int[] parents;
	static int totalNode;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int test_cases = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();

		while (test_cases-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			totalNode = r * c;

			parents = new int[r * c];
			for(int i = 0; i < parents.length; i++) {
				parents[i] = i;
			}
			
			graph = new ArrayList<>();

			for(int i = 0; i < r; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < c-1; j++) {
					graph.add(new Node(i * c + j, i * c + j + 1, Integer.parseInt(st.nextToken())));
				}
			}

			for(int i = 0; i < r-1; i++){
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < c; j++) {
					graph.add(new Node(i *c + j, (i + 1) *c + j, Integer.parseInt(st.nextToken())));
				}
			}



			graph.sort(Comparator.comparingInt(o -> o.cost)); // 간선의 가중치를 기준으로 오름차순 정렬

			sb.append(kruskal()).append('\n');
		}

		System.out.print(sb);
	}

	static int kruskal(){

		int cnt = 0;
		int total = 0;

		for(Node node : graph) {
			int from = node.from;
			int to = node.to;
			int cost = node.cost;

			if(cnt == totalNode -1) break;

			if(find(from) != find(to)) {
				union(from, to);
				total += cost;
				cnt++;
			}
		}

		return total;
	}

	static int find(int x){

		if(x == parents[x]) return x;
		return parents[x] = find(parents[x]);
	}

	static void union(int x, int y){
		int rootX = find(x);
		int rootY = find(y);

		if(rootX != rootY){
			parents[rootY] = rootX;
		}
	}

	static class Node{
		int from;
		int to;
		int cost;

		public Node(int from, int to, int cost) {
			this.from = from;
			this.to = to;
			this.cost = cost;
		}
	}
}