import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int m = Integer.parseInt(br.readLine());
		int n = Integer.parseInt(br.readLine());

		List<Integer> list = new ArrayList<>();

		int sum = 0;
		for(int i = 1; i * i <= n; i++){
			if(i * i >= m && i * i <= n){
				sum += i * i;
				list.add(i * i);
			}
		}

		if(list.size() == 0){
			System.out.print(-1);
		}
		else{
			System.out.println(sum);
			System.out.print(list.get(0));
		}
	}
}