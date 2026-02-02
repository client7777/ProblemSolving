import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int n;
	static char[][] arr;
	static int maxLength = Integer.MIN_VALUE;
	static int[] dx = {0, 1};
	static int[] dy = {1, 0};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());

		arr = new char[n][n];
		for(int i = 0; i < n; i++) {
			arr[i] = br.readLine().toCharArray();
		}

		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				swapCandy(i, j);
			}
		}
		
		System.out.print(maxLength);
	}
	
	static void swapCandy(int x,int y){
		for(int dir = 0; dir < 2; dir++) {
			int nX = x + dx[dir];
			int nY = y + dy[dir];
			
			if(OOB(nX,nY) || arr[nX][nY] == arr[x][y]){
				continue;
			}
			
			// 인접한 사탕의 색이 다르면 위치 바꿈
			char tmp = arr[nX][nY];
			arr[nX][nY] = arr[x][y];
			arr[x][y] = tmp;
			calMaxLength();
			
			// 원위치
			arr[x][y] = arr[nX][nY];
			arr[nX][nY] = tmp;
		}
	}
	
	static void calMaxLength() {
		// 가장 긴 연속 부분의 길이 계산

		int rowMax = 1;
		int colMax = 1;

		// 가장 긴 행 조사
		for(int i = 0; i < n; i++) {
			int length = 1;
			for(int j = 0; j < n-1; j++) {
				if(arr[i][j] == arr[i][j + 1]){
					length++;
				}
				else{
					length = 1;
				}
				rowMax = Math.max(rowMax, length);
			}
		}
		
		// 가장 긴 열 조사
		for(int i = 0; i < n; i++) {
			int length = 1;
			for(int j = 0; j < n-1; j++) {
				if(arr[j][i] == arr[j + 1][i]){
					length++;
				}
				else{
					length = 1;
				}
				colMax = Math.max(colMax, length);
			}
		}

		maxLength = Math.max(maxLength, Math.max(rowMax, colMax));
	}
	
	static boolean OOB(int x, int y){
		return x < 0 || y < 0 || x >= n || y >= n;
	}
}