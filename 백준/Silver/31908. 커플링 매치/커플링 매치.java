import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		HashMap<String, Integer> map = new HashMap<>();
		HashMap<String, List<String>> map2 = new HashMap<>();
		while (n-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String name = st.nextToken();
			String type = st.nextToken();
	
			if(type.equals("-")){
				continue;
			}
			
			map.put(type, map.getOrDefault(type, 0) + 1);

			List<String> list = map2.getOrDefault(type, new ArrayList<>());

			list.add(name);
			map2.put(type, list);
		}

		int cnt = 0;

		StringBuilder sb = new StringBuilder();
		for(String s : map.keySet()) {
			if(map.get(s) == 2){
				cnt++;

				List<String> list = map2.get(s);

				for(String str : list) {
					sb.append(str).append(" ");
				}

				sb.append('\n');
			}
		}

		System.out.println(cnt);
		System.out.print(sb);
	}
}