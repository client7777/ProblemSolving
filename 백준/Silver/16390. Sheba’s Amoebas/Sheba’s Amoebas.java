import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int n;
	static int m;
	static char[][] map;
	static int[] dx = {-1, 0, 1, 0, -1, -1, 1, 1};
	static int[] dy = {0, 1, 0, -1, -1, 1, -1, 1};
	static boolean[][] visit;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		map = new char[n][m];
		for (int i = 0; i < n; i++) {
			String str = br.readLine();
			for (int j = 0; j < m; j++) {
				map[i][j] = str.charAt(j);
			}
		}

		int cnt = 0;
		visit = new boolean[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if(map[i][j] == '#' && !visit[i][j]) {
					cnt++;
					bfs(i, j);
				}
			}
		}

		System.out.println(cnt);
	}

	static void bfs(int x,int y){
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(x,y));

		while(!q.isEmpty()){
			Node cur = q.poll();
			int curX = cur.x;
			int curY = cur.y;

			for(int dir = 0; dir < 8; dir++){
				int nX = curX + dx[dir];
				int nY = curY + dy[dir];

				if(OOB(nX,nY) || visit[nX][nY] || map[nX][nY] != map[curX][curY]){
					continue;
				}

				visit[nX][nY] = true;
				q.add(new Node(nX,nY));

			}
		}
	}

	static boolean OOB(int x,int y){
		return x < 0 || y < 0 || x >= n || y >= m;
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