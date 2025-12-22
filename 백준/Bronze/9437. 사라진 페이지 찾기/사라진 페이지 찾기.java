import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		while (true){
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());

			if(n == 0){
				break;
			}

			int p = Integer.parseInt(st.nextToken());
			
			int pair;

			if(p % 2 == 0){
				pair = p-1;
			}
			else{
				pair = p+1;
			}

			List<Integer> answer = new ArrayList<>();
			answer.add(pair);
			answer.add(n - pair + 1);
			answer.add(n - p + 1);

			Collections.sort(answer);

			for(int i : answer){
				sb.append(i).append(" ");
			}
			sb.append('\n');
		}

		System.out.print(sb);
	}
}