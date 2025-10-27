import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		int cnt = 0;
		HashSet<String> set = new HashSet<>();

		for(int i = 0; i < n; i++) {
			String str = br.readLine();
			if(str.equals("ENTER")){
				cnt += set.size();
				set.clear();
			}
			else{
				set.add(str);
			}
		}

		cnt += set.size();
		System.out.print(cnt);
	}
}