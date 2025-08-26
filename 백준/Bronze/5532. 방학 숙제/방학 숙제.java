import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int l = Integer.parseInt(br.readLine()); // 방학은 총 l일
		int a = Integer.parseInt(br.readLine()); // 국어
		int b = Integer.parseInt(br.readLine()); // 수학
		int c = Integer.parseInt(br.readLine());
		int d = Integer.parseInt(br.readLine());

		int korea = a / c;
		if(a % c != 0){
			korea++;
		}

		int math = b / d;
		if(b % d != 0){
			math++;
		}

		System.out.print(l - Math.max(korea, math));
	}
}