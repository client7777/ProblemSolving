import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int k = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());

		if(k <= 2){
			System.out.print(n-1);
			return;
		}
		
		int answer = 0;

		for(int i = 1; i <= n; i++){
			if(gcd(i, k) <= 2){
				answer++;
			}
		}

		System.out.print(answer);
	}

	static int gcd(int x,int y){

		while (y != 0){
			int tmp = y;
			y = x % y;
			x = tmp;
		}

		return x;
	}
}