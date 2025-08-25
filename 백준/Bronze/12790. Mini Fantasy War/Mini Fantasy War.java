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
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int f = Integer.parseInt(st.nextToken());
			int g = Integer.parseInt(st.nextToken());
			int h = Integer.parseInt(st.nextToken());

			int hp = a + e;
			int mp = b + f;
			int damage = c + g;
			int shield = d + h;

			if(a + e < 1){
				hp = 1;
			}

			if(b + f < 1){
				mp = 1;
			}

			if(c + g < 0){
				damage = 0;
			}

			System.out.println(hp + 5 * mp + 2 * damage + 2 * shield);
		}
	}
}