import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int cntA;
	static int cntB;
	static int cntC;
	static boolean[][][][][] visit;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();

		for(int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);

			if(c == 'A'){
				cntA++;
			}
			else if(c == 'B'){
				cntB++;
			}
			else {
				cntC++;
			}

		}

		visit = new boolean[cntA + 1][cntB + 1][cntC + 1][3][3];
		dfs(cntA, cntB, cntC, "", 0, 0);
		System.out.print(-1);
	}

	static void dfs(int a, int b, int c, String comb, int prevPrev, int prev){

		if(a + b + c == 0){
			System.out.print(comb);
			System.exit(0);
		}

		if(visit[a][b][c][prevPrev][prev]){
			return;
		}

		visit[a][b][c][prevPrev][prev] = true;

		if(a > 0)
			dfs(a - 1, b, c, comb + 'A', prev, 0);

		if(b > 0 && prev != 1)
			dfs(a, b - 1, c, comb + 'B', prev, 1);
		
		if(c > 0 && prevPrev != 2 && prev != 2)
			dfs(a, b, c - 1, comb + 'C', prev, 2);
	}
}