package BFS_DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_24513 {
	static int n;
	static int m;
	static int[][] map;
	static int[][] state;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	static Queue<Node> q = new LinkedList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		state = new int[n][m];
		map = new int[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1 || map[i][j] == 2){
					q.add(new Node(i,j,1));
				}
			}
		}

		bfs();

		int type1_cnt = 0;
		int type2_cnt = 0;
		int type3_cnt = 0;

		for(int i = 0; i < n; i++){
			for(int j = 0; j < m; j++){

				switch (map[i][j]){
					case 1:
						type1_cnt++;
						break;

					case 2:
						type2_cnt++;
						break;
					case 3:
						type3_cnt++;
						break;
				}
			}
		}

		System.out.print(type1_cnt + " " + type2_cnt + " " + type3_cnt);
	}

	static void bfs(){

		while(!q.isEmpty()){

			Node cur = q.poll();
			int curX = cur.x;
			int curY = cur.y;
			int curTime = cur.time;

			if(map[curX][curY] == 1 | map[curX][curY] == 2){
				for(int dir=0; dir<4; dir++){
					int nX = curX + dx[dir];
					int nY = curY + dy[dir];

					if(OOB(nX,nY) || map[nX][nY] == -1 || map[nX][nY] == 3){
						continue;
					}

					if(map[nX][nY] == 0){
						map[nX][nY] = map[curX][curY];
						q.add(new Node(nX,nY,curTime + 1));
						state[nX][nY] = curTime;
					}
					else if(state[nX][nY] == curTime && map[nX][nY] != map[curX][curY]){
						map[nX][nY] = 3;
					}
				}
			}
		}
	}

	static boolean OOB(int x,int y){
		return x < 0 || y < 0 || x >= n || y >= m;
	}

	static class Node{
		int x;
		int y;
		int time;

		public Node(int x, int y, int time) {
			this.x = x;
			this.y = y;
			this.time = time;
		}
	}
}