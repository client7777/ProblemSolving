import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		ArrayList<String> list = new ArrayList<>();
		for(int i = 0; i < n; i++) {
			list.add(br.readLine());
		}

		list.sort(Comparator.naturalOrder());

		HashMap<String, Integer> map = new HashMap<>();
		for(int i = 0; i < list.size(); i++) {
			map.put(list.get(i), map.getOrDefault(list.get(i), 0) + 1);
		}

		String answer = "";
		int max = 0;

		for(int i = 0; i < list.size(); i++) {
			if(map.get(list.get(i)) > max) {
				max = map.get(list.get(i));
				answer = list.get(i);
			}
		}

		System.out.println(answer);
	}
}