import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());

		int onionA = 1;
		int onionB = 1;
		for(int i = 0; i < n; i++){
			onionA += a;
			onionB += b;

			if(onionB > onionA){
				int tmp  = onionB;
				onionB = onionA;
				onionA = tmp;
			}
			else if(onionA == onionB){
				onionB--;
			}
		}

		System.out.print(onionA + " " + onionB);
	}
}