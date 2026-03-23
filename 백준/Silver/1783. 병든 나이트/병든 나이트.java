import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		int max = 0;

		if(n == 1){
			max = 1;
		}
		else if(n == 2){
			max = Math.min(4, (m + 1) / 2);
		}
		else if(n >= 3){
			if(m < 7){
				max = Math.min(4, m);
			}
			else{
				max = m - 2;
			}
		}

		System.out.print(max);
	}
}