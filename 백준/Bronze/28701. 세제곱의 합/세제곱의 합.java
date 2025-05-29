import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();

		int sum = 0;
		for(int i = 1; i <= n; i++) {
			sum += i;
		}

		sb.append(sum).append('\n');
		sb.append(sum * sum).append('\n');
		sb.append(sum * sum).append('\n');

		System.out.println(sb);
	}
}