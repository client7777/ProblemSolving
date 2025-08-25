package BFS_DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_3109 {
	static int r;
	static int c;
	static char[][] map;
	static boolean[][] visit;
	static int[] dx = {-1, 0, 1};
	static int[] dy = {1, 1, 1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());

		visit = new boolean[r][c];
		map = new char[r][c];

		for(int i = 0; i < r; i++){
			String row = br.readLine();
			for(int j = 0; j < c; j++){
				map[i][j] = row.charAt(j);
			}
		}

		int answer = 0;
		for(int i = 0; i < r; i++){
			visit[i][0] = true;
			if(dfs(i, 0)){
				answer++;
			}
		}

		System.out.println(answer);
	}

	static boolean dfs(int x,int y){
		visit[x][y] = true;

		if(y == c-1){
			return true;
		}
		for(int dir=0; dir<3; dir++){
			int nX = x + dx[dir];
			int nY = y + dy[dir];

			if(OOB(nX,nY) || visit[nX][nY] || map[nX][nY] == 'x'){
				continue;
			}

			if(dfs(nX,nY)){
				return true;
			}
		}

		return false;
	}

	static boolean OOB(int x,int y){

		return x < 0 || x >= r || y >= c;
	}
}
