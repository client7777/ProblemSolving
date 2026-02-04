import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int n;
	static int s;
	static int[] arr;
	static int ans = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		s = Integer.parseInt(st.nextToken());
		arr = new int[n];

		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++){
			arr[i] = Integer.parseInt(st.nextToken());
		}

		dfs(0, 0);
		if(s == 0){
			// s가 0인 경우 부분수열의 크기가 0인 부분수열이 존재할 수도 있다.
			ans--;
		}
		
		System.out.print(ans);
	}

	static void dfs(int depth, int sum){
		if(depth == n){
			if(sum == s){
				ans++;
			}

			return;
		}

		dfs(depth + 1, sum); // 원소를 포함시키지 않음
		dfs(depth + 1, sum + arr[depth]); // 원소를 포함시킴
	}
}