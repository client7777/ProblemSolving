import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[][] map = new int[1001][1001];
	static int[] dx = {-1 ,0, 1, 0};
	static int[] dy = { 0, 1, 0, -1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int endX = Integer.parseInt(st.nextToken()) + 500;
		int endY = Integer.parseInt(st.nextToken()) + 500;
		int n = Integer.parseInt(st.nextToken());

		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) + 500;
			int y = Integer.parseInt(st.nextToken()) + 500;

			map[x][y] = 1;
		}

		bfs(endX, endY);
	}

	static void bfs(int endX, int endY) {
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(500,500,0));

		boolean[][] visit = new boolean[1001][1001];
		visit[0][0] = true;

		while(!q.isEmpty()){
			Node cur = q.poll();
			int curX = cur.x;
			int curY = cur.y;
			int curCnt = cur.cnt;

			if(curX == endX && curY == endY){
				System.out.println(curCnt);
				return;
			}

			for(int dir = 0; dir < 4; dir++){
				int nX = curX + dx[dir];
				int nY = curY + dy[dir];

				if(OOB(nX,nY) || map[nX][nY] == 1 || visit[nX][nY]){
					continue;
				}

				visit[nX][nY] = true;
				q.add(new Node(nX,nY,curCnt + 1));
			}
		}
	}

	static boolean OOB(int x,int y){
		return x < 0 || y < 0 || x > 1000 || y > 1000;
	}
	static class Node{
		int x;
		int y;
		int cnt;

		public Node(int x, int y, int cnt) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}
	}
}