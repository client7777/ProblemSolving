import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static final int MAX = 1000 * 50;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		ArrayList<Integer> num = new ArrayList<>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++){
			num.add(Integer.parseInt(st.nextToken()));
		}

		int k = Integer.parseInt(br.readLine());

		int[] d = new int[MAX + 1]; // d[i] = i를 만들 때 필요한 최소 숫자 개수
		Arrays.fill(d, MAX);
		d[0] = 0;

		for(int i : num){
			for(int j = i; j <= MAX; j++){
				d[j] = Math.min(d[j], d[j - i] + 1);
			}
		}

		StringBuilder sb = new StringBuilder();
		for(int i = 1; i <= MAX; i++){
			if(d[i] > k){

				sb.append(i % 2 == 0 ? "holsoon win at " : "jjaksoon win at ").append(i);
				break;
			}
		}

		System.out.print(sb);
	}
}