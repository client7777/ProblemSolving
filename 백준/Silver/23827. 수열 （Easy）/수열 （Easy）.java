import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static final int MOD = 1_000_000_007;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		long[] arr = new long[n];
		long[] suffix = new long[n]; 

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			arr[i] = Long.parseLong(st.nextToken());
		}
		
		suffix[n - 1] = arr[n - 1];
		for (int i = n - 2; i >= 0; i--) {
			suffix[i] = (suffix[i + 1] + arr[i]) % MOD;
		}

		long sum = 0;
		for (int i = 0; i < n - 1; i++) {
			long rightSum = suffix[i + 1];
			sum = (sum + arr[i] * rightSum % MOD) % MOD;
		}
		
		System.out.println(sum);
	}
}
