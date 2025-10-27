package BFS_DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_16929 {
	static int n;
	static int m;
	static char[][] map;
	static int startX;
	static int startY;
	static boolean[][] visited;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		map = new char[n][m];
		for (int i = 0; i < n; i++) {
			String row = br.readLine();
			for (int j = 0; j < m; j++) {
				map[i][j] = row.charAt(j);
			}
		}

		for(int i = 0; i < n; i++){
			for(int j = 0; j < m; j++){

				visited = new boolean[n][m];
				visited[i][j] = true;
				startX = i;
				startY = j;
				dfs(i,j,1,map[i][j]);
			}
		}

		System.out.print("No");
	}

	static void dfs(int x,int y,int cnt, char dot){

		for(int dir=0; dir<4; dir++){
			int nX = x + dx[dir];
			int nY = y + dy[dir];

			if(!OOB(nX,nY)){
				if(map[nX][nY] == dot){
					if(!visited[nX][nY]){
						visited[nX][nY] = true;
						dfs(nX,nY,cnt+1,dot);
					}
					else{
						if(cnt >= 4 && startX == nX && startY == nY){
							// 사이클 발견하면 프로그램 종료
							System.out.print("Yes");
							System.exit(0);
						}
					}
				}
			}
		}
	}

	static boolean OOB(int x,int y){
		return x < 0 || y < 0 || x >= n || y >= m;
	}
}
