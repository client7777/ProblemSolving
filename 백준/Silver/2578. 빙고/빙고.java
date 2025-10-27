import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int cnt = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int[][] map = new int[5][5];
		for (int i = 0; i < 5; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 5; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for(int i = 0; i < 5; i++){
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 5; j++){
				
				int num = Integer.parseInt(st.nextToken());
				
				for(int x = 0; x < 5; x++){
					for(int y = 0; y < 5; y++){
						if(map[x][y] == num){
							map[x][y] = 0;
						}
					}
				}

				cnt = 0;

				// 빙고 체크
				// 왼쪽, 오른쪽, 좌대각, 우대각
				rightCheck(map);
				leftCheck(map);
				leftCrossCheck(map);
				rightCrossCheck(map);
				if(cnt >= 3){
					System.out.print(i * 5 + j + 1);
					return;
				}
			}
		}
	}

	static void rightCheck(int[][] map){
		for(int i = 0; i < 5; i++){
			int zeroCnt = 0;
			for(int j = 0; j < 5; j++){
				if(map[i][j] == 0){
					zeroCnt++;
				}

				if(zeroCnt == 5){
					cnt++;
				}
			}
		}
	}

	static void leftCheck(int[][] map){
		for(int i = 0; i < 5; i++){
			int zeroCnt = 0;
			for(int j = 0; j < 5; j++){
				if(map[j][i] == 0){
					zeroCnt++;
				}
			}

			if(zeroCnt == 5){
				cnt++;
			}
		}
	}

	static void leftCrossCheck(int[][] map){
		int zeroCnt = 0;
		for(int i = 0; i < 5; i++){
			if(map[i][i] == 0){
				zeroCnt++;
			}

			if(zeroCnt == 5){
				cnt++;
			}
		}
	}

	static void rightCrossCheck(int[][] map){
		int zeroCnt = 0;
		for(int i = 0; i < 5; i++){
			if(map[i][4 - i] == 0){
				zeroCnt++;
			}
			if(zeroCnt == 5){
				cnt++;
			}
		}
	}
}