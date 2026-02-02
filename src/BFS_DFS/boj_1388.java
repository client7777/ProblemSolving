package BFS_DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_1388 {
	static int n;
	static int m;
	static int answer = 0;
	static char[][] map;
	static boolean[][] visit;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		visit = new boolean[n][m];
		map = new char[n][m];
		for(int i = 0; i < n; i++){
			String str = br.readLine();
			for(int j = 0; j < m; j++){
				map[i][j] = str.charAt(j);
			}
		}

		for(int i = 0; i < n; i++){
			for(int j = 0; j < m; j++){
				if(!visit[i][j]){
					bfs(i,j,map[i][j]);
					answer++;
				}
			}
		}

		System.out.print(answer);
	}

	static void bfs(int x,int y, char state){
		visit[x][y] = true;

		if(state == '-'){
			int ny = y + 1;
			while(ny < m && map[x][ny] == '-' && !visit[x][ny]){
				visit[x][ny] = true;
				ny++;
			}
		}
		else if(state == '|'){
			int nx = x + 1;
			while(nx < n && map[nx][y] == '|' && !visit[nx][y]){
				visit[nx][y] = true;
				nx++;
			}
		}
	}
}
