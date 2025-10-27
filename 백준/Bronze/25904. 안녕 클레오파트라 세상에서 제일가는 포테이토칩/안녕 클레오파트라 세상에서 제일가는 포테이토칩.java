import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int x = Integer.parseInt(st.nextToken());

		int[] arr = new int[n+1];
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		boolean flag = false;

		while (true){
			for(int i = 1; i <= n; i++){
				if(arr[i] < x){
					System.out.print(i);
					flag = true;
					break;
				}
				else {
					x++;
				}
			}

			if(flag){
				break;
			}
		}
	}
}