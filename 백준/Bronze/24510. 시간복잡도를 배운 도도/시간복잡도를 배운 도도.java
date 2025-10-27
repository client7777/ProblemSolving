import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int line = Integer.parseInt(br.readLine());
		int answer = 0;

		while (line-- > 0) {
			String str = br.readLine();

			int cnt = 0;

			str = str.replace("for", "A");
			str = str.replace("while", "B");

			char[] chars = str.toCharArray();

			for(char c : chars) {
				if(c == 'A' || c == 'B') {
					cnt++;
				}
			}

			answer = Math.max(answer, cnt);
		}

		System.out.print(answer);
	}
}