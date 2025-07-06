import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int r;
	static int c;
	static int k;
	static int answer = 0;
	static boolean[][] visit;
	static char[][] map;
	static int startX;
	static int startY;
	static int endX;
	static int endY;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		visit = new boolean[r][c];
		map = new char[r][c];

		for(int i = 0; i < r; i++){
			String str = br.readLine();
			for(int j = 0; j < c; j++){
				map[i][j] = str.charAt(j);
			}
		}

		startX = r-1;
		startY = 0;
		endX = 0;
		endY = c-1;

		visit[startX][startY] = true;
		dfs(startX, startY, 1);

		System.out.print(answer);

	}

	static void dfs(int x, int y, int dist){
		if(x == endX && y == endY){
			if(dist == k){
				answer++;
			}

			return;
		}

		for(int dir = 0; dir < 4; dir++){
			int nX = x + dx[dir];
			int nY = y + dy[dir];

			if(OOB(nX,nY) || visit[nX][nY] || map[nX][nY] == 'T'){
				continue;
			}

			visit[nX][nY] = true;
			dfs(nX, nY, dist + 1);
			visit[nX][nY] = false;
		}
	}

	static boolean OOB(int x,int y){
		return x < 0 || y < 0 || x >= r || y >= c;
	}
}
