import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int h = Integer.parseInt(br.readLine());
		int w = Integer.parseInt(br.readLine());

		double radius = Math.min(h, w) / 2.0;
		System.out.print((int)(radius * 100));
	}
}