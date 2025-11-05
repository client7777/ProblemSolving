import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCases = Integer.parseInt(br.readLine());

		while (testCases-- > 0) {
			int car = Integer.parseInt(br.readLine());

			int option = Integer.parseInt(br.readLine());

			for(int i = 0; i < option; i++){
				StringTokenizer st = new StringTokenizer(br.readLine());

				car += (Integer.parseInt(st.nextToken()) * Integer.parseInt(st.nextToken()));
			}

			System.out.println(car);
		}
	}
}