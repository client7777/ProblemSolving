import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCases = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();

		for(int t = 0; t < testCases; t++) {

			sb.append("Case #" + (t + 1) + ": ");

			String str = br.readLine();

			String[] stringArr = str.split(" ");

			for(int i = stringArr.length - 1; i >= 0; i--) {
				sb.append(stringArr[i]).append(" ");
			}

			sb.append('\n');
		}

		System.out.println(sb);
	}
}