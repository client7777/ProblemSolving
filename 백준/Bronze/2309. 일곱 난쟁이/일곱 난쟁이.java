import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int[] arr = new int[9];
		int sum = 0;

		for(int i = 0; i < 9; i++){
			arr[i] = Integer.parseInt(br.readLine());
			sum += arr[i];
		}
		Arrays.sort(arr);
		
		int a = -1;
		int b = -1;

		for(int i = 0; i < 8; i++){
			for(int j = i + 1; j < 9; j++){
				if(sum - (arr[i] + arr[j]) == 100) {
					a = i;
					b = j;
					break;
				}
			}
		}

		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < 9; i++){
			if(i != a && b != i){
				sb.append(arr[i]).append('\n');
			}
		}

		System.out.print(sb);
	}
}