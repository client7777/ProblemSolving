import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());

		while (n-- > 0){
			st = new StringTokenizer(br.readLine());
			String major = st.nextToken();
			int year = Integer.parseInt(st.nextToken());

			if(year == 2026){
				System.out.print(major);
				break;
			}
		}
	}
}