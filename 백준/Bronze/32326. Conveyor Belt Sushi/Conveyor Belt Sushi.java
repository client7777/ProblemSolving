import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int a = Integer.parseInt(br.readLine()) * 3;
		int b = Integer.parseInt(br.readLine()) * 4;
		int c = Integer.parseInt(br.readLine()) * 5;
		
		System.out.println(a + b + c);
	}
}