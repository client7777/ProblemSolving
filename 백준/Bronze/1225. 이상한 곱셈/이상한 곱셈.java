import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		char[] arr1 = st.nextToken().toCharArray();
		char[] arr2 = st.nextToken().toCharArray();

		long sum = 0;
		for(char c : arr1){
			for(char c2 : arr2){
				sum += (long)(c - '0') * (c2 - '0');
			}
		}

		System.out.print(sum);
	}
}