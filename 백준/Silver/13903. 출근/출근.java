import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int r;
	static int c;
	static int n;
	static int[][] map;
	static int[] dx;
	static int[] dy;
	static ArrayList<Node> list = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st =  new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());

		map = new int[r][c];

		for(int i = 0; i < r; i++) {
			st =  new StringTokenizer(br.readLine());
			for(int j = 0; j < c; j++) {
				//가로 -> 0 세로 -> 1
				map[i][j] = Integer.parseInt(st.nextToken());

				//첫번째 행이면서 세로칸이면 시작지점에 추가
				if(i == 0 && map[i][j] == 1){
					list.add(new Node(i, j));
				}
			}
		}

		n = Integer.parseInt(br.readLine());
		dx = new int[n];
		dy = new int[n];

		for(int i = 0; i < n; i++) {
			st =  new StringTokenizer(br.readLine());
			int dirX = Integer.parseInt(st.nextToken());
			int dirY = Integer.parseInt(st.nextToken());

			dx[i] = dirX;;
			dy[i] = dirY;
		}

		System.out.print(bfs());
	}

	static int bfs(){
		boolean[][] visited = new boolean[r][c];
		Queue<Node> q = new LinkedList<>();

		for(Node node : list){
			visited[node.x][node.y] = true;
			q.add(new Node(node.x,node.y,0));
		}

		while (!q.isEmpty()){
			Node cur = q.poll();
			int curX = cur.x;
			int curY = cur.y;
			int curDist = cur.dist;

			if(curX == r - 1){
				return curDist;
			}

			for(int dir=0; dir < n; dir++){
				int nX = curX + dx[dir];
				int nY = curY + dy[dir];

				if(OOB(nX,nY) || visited[nX][nY] || map[nX][nY] == 0) continue;

				visited[nX][nY] = true;
				q.add(new Node(nX,nY,curDist + 1));
			}
		}

		return -1;
	}

	static boolean OOB(int x,int y){
		return x < 0 || y < 0 || x >= r || y >= c;
	}

	static class Node{
		int x;
		int y;
		int dist;

		public Node(int x, int y, int dist) {
			this.x = x;
			this.y = y;
			this.dist = dist;
		}

		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}