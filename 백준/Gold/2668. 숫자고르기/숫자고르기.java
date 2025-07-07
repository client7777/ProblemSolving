import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class Main {
	static int n;
	static boolean[] visit;
	static HashMap<Integer, Integer> map = new HashMap<>();
	static ArrayList<Integer> list = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());

		visit = new boolean[n+1];

		for(int i = 1; i <= n; i++){
			int num = Integer.parseInt(br.readLine());
			map.put(i, num);
		}

		for(int i = 1; i <= n; i++){

			if(!visit[i]){
				visit[i] = true;
				dfs(i,i);
				visit[i] = false;
			}
		}

		System.out.println(list.size());

		list.sort(Comparator.comparingInt(o->o));
		for(int i : list){
			System.out.println(i);
		}

	}

	static void dfs(int index,int target){
		int value = map.get(index);

		if(!visit[value]){
			visit[value] = true;
			dfs(value, target);
			visit[value] = false;
		}

		if(value == target){
			list.add(target);
		}
	}
}
