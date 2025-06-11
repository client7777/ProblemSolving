import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int burger = 2000;

		for(int i = 0; i < 3; i++){
			burger = Math.min(burger, Integer.parseInt(br.readLine()));
		}

		int coke = Math.min(Integer.parseInt(br.readLine()), Integer.parseInt(br.readLine()));

		System.out.print(burger + coke - 50);
	}
}