import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		HashMap<String, Integer> map = new HashMap<>();
		for(int i = 0; i < n; i++){
			st = new StringTokenizer(br.readLine());
			String str = st.nextToken();

			if(str.length() < m){
				continue;
			}

			map.put(str, map.getOrDefault(str, 0) + 1);
		}

		List<String> list = new ArrayList<>(map.keySet());

		list.sort((o1, o2) -> {
			if(Integer.compare(map.get(o1), map.get(o2)) != 0) {
				return Integer.compare(map.get(o2), map.get(o1));
			}

			if(o1.length() != o2.length()) {
				return Integer.compare(o2.length(), o1.length());
			}

			return o1.compareTo(o2);
		});

		StringBuilder sb = new StringBuilder();
		for(String str : list){
			sb.append(str).append("\n");
		}

		System.out.print(sb);
	}
}