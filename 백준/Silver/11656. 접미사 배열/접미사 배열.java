import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String str = br.readLine();

		ArrayList<String> list = new ArrayList<String>();

		for(int i = 0; i < str.length(); i++) {
			list.add(str.substring(i));
		}

		Collections.sort(list);

		StringBuilder sb = new StringBuilder();

		for(String s : list) {
			sb.append(s).append("\n");
		}

		System.out.print(sb);
	}
}