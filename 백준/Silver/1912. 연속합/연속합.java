import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		int[] arr = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] d = new int[n]; // d[i] = i번째 숫자까지 최대 합
		 
		int answer = arr[0];
		d[0] = arr[0];

		for(int i = 1; i < n; i++) {
			d[i] = Math.max(arr[i], d[i-1] + arr[i]);
			answer = Math.max(answer, d[i]);
		}

		System.out.print(answer);
	}
}