import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		int[] a = new int[n];
		int[] b = new int[n];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			a[i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			b[i] = Integer.parseInt(st.nextToken());
		}

		int cnt = 0;
		for(int i = 0; i < n; i++){
			if(a[i] >= b[i]){
				continue;
			}
			cnt += (b[i] - a[i]);
		}

		System.out.println(cnt);
	}
}