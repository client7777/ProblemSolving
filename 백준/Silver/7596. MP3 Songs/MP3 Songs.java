import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringBuilder sb = new StringBuilder();
		int test_case = 1;
		while (true) {
			int n = Integer.parseInt(br.readLine());
			if(n == 0){
				break;
			}

			ArrayList<String> list = new ArrayList<>();

			for(int i = 0; i < n; i++){
				String str = br.readLine();
				list.add(str);
			}

			Collections.sort(list);

			sb.append(test_case).append("\n");

			for(String str : list){
				sb.append(str).append("\n");
			}

			test_case++;
		}

		System.out.println(sb);
	}
}