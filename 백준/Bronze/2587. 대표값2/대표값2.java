import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int sum = 0;
		ArrayList<Integer> list = new ArrayList<>();
		
		for(int i = 0; i < 5; i++){
			int num = Integer.parseInt(br.readLine());
			sum += num;
			list.add(num);
		}

		list.sort(Comparator.comparingInt(o->o));
		
		StringBuilder sb = new StringBuilder();
		sb.append(sum / 5).append('\n');
		sb.append(list.get(2)).append('\n');

		System.out.println(sb);
	}
}