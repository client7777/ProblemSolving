import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int n;
	static int m;
	static int[][] map;
	static int startX;
	static int startY;
	//상 -> 우 -> 하 -> 좌
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		map = new int[n][m];

		st = new StringTokenizer(br.readLine());
		int leftCount = Integer.parseInt(st.nextToken());
		int rightCount = Integer.parseInt(st.nextToken());

		for(int i = 0; i < n; i++){
			String str = br.readLine();
			for(int j = 0; j < m; j++){
				map[i][j] = str.charAt(j) - '0';
				if(map[i][j] == 2){
					startX = i;
					startY = j;
					map[i][j] = 0;
				}
			}
		}

		System.out.print(bfs(leftCount, rightCount));
	}

	static int bfs(int leftCount, int rightCount){
		int cnt = 1; // 시작 위치를 개수에 포함하므로 1부터 시작

		boolean[][] visit = new boolean[n][m];
		visit[startX][startY] = true;

		Queue<Node> q = new LinkedList<>();
		q.add(new Node(startX, startY, leftCount, rightCount));

		while(!q.isEmpty()){
			Node cur = q.poll();
			int curX = cur.x;
			int curY = cur.y;
			int curL = cur.l;
			int curR = cur.r;

			for(int dir = 0; dir < 4; dir++){
				int nX = curX + dx[dir];
				int nY = curY + dy[dir];

				if(dir == 1){
					if(OOB(nX,nY) || visit[nX][nY] || map[nX][nY] == 1){
						continue;
					}
					//오른쪽으로 이동할 수 있는 횟수가 남아있고 오른쪽이 벽이 아니라면
					if(curR > 0){
						visit[nX][nY] = true;
						q.add(new Node(nX,nY,curL, curR-1));
						cnt++;
					}
				}
				else if(dir == 3){
					if(OOB(nX,nY) || visit[nX][nY] || map[nX][nY] == 1){
						continue;
					}
					//왼쪽으로 이동할 수 있느 횟수가 남아있고 왼쪽이 벽이 아니라면
					if(curL > 0){
						visit[nX][nY] = true;
						q.add(new Node(nX,nY,curL -1, curR));
						cnt++;
					}
				}
				else {
					int nX2 = nX;
					int nY2 = nY;
					while(true){
						if(OOB(nX2,nY2) || visit[nX2][nY2] || map[nX2][nY2] == 1){
							break;
						}
						visit[nX2][nY2] = true;
						q.add(new Node(nX2, nY2, curL, curR));
						cnt++;

						nX2 += dx[dir];
						nY2 += dy[dir];
					}
				}
			}
		}

		return cnt;
	}

	static boolean OOB(int x,int y){
		return x < 0 || y < 0 || x >= n || y >= m;
	}

	static class Node{
		int x;
		int y;
		int l;
		int r;

		public Node(int x, int y, int l, int r) {
			this.x = x;
			this.y = y;
			this.l = l;
			this.r = r;
		}
	}
}