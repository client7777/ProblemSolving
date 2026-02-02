package BFS_DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_4677 {
	static int n;
	static int m;
	static int[] dx = {-1, 0, 1, 0, -1, -1, 1, 1};
	static int[] dy = {0, 1, 0, -1, -1, 1, -1, 1};
	static char[][] map;
	static boolean[][] visit;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());

			if(n == 0 && m == 0){
				break;
			}

			int cnt = 0;

			map = new char[n][m];
			visit = new boolean[n][m];

			for(int i = 0; i < n; i++){
				String str = br.readLine();
				for(int j = 0; j < m; j++){
					map[i][j] = str.charAt(j);
				}
			}

			for(int i = 0; i < n; i++){
				for(int j = 0; j < m; j++){
					if(!visit[i][j] && map[i][j] == '@'){
						cnt++;
						dfs(i, j);
					}
				}
			}
			sb.append(cnt).append('\n');
		}

		System.out.print(sb);
	}

	static void dfs(int x,int y){
		visit[x][y] = true;

		for(int dir = 0; dir < 8; dir++){
			int nX = x + dx[dir];
			int nY = y + dy[dir];

			if(OOB(nX,nY) || visit[nX][nY] || map[nX][nY] == '*'){
				continue;
			}

			dfs(nX,nY);
		}
	}

	static boolean OOB(int x,int y){
		return x < 0 || y < 0 || x >= n || y >= m;
	}
}
