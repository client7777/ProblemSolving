import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int n;
	static int m;
	static int k;
	static int[][] map;
	static boolean[][] visit;
	static int[] dx = {-1,0,1,0};
	static int[] dy = {0,1,0,-1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		map = new int[n][n];

		for(int i = 0; i < n; i++){
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++){
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		visit = new boolean[n][n];

		int tot = 0;
		for(int i = 0; i < n; i++){
			for(int j = 0; j < n; j++){
				if(!visit[i][j] && map[i][j] == 0){
					tot += bfs(i, j);
				}
			}
		}

		if(tot == 0 || m - tot < 0 ){
			System.out.print("IMPOSSIBLE");
		}
		else {
			System.out.println("POSSIBLE");
			System.out.print(m - tot);
		}
	}

	static int bfs(int x,int y){
		int cnt = 0;

		Queue<Node> q = new LinkedList<>();
		q.add(new Node(x,y));

		visit[x][y] = true;

		while (!q.isEmpty()){
			Node cur = q.poll();
			int curX = cur.x;
			int curY = cur.y;

			cnt++;

			for(int dir = 0; dir < 4; dir++){
				int nX = curX + dx[dir];
				int nY = curY + dy[dir];

				if(OOB(nX,nY) || visit[nX][nY] || map[nX][nY] == 1){
					continue;
				}

				visit[nX][nY] = true;
				q.add(new Node(nX,nY));
			}
		}

		cnt = (cnt + k - 1) / k;
		return cnt;
	}

	static boolean OOB(int x,int y){
		return x < 0 || y < 0 || x >= n || y >= n;
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