import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());

		int odd = 0;
		int even = 0;

		String num = br.readLine();
		for(int i = 0; i < n; i++) {
			if((num.charAt(i) - '0') % 2 == 0){
				even++;
			}
			else {
				odd++;
			}
		}

		if(odd > even){
			System.out.print("1");
		}
		else if(odd == even){
			System.out.print("-1");
		}
		else {
			System.out.print("0");
		}
	}
}