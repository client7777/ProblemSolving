import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int test_case = Integer.parseInt(br.readLine());

		while (test_case-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			float num = Float.parseFloat(st.nextToken());

			while (st.hasMoreTokens()) {
				String operator = st.nextToken();

				switch (operator) {
					case "@":
						num *= 3.00F;
						break;
					case "%":
						num += 5.00F;
						break;
					case "#":
						num -= 7.00F;
						break;
				}
			}
			System.out.printf("%.2f\n", num);
		}
	}
}