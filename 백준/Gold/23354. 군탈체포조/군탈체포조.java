import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int n;
	static int[][] map;
	static Position startPos;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	static ArrayList<Position> keyPoints = new ArrayList<>();
	static int[][] dist;
	static boolean[] visited;
	static int minCost = Integer.MAX_VALUE;
	static int deserterCount;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());

		map = new int[n][n];

		StringTokenizer st;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == -1) {
					startPos = new Position(i, j);
				} 
				else if (map[i][j] == 0) {
					keyPoints.add(new Position(i, j));
				}
			}
		}
		
		keyPoints.add(0, startPos);
		deserterCount = keyPoints.size() - 1;
		
		dist = new int[keyPoints.size()][keyPoints.size()];
		
		for (int i = 0; i < keyPoints.size(); i++) {
			int[][] costMap = dijkstra(keyPoints.get(i).x, keyPoints.get(i).y);
			for (int j = 0; j < keyPoints.size(); j++) {
				dist[i][j] = costMap[keyPoints.get(j).x][keyPoints.get(j).y];
			}
		}

		visited = new boolean[deserterCount + 1];
		backTrack(0, 0, 0);

		System.out.println(minCost);
	}

	static void backTrack(int depth, int lastIndex, int currentCost) {
		if (depth == deserterCount) {
			minCost = Math.min(minCost, currentCost + dist[lastIndex][0]);
			return;
		}

		for (int i = 1; i <= deserterCount; i++) {
			if (!visited[i]) {
				visited[i] = true;
				backTrack(depth + 1, i, currentCost + dist[lastIndex][i]);
				visited[i] = false;
			}
		}
	}

	static int[][] dijkstra(int sx, int sy) {
		int[][] cost = new int[n][n];
		for (int i = 0; i < n; i++) {
			Arrays.fill(cost[i], Integer.MAX_VALUE);
		}
		cost[sx][sy] = 0;

		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(sx, sy, 0));

		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			int curX = cur.x;
			int curY = cur.y;
			int curCost = cur.cost;

			if (curCost > cost[curX][curY]) continue;

			for (int dir = 0; dir < 4; dir++) {
				int nX = curX + dx[dir];
				int nY = curY + dy[dir];

				if (OOB(nX, nY)) continue;
				
				int moveCost = map[nX][nY];
				if (moveCost == -1 || moveCost == 0) {
					moveCost = 0;
				}

				int nCost = curCost + moveCost;
				if (cost[nX][nY] > nCost) {
					cost[nX][nY] = nCost;
					pq.add(new Node(nX, nY, nCost));
				}
			}
		}
		return cost;
	}

	static boolean OOB(int x, int y) {
		return x < 0 || y < 0 || x >= n || y >= n;
	}

	static class Node implements Comparable<Node> {
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

	static class Position {
		int x;
		int y;

		public Position(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}