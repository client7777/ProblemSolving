import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static HashSet<Integer> set = new HashSet<>();
	static int n;
	static int m;
	static int a;
	static int b;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		a = Integer.parseInt(st.nextToken());
		b = Integer.parseInt(st.nextToken());

		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());

			for(int j = start; j <= end; j++) {
				set.add(j);
			}
		}

		System.out.print(bfs());
	}

	static int bfs(){

		boolean[] visited = new boolean[100_000 + 1];
		visited[0] = true;

		Queue<Node> q = new LinkedList<>();
		q.add(new Node(0,0));

		while (!q.isEmpty()) {
			Node cur = q.poll();
			int curDog = cur.dog;
			int curCnt = cur.cnt;

			if(curDog == n){
				return curCnt;
			}

			int[] next = {a,b};

			for(int n : next) {
				int nextNode = curDog + n;

				if(nextNode > 100_000 || set.contains(nextNode) || visited[nextNode]) {
					continue;
				}

				visited[nextNode] = true;
				q.add(new Node(nextNode, curCnt + 1));
			}
		}

		return -1;
	}

	static class Node{
		int dog;
		int cnt;

		public Node(int dog, int cnt) {
			this.dog = dog;
			this.cnt = cnt;
		}
	}
}