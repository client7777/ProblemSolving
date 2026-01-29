import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		while (n-- > 0) {
			String str = br.readLine();
			int length = str.length();

			if(length >= 6 && length <= 9){
				sb.append("yes").append("\n");
			}
			else{
				sb.append("no").append("\n");
			}
		}

		System.out.print(sb);
	}
}