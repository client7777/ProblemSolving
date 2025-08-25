import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int n;
	static int m;
	static char[][] map;
	static int[][] state;
	static int answer = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		state = new int[n][m];
		map = new char[n][m];

		for(int i = 0; i < n; i++){
			String row = br.readLine();
			for(int j = 0; j < m; j++){
				map[i][j] = row.charAt(j);
			}
		}

		for(int i = 0; i < n; i++){
			for(int j = 0; j < m; j++){
				if(state[i][j] == 0){
					dfs(i, j);
				}
			}
		}

		System.out.print(answer);
	}

	static void dfs(int x,int y){
		state[x][y] = 1;

		int nX = x;
		int nY = y;

		switch (map[x][y]) {
			case 'D':
				nX++;
				break;
				case 'U':
					nX--;
					break;
					case 'L':
						nY--;
						break;
						case 'R':
							nY++;
							break;
		}

		if(state[nX][nY] == 0){
			dfs(nX, nY);
		}
		else if(state[nX][nY] == 1){
			answer++;
		}
		
		state[x][y] = 2;
	}
}
