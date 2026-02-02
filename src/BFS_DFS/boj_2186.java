package BFS_DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_2186 {
	static int n;
	static int m;
	static int k;
	static String target;
	static char[][] map;
	static int[][][] d;
	static int cnt = 0;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		map = new char[n][m];
		for (int i = 0; i < n; i++) {
			String str = br.readLine();
			for (int j = 0; j < m; j++) {
				map[i][j] = str.charAt(j);
			}
		}

		target = br.readLine();

		d = new int[n][m][target.length()]; // x,y에서 target[depth]를 밟고 이후 문자열을 완성할 수 있는 경우의 수
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				Arrays.fill(d[i][j], -1);
			}
		}

		for(int i = 0; i < n; i++){
			for(int j = 0; j < m; j++){
				if(map[i][j] == target.charAt(0)){
					cnt += dfs(i, j, 0);
				}
			}
		}

		System.out.print(cnt);
	}

	static int dfs(int x, int y, int depth){
		if(d[x][y][depth] != -1){
			return d[x][y][depth];
		}

		if(depth == target.length() - 1){
			d[x][y][depth] = 1;
			return 1;
		}

		int res = 0;

		for(int dir = 0; dir < 4; dir++){
			for(int i = 1; i <= k; i++){
				int nX = x + dx[dir] * i;
				int nY = y + dy[dir] * i;

				if(OOB(nX,nY)) {
					continue;
				}

				if(map[nX][nY] == target.charAt(depth + 1)){
					res += dfs(nX, nY, depth + 1);
				}
			}
		}

		return d[x][y][depth] = res;
	}

	static boolean OOB(int x,int y){
		return x < 0 || y < 0 || x >= n || y >= m;
	}
}
