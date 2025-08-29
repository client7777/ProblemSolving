import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int n;
	static int m;
	static int move;
	static int[][] grid;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCases = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();

		while (testCases-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			 n = Integer.parseInt(st.nextToken());
			 m = Integer.parseInt(st.nextToken());

			grid = new int[n][m];
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < m; j++) {
					grid[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			move = 0;

			while (true){
				if(!checkMove()){
					break;
				}
			}

			sb.append(move).append("\n");
		}

		System.out.print(sb.toString());
	}

	static boolean checkMove(){

		boolean flag = false;

		for(int i = 0; i < grid.length; i++){
			for(int j = 0; j < grid[0].length; j++){
				if(grid[i][j] == 1){
					if(i + 1 < n && grid[i+1][j] == 0){
						grid[i][j] = 0;
						grid[i+1][j] = 1;
						flag = true;
						move++;
					}
				}
			}
		}

		return flag;
	}
}