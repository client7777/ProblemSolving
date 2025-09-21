import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();

		String[] list = {"c=", "c-", "dz=", "d-", "lj", "nj", "s=", "z="};

		for(String s : list){
			str = str.replace(s, "@");
		}

		System.out.print(str.length());
	}
}