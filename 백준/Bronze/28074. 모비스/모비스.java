import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		char[] chars = str.toCharArray();

		HashSet<Character> set = new HashSet<>();
		for(char c : chars) {
			if(c == 'M' || c == 'O' || c == 'I' || c == 'S' || c == 'B'){
				set.add(c);
			}
		}

		if(set.size() == 5){
			System.out.println("YES");
		}
		else{
			System.out.println("NO");
		}
	}
}