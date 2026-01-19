import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int testCase = Integer.parseInt(br.readLine());
		while (testCase-- > 0) {
			int n = Integer.parseInt(br.readLine());

			ArrayList<String> before = new ArrayList<>();
			ArrayList<String> after = new ArrayList<>();

			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i = 0; i < n; i++) {
				before.add(st.nextToken());
			}

			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < n; i++) {
				after.add(st.nextToken());
			}

			Collections.sort(before);
			Collections.sort(after);

			boolean flag = false;

			for(int i = 0; i < n; i++) {
				if(!before.get(i).equals(after.get(i))) {
					flag = true;
					break;
				}
			}

			sb.append(flag ? "CHEATER" : "NOT CHEATER").append("\n");
		}

		System.out.print(sb);
	}
}