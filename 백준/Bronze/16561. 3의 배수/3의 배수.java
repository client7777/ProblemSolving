import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int answer = 0;

		for(int i = 3; i <= n; i += 3) {
			for(int j = 3; j <= n; j += 3) {
				if(n - (i + j) > 0){
					answer++;
				}
			}
		}

		System.out.print(answer);
	}
}