import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringBuilder sb = new StringBuilder();
		int index = 1;
		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int l = Integer.parseInt(st.nextToken());
			int p = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			
			if(l == 0 && p == 0 && v == 0){
				break;
			}

			sb.append("Case " + index + ": ");
			int ans = 0;


			while (v - p >= 0){
				ans += l;
				v -= p;
			}
			ans += Math.min(l,v);

			sb.append(ans).append("\n");
			index++;
		}

		System.out.println(sb);
	}
}