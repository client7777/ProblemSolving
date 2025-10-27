import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		while (testCase-- > 0) {
			sb.append("Distances: ");

			String[] str = br.readLine().split(" ");

			char[] ch1 = str[0].toCharArray();
			char[] ch2 = str[1].toCharArray();

			for(int i = 0; i < ch1.length; i++){
				int x = ch1[i] - '0';
				int y = ch2[i] - '0';

				if(y >= x){
					sb.append(y - x).append(" ");
				}
				else{
					sb.append((y + 26) - x).append(" ");
				}
			}
			sb.append("\n");
		}

		System.out.print(sb);
	}
}