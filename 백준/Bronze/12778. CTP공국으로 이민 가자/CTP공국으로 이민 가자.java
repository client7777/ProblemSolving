import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int test_case = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		while (test_case-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int m = Integer.parseInt(st.nextToken());
			String type = st.nextToken();

			if(type.equals("C")){
				st = new StringTokenizer(br.readLine());
				while (st.hasMoreTokens()){
					sb.append(st.nextToken().charAt(0) - 'A' + 1).append(" ");
				}
				sb.append('\n');
			}
			else if(type.equals("N")){
				st = new StringTokenizer(br.readLine());
				while (st.hasMoreTokens()){
					sb.append((char)(Integer.parseInt(st.nextToken()) + 64)).append(" ");
				}
				sb.append('\n');
			}
		}
		System.out.print(sb);
	}
}