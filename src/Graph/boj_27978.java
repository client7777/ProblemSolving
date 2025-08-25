package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class boj_27978 {
	static int h;
	static int w;
	static char[][] map;
	static int startX;
	static int startY;
	static int endX;
	static int endY;
	static int[] dx = {-1, 0, 1, 0, -1, -1, 1, 1};
	static int[] dy = {0, -1, 0, 1, -1, 1, -1, 1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		h = Integer.parseInt(st.nextToken());
		w = Integer.parseInt(st.nextToken());

		map = new char[h+1][w+1];

		for(int i = 1; i <= h; i++){
			String line = br.readLine();
			for(int j = 1; j <= w; j++){
				map[i][j] = line.charAt(j - 1);
				if(map[i][j] == 'K'){
					startX = i;
					startY = j;
				}

				if(map[i][j] == '*'){
					endX = i;
					endY = j;
				}
			}
		}

		dijkstra();
	}

	static void dijkstra(){
		int[][] cost = new int[h+1][w+1];
		for(int i = 1; i <= h; i++){
			Arrays.fill(cost[i], Integer.MAX_VALUE);
		}
		cost[startX][startY] = 0;

		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(startX, startY, 0));

		while(!pq.isEmpty()){
			Node cur = pq.poll();
			int curX = cur.x;
			int curY = cur.y;
			int curCost = cur.cost;

			if(curCost > cost[curX][curY]){
				continue;
			}

			for(int dir = 0; dir < 8; dir++){
				int nX = curX + dx[dir];
				int nY = curY + dy[dir];
				int nextCost = curCost + 1;

				if(OOB(nX,nY) || map[nX][nY] == '#'){
					//탐색범위 밖이거나 암초이면 이동 불가
					continue;
				}

				if(dir == 5 || dir == 3 || dir == 7){
					nextCost--;
				}

				if(cost[nX][nY] > nextCost){
					cost[nX][nY] = nextCost;
					pq.add(new Node(nX, nY, nextCost));
				}
			}
		}

		System.out.print(cost[endX][endY] == Integer.MAX_VALUE ? -1 : cost[endX][endY]);
	}

	static boolean OOB(int x,int y){
		return x < 1 || y < 1 || x > h || y > w;
	}

	static class Node implements Comparable<Node>{
		int x;
		int y;
		int cost;

		public Node(int x, int y, int cost) {
			this.x = x;
			this.y = y;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.cost, o.cost);
		}
	}
}
