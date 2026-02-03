import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int answer = Integer.MAX_VALUE;
	static String[] map;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		map = new String[n];
		for (int i = 0; i < n; i++) {
			map[i] = br.readLine();
		}

		for(int i = 0; i <= n-8; i++){
			for(int j = 0; j <= m-8; j++){
				answer = Math.min(answer, paintMap(i, j));
			}
		}

		System.out.print(answer);
	}

	static int paintMap(int x, int y){
		String[] origin = {"WBWBWBWB", "BWBWBWBW"};
		int white = 0;
		for(int i = 0; i < 8; i++){
			int row = x + i;
			for(int j =0; j < 8; j++){
				int cal = y + j;

				if(map[row].charAt(cal) != origin[i % 2].charAt(j)){
					white++;
				}
			}
		}

		return Math.min(white, 64 - white);
	}
}