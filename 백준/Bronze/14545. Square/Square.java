import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int test_case = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		while (test_case-- > 0) {
			int number = Integer.parseInt(br.readLine());

			int value = 0;
			for(int i = 1; i <= number; i++) {
				value += i * i;
			}

			sb.append(value).append('\n');
		}

		System.out.println(sb);
	}
}