import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int h = Integer.parseInt(br.readLine());
		int w = Integer.parseInt(br.readLine());

		int result = (Math.min(h, w) * 100 / 2);
		System.out.println(result);
	}
}