import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
	static int h;
	static int w;
	static int[][] map;
	static char[][] arr;
	static int[][] sinkMemo;
	// 북 -> 서 -> 동 -> 남
	static int[] dx = {-1, 0, 0, 1};
	static int[] dy = {0, -1, 1, 0};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();

		for (int t = 1; t <= testCase; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			h = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());

			map = new int[h][w];
			sinkMemo = new int[h][w];
			arr = new char[h][w];

			for (int i = 0; i < h; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < w; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					sinkMemo[i][j] = -1;
				}
			}

			HashMap<Integer, Character> labelMap = new HashMap<>();
			char label = 'a';

			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					int sink = findSink(i, j);
					if (!labelMap.containsKey(sink)) {
						labelMap.put(sink, label++);
					}
					arr[i][j] = labelMap.get(sink);
				}
			}

			sb.append("Case #").append(t).append(":\n");
			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					sb.append(arr[i][j]);
					if (j != w - 1) sb.append(" ");
				}
				sb.append("\n");
			}
		}
		System.out.print(sb);
	}

	// (x,y)에서 출발해서 최종적으로 도달하는 sink 좌표를 반환
	static int findSink(int x, int y) {
		if (sinkMemo[x][y] != -1) return sinkMemo[x][y];

		int minAlt = map[x][y];
		int sinkX = x, sinkY = y;

		for (int d = 0; d < 4; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];
			if (OOB(nx, ny)) continue;
			if (map[nx][ny] < minAlt) {
				minAlt = map[nx][ny];
				sinkX = nx;
				sinkY = ny;
			}
		}

		if (sinkX == x && sinkY == y) {
			return sinkMemo[x][y] = x * w + y;
		} 
		else {
			return sinkMemo[x][y] = findSink(sinkX, sinkY);
		}
	}

	static boolean OOB(int x, int y) {
		return x < 0 || y < 0 || x >= h || y >= w;
	}
}