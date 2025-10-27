import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());

		PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o));

		st = new StringTokenizer(br.readLine());
		while (n-- > 0) {
			pq.add(Integer.parseInt(st.nextToken()));
		}

		for(int i = 0; i < k-1; i++){
			pq.poll();
		}

		System.out.print(pq.poll());
	}
}