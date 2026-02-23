import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		ArrayList<Info> list = new ArrayList<>();
		StringTokenizer st;
		for(int i = 0; i < 11; i++){
			st = new StringTokenizer(br.readLine());
			int d = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());

			list.add(new Info(d, v));
		}

		Collections.sort(list);

		int time = 0;
		int answer = 0;

		for(Info cur : list){
			time += cur.d;
			answer += (time + cur.v * 20);
		}

		System.out.println(answer);
	}

	static class Info implements Comparable<Info>{
		int d;
		int v;

		public Info(int d, int v){
			this.d = d;
			this.v = v;
		}

		@Override
		public int compareTo(Info o) {
			return Integer.compare(d, o.d);
		}
	}
}