import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int n;
	static int m;
	static char[][] map;
	static int[][] visited;
	static int cnt = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		visited = new int[n][m];
		map = new char[n][m];
		for (int i = 0; i < n; i++) {
			String str = br.readLine();
			for (int j = 0; j < m; j++) {
				map[i][j] = str.charAt(j);
			}
		}

		int depth = 1;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (visited[i][j] == 0) {
					dfs(i, j, depth);
					depth++;
				}
			}
		}

		System.out.print(cnt);
	}

	static void dfs(int x, int y, int depth) {

		visited[x][y] = depth;

		char dir = map[x][y];

		int nX = x;
		int nY = y;
		switch (dir) {
			case 'N':
				nX--;
				break;
			case 'S':
				nX++;
				break;
			case 'E':
				nY++;
				break;
			case 'W':
				nY--;
				break;
		}

		if(visited[nX][nY] == 0){
			dfs(nX, nY, depth);
		}
		else if(visited[nX][nY] == depth){
			cnt++;
		}
	}
}