import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static int n;
	static int m;
	static int[] parent;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int test_case = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		while (test_case-- > 0) {
			n = Integer.parseInt(br.readLine());
			m = Integer.parseInt(br.readLine());

			parent = new int[n+1];
			for(int i = 1; i <= n; i++){
				parent[i] = i;
			}

			StringTokenizer st;
			for(int i = 0; i < m; i++){
				st = new StringTokenizer(br.readLine());
				int u = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());

				union(u,v);
			}

			Set<Integer> set = new HashSet<>();
			for(int i = 1; i <= n; i++){
				set.add(find(parent[i]));
			}

			sb.append(m == n-1 && set.size() == 1 ? "tree" : "graph").append('\n');
		}

		System.out.print(sb);
	}

	static int find(int x){
		if(x == parent[x]) return x;
		return parent[x] = find(parent[x]);
	}

	static void union(int x,int y){
		int rootX = find(x);
		int rootY = find(y);

		if(rootX != rootY){
			parent[rootY] = rootX;
		}
	}
}