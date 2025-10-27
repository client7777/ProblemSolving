import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int r;
	static int c;
	static char[][] map;
	//U, R, D, L
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	static int startX;
	static int startY;
	static ArrayList<Character> record = new ArrayList<>();
	static boolean[][] visit;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());

		map = new char[r + 1][c + 1];
		for (int i = 1; i <= r; i++) {
			String str = br.readLine();
			for (int j = 1; j <= c; j++) {
				map[i][j] = str.charAt(j - 1);
			}
		}

		st = new StringTokenizer(br.readLine());
		startX = Integer.parseInt(st.nextToken());
		startY = Integer.parseInt(st.nextToken());

		visit = new boolean[r + 1][c + 1];

		char[] ch = br.readLine().toCharArray();

		for (char c : ch) {

			switch (c) {
				case 'U':
					startX--;
					break;
				case 'L':
					startY--;
					break;
				case 'R':
					startY++;
					break;
				case 'D':
					startX++;
					break;
				case 'W':
					bfs();
					break;
			}
		}

		visit[startX][startY] = true;

		for(int dir = 0; dir < 4; dir++) {
			int nX = startX + dx[dir];
			int nY = startY + dy[dir];

			if(OOB(nX,nY) || visit[nX][nY]){
				continue;
			}

			visit[nX][nY] = true;
		}

		StringBuilder sb = new StringBuilder();
		for(int i = 1; i <= r; i++) {
			for(int j = 1; j <= c; j++) {
				if(visit[i][j]){
					sb.append('.');
				}
				else{
					sb.append('#');
				}
			}
			sb.append('\n');
		}

		System.out.print(sb);
	}

	static void bfs(){
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(startX, startY));

		visit[startX][startY] = true;

		while (!q.isEmpty()) {
			Node cur = q.poll();
			int curX = cur.x;
			int curY = cur.y;

			for(int dir = 0; dir < 4; dir++){
				int nX = curX + dx[dir];
				int nY = curY + dy[dir];

				if(OOB(nX,nY) || visit[nX][nY] || map[nX][nY] != map[curX][curY]){
					continue;
				}

				visit[nX][nY] = true;
				q.add(new Node(nX, nY));
			}
		}
	}
	static boolean OOB(int x, int y) {
		return x < 1 || y < 1 || x > r || y > c;
	}

	static class Node {
		int x;
		int y;

		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}