import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int test_cases = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();

		while (test_cases-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());

			//전체 다리의 수 = 2 * m
			int a = 2 * m - n; // 다리가 질린 닭의 수
			int b = (n - a) / 2; // 다리가 잘리지 않은 닭의 수

			sb.append(a).append(" ").append(b).append("\n");
		}

		System.out.println(sb);
	}
}