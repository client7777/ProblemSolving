import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int n;
	static int max = Integer.MIN_VALUE;
	static int min = Integer.MAX_VALUE;
	static int[] dx = {0,1};
	static int[] dy = {1,0};
	static char[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());

		StringTokenizer st;
		map = new char[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = st.nextToken().charAt(0);
			}
		}

		backTrack(0,0,map[0][0] - '0',' ');
		System.out.print(max + " " + min);
	}

	static void backTrack(int x,int y,int value,char operator){
		if(x == n-1 && y == n-1){
			max = Math.max(max,value);
			min = Math.min(min,value);
			return;
		}

		for(int dir=0; dir<2; dir++){
			int nX = x + dx[dir];
			int nY = y + dy[dir];

			if(OOB(nX,nY)){
				continue;
			}

			if(map[nX][nY] >= '0' && map[nX][nY] <= '5'){
				// 다음 좌표의 값이 숫자인 경우
				int nextValue = map[nX][nY] - '0';
				if(operator == '+'){
					backTrack(nX,nY,value + nextValue, ' ');
				}
				else if(operator == '-'){
					backTrack(nX,nY,value - nextValue, ' ');
				}
				else if(operator == '*'){
					backTrack(nX,nY,value * nextValue, ' ');
				}
			}
			else {
				// 다음 좌표의 값이 연산자라면 계산 없이 연산자를 넘겨줌
				backTrack(nX,nY,value, map[nX][nY]);
			}
		}
	}

	static boolean OOB(int x,int y){
		return x >= n || y >= n;
	}
}
