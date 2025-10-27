import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int m = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		HashMap<Integer, Integer> map = new HashMap<>();
		while (m-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			int request = Integer.parseInt(st.nextToken());

			if(request == 1){
				int x = Integer.parseInt(st.nextToken());
				int w = Integer.parseInt(st.nextToken());

				map.put(w, x);
			}
			else if(request == 2){
				int w = Integer.parseInt(st.nextToken());
				sb.append(map.get(w)).append('\n');
			}
		}

		System.out.print(sb);
	}
}