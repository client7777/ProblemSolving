import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();

		if(input.equals("n") || input.equals("N")){
			System.out.print("Naver D2");
		}
		else {
			System.out.print("Naver Whale");
		}
	}
}