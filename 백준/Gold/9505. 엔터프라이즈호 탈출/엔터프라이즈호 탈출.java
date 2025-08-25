import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static final int INF = 987654321;
	static int k;
	static int w;
	static int h;
	static HashMap<String, Integer> map;
	static int[][] graph;
	static int startX;
	static int startY;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		StringBuilder sb = new StringBuilder();

		int test_case = Integer.parseInt(st.nextToken());

		while (test_case-- > 0) {
			st = new StringTokenizer(br.readLine());
			k = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());

			map = new HashMap<>();

			for(int i = 0; i < k; i++){
				st = new StringTokenizer(br.readLine());
				String class_name = st.nextToken();
				int value = Integer.parseInt(st.nextToken());

				map.put(class_name, value);
			}

			graph = new int[h][w];

			for(int i = 0; i < h; i++){
				String[] str = br.readLine().split("");

				for(int j = 0; j < w; j++){

					if(str[j].equals("E")){
						startX = i;
						startY = j;
						continue;
					}

					graph[i][j] = map.get(str[j]);
				}
			}

			sb.append(dijkstra()).append('\n');
		}

		System.out.println(sb);
	}

	static int dijkstra(){
		int[][] dist = new int[h][w];
		for(int[] row : dist){
			Arrays.fill(row, INF);
		}

		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(startX, startY, 0));

		while(!pq.isEmpty()){
			Node cur = pq.poll();
			int curX = cur.x;
			int curY = cur.y;
			int curDist = cur.dist;

			if(curX == 0 || curX == h-1 || curY == 0 || curY == w-1){
				return curDist;
			}

			for(int dir = 0; dir < 4; dir++){
				int nX = curX + dx[dir];
				int nY = curY + dy[dir];

				if(OOB(nX,nY)) continue;

				int nDist = curDist + graph[nX][nY];

				if(dist[nX][nY] > nDist){
					dist[nX][nY] = nDist;
					pq.add(new Node(nX, nY, dist[nX][nY]));
				}
			}
		}

		return -1;
	}

	static boolean OOB(int x, int y){
		return x < 0 || y < 0 || x >= h || y >= w;
	}

	static class Node implements Comparable<Node>{
		int x;
		int y;
		int dist;

		public Node(int x, int y, int dist) {
			this.x = x;
			this.y = y;
			this.dist = dist;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.dist, o.dist);
		}
	}
}