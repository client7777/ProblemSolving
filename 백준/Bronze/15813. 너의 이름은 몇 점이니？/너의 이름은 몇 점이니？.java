import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		char[] chars = br.readLine().toCharArray();

		int sum = 0;
		for(int i = 0; i < n; i++) {
			int val = chars[i] - 'A' + 1;
			sum += val;
		}

		System.out.println(sum);
    }
}