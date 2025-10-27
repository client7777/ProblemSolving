import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		int curYear = 2024;
		int curMonth = 8;

		Map<Integer, String> map = new HashMap<>();
		map.put(1, curYear + " " + curMonth);

		for(int i = 2; i <= n; i++) {
			curMonth += 7;
			if(curMonth > 12){
				curYear ++;
				curMonth -= 12;
			}

			map.put(i, curYear + " " + curMonth);
		}

		System.out.print(map.get(n));
	}
}