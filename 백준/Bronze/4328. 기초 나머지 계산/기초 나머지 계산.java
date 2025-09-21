import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while (true) {


			StringTokenizer st = new StringTokenizer(br.readLine());
			int b = Integer.parseInt(st.nextToken());
			if (b == 0) break;

			String p = st.nextToken();
			String m = st.nextToken();

			BigInteger bigP = new BigInteger(p, b);
			BigInteger bigM = new BigInteger(m, b);

			BigInteger mod = bigP.mod(bigM);

			System.out.println(mod.toString(b));
		}
	}
}