package BFS_DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class boj_8783 {
	static int n;
	static boolean [][] map;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int test_case = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		while (test_case-- > 0) {
			n = Integer.parseInt(br.readLine());
			map = new boolean[n + 2][n + 2];

			for(int i = 1; i <= n; i++) {
				String str = br.readLine();
				for(int j = 1; j <= n; j++) {
					map[i][j] = str.charAt(j-1) == '#';
				}
			}

			sb.append(bfs()).append('\n');
		}

		System.out.print(sb);
	}

	static int bfs(){
		int cnt = (n + 2) * (n + 2);

		Queue<Node> q = new LinkedList<>();
		q.add(new Node(0, 0));
		map[0][0] = true;

		while(!q.isEmpty()){
			Node cur = q.poll();
			int curX = cur.x;
			int curY = cur.y;

			cnt--;

			for(int dir = 0; dir < 4; dir++) {
				int nX = curX + dx[dir];
				int nY = curY + dy[dir];

				if(OOB(nX,nY) || map[nX][nY]) {
					continue;
				}

				q.add(new Node(nX, nY));
				map[nX][nY] = true;
			}
		}

		return cnt;
	}

	static boolean OOB(int x,int y){
		return x < 0 || y < 0 || x >= n + 2 || y >= n + 2;
	}

	static class Node{
		int x;
		int y;

		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
