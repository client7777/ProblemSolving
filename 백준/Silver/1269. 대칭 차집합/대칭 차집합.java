import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());

		HashSet<Integer> aSet = new HashSet<>();
		HashSet<Integer> bSet = new HashSet<>();

		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < a; i++) {
			aSet.add(Integer.parseInt(st.nextToken()));
		}

		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < b; i++) {
			bSet.add(Integer.parseInt(st.nextToken()));
		}

		int cntA = 0;
		int cntB = 0;

		for(int i : aSet) {
			if(bSet.contains(i)) {
				cntA++;
			}
		}

		for(int i : bSet) {
			if(aSet.contains(i)) {
				cntB++;
			}
		}

		System.out.print(aSet.size() - cntA + bSet.size() - cntB);
	}
}