import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split("");

		ArrayList<String> list = new ArrayList<String>();

		for(int i = 0; i < str.length; i++){
			list.add(str[i]);
		}

		list.sort(Comparator.reverseOrder());

		StringBuilder sb = new StringBuilder();

		for(String s : list){
			sb.append(s);
		}

		System.out.println(sb.toString());
	}
}