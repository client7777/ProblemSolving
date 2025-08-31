import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int cntRecur = 0;
	static int cntDp = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		int[][] map = new int[16][16];

		StringTokenizer st;

		for(int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= n; j++){
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		recur(map, n,n);
		dp(map, n);
		System.out.println(cntRecur + " " + cntDp);
	}

	static int recur(int[][] arr, int i, int j) {

		if(i == 0 || j == 0) {
			cntRecur++;
			return 0;
		}
		else{
			return arr[i][j] + Math.max(recur(arr, i - 1, j), recur(arr, i, j - 1));
		}
	}

	static void dp(int[][] arr, int n){

		int[][] d = new int[16][16];

		for(int i = 0 ; i <= n; i++){
			d[i][0] = 0;
		}

		for(int j = 0; j <= n; j++){
			d[0][j] = 0;
		}

		for(int i = 1; i <= n; i++){
			for(int j = 1; j <= n; j++){
				d[i][j] = arr[i][j] + Math.max(d[i-1][j], d[i][j-1]);
				cntDp++;
			}
		}
	}
}