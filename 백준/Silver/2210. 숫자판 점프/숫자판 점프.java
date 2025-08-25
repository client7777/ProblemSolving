import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	static Set<String> set = new HashSet<>();
	static String[][] map = new String[5][5];
	static boolean[][] visit = new boolean[5][5];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		for(int i = 0; i < 5; i++){
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 5; j++){
				map[i][j] = st.nextToken();
			}
		}

		for(int i = 0; i < 5; i++){
			for(int j = 0; j < 5; j++){
				backTrack(i,j,0,map[i][j]);
			}
		}

		System.out.print(set.size());
	}

	static void backTrack(int x,int y,int depth, String str){
		if(depth == 5){
			set.add(str);
			return;
		}

		for(int dir = 0; dir < 4; dir++){
			int nX = x + dx[dir];
			int nY = y + dy[dir];

			if(OOB(nX,nY)){
				continue;
			}

			backTrack(nX,nY,depth+1,str + map[nX][nY]);
		}
	}

	static boolean OOB(int x,int y){
		return x < 0 || y < 0 || x >= 5 || y >= 5;
	}
}
