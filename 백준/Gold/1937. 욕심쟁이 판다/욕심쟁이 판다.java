import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int n;
	static int[][] map;
	static int[][] d;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	static int answer = Integer.MIN_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());

		d = new int[n][n];
		map = new int[n][n];

		for(int i = 0; i < n; i++){
			String[] row = br.readLine().split(" ");
			for(int j = 0; j < n; j++){
				map[i][j] = Integer.parseInt(row[j]);
			}
		}

		for(int i = 0; i < n; i++){
			for(int j = 0; j < n; j++){
				answer = Math.max(dfs(i,j), answer);
			}
		}

		System.out.print(answer);
	}

	static int dfs(int x,int y){

		if(d[x][y] != 0){
			return d[x][y];
		}

		d[x][y] = 1;

		for(int dir=0; dir<4; dir++){
			int nX = x + dx[dir];
			int nY = y + dy[dir];

			if(OOB(nX,nY) || map[x][y] >= map[nX][nY]){
				continue;
			}

			d[x][y] = Math.max(d[x][y], dfs(nX,nY) + 1);
		}

		return d[x][y];
	}

	static boolean OOB(int x,int y){
		return x < 0 || y < 0 || x >= n || y >= n;
	}
}


