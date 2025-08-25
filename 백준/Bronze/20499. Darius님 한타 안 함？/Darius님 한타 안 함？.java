import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split("/");
		int k = Integer.parseInt(input[0]);
		int d = Integer.parseInt(input[1]);
		int a = Integer.parseInt(input[2]);
		
		if(k + a < d || d == 0){
			System.out.print("hasu");
		}
		else {
			System.out.print("gosu");
		}
	}
}