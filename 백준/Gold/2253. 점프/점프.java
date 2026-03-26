import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int answer = 0;
	static boolean[] map;
	static int n;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		map = new boolean[n + 1];
		for(int i = 0; i < m; i++) {
			int ban = Integer.parseInt(br.readLine());
			map[ban] = true;
		}

		bfs();
		System.out.print(answer);
	}

	static void bfs(){
		Queue<Node> q = new LinkedList<>();
		boolean[][] visited = new boolean[n + 1][n + 1];

		q.add(new Node(1, 0, 0)); // pos, jump, count

		while(!q.isEmpty()){
			Node cur = q.poll();

			if(cur.pos == n){
				answer = cur.count;
				return;
			}

			int[] d = {cur.jump - 1, cur.jump, cur.jump + 1};
			for(int i = 0; i < 3; i++){
				int nextJump = d[i];

				if(nextJump <= 0) {
					continue;
				}

				int nextPos = cur.pos + nextJump;

				if(OOB(nextPos)) {
					continue;
				}
				if(map[nextPos]) {
					continue;
				}
				if(visited[nextPos][nextJump]) {
					continue;
				}

				visited[nextPos][nextJump] = true;
				q.add(new Node(nextPos, nextJump, cur.count + 1));
			}
		}

		answer = -1; // 못 가는 경우
	}

	static boolean OOB(int pos){
		return pos < 1 || pos > n;
	}

	static class Node {
		int pos;
		int jump;
		int count;

		public Node(int pos, int jump, int count) {
			this.pos = pos;
			this.jump = jump;
			this.count = count;
		}
	}
}