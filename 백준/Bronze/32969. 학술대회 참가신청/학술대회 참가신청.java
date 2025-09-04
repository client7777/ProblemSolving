import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();

		List<String> list = new ArrayList<>();
		list.add("bigdata");
		list.add("public");
		list.add("society");

		for(String s : list){
			if(str.contains(s)){
				System.out.print("public bigdata");
				return;
			}
		}

		System.out.print("digital humanities");
	}
}