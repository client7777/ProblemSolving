import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		while (true) {

			String value = br.readLine();
			if(value.equals("0")){
				break;
			}

			sb.append(value).append(" ");

			while (true){

				String[] arr = value.split("");

				if(arr.length == 1){
					break;
				}

				int tmp = 1;

				for(String s : arr){
					tmp *= Integer.parseInt(s);
				}

				sb.append(tmp).append(" ");
				value = String.valueOf(tmp);

			}
			sb.append('\n');
		}

		System.out.println(sb);
	}
}