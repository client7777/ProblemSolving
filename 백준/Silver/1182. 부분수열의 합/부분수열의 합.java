import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static int n;
	static int s;
	static int answer;
	static ArrayList<Integer> list;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		s = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		list = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			list.add(Integer.parseInt(st.nextToken()));
		}

		dfs(0, 0);
		if(s == 0) answer--;
		System.out.print(answer);
	}

	static void dfs(int depth, int sum){
		if(depth == n){
			
			if(sum == s){
				answer++;
			}
			
			return;
		}
		
		dfs(depth + 1, sum);
		dfs(depth + 1, sum + list.get(depth));
	}
}