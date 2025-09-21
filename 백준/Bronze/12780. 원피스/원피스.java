import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String h = br.readLine();
		String n = br.readLine();

		String replaced = h.replace(n, "a");

		int answer = 0;

		for(int i = 0; i < replaced.length(); i++) {
			if(replaced.charAt(i) == 'a') {
				answer++;
			}
		}

		System.out.println(answer);
	}
}