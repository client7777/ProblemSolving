package BFS_DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_16469 {
	static int r;
	static int c;
	static char[][] map;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());

		map = new char[r + 1][c + 1];
		for(int i = 1; i <= r; i++){
			String row = br.readLine();
			for(int j = 1; j <= c; j++){
				map[i][j] = row.charAt(j-1);
			}
		}

		st = new StringTokenizer(br.readLine());
		int nuckX = Integer.parseInt(st.nextToken());
		int nuckY = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		int swingX = Integer.parseInt(st.nextToken());
		int swingY = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		int changX = Integer.parseInt(st.nextToken());
		int changY = Integer.parseInt(st.nextToken());

		int[][] nuckDist = bfs(nuckX, nuckY);
		int[][] swingDist = bfs(swingX, swingY);
		int[][] changDist = bfs(changX, changY);

		int minDist = Integer.MAX_VALUE;
		int count = 0;

		for(int i = 1; i <= r; i++){
			for(int j = 1; j <= c; j++){

				if(map[i][j] == '1') continue;

				if(nuckDist[i][j] == -1 || swingDist[i][j] == -1 || changDist[i][j] == -1){
					continue;
				}

				int sum = Math.max(Math.max(nuckDist[i][j], swingDist[i][j]), changDist[i][j]);

				if(sum < minDist){
					minDist = sum;
					count = 1;
				}
				else if(sum == minDist){
					count++;
				}
			}
		}

		if(minDist == Integer.MAX_VALUE){
			System.out.println(-1);
		}
		else {
			System.out.println(minDist);
			System.out.println(count);
		}

	}

	static int[][] bfs(int x,int y){

		int[][] dist = new int[r+1][c+1];
		for(int i = 1; i <= r; i++){
			for(int j = 1; j <= c; j++){
				dist[i][j] = -1;
			}
		}

		dist[x][y] = 0;

		Queue<Node> q = new LinkedList<>();
		q.add(new Node(x,y));

		while(!q.isEmpty()){
			Node cur = q.poll();
			int curX = cur.x;
			int curY = cur.y;

			for(int dir=0; dir<4; dir++){
				int nX = curX + dx[dir];
				int nY = curY + dy[dir];

				if(OOB(nX,nY) || map[nX][nY] == '1' || dist[nX][nY] != -1){
					continue;
				}

				dist[nX][nY] = dist[curX][curY] + 1;
				q.add(new Node(nX,nY));
			}
		}

		return dist;
	}

	static boolean OOB(int x,int y){
		return x < 1 || y < 1 || x > r || y > c;
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
