import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		ArrayList<Integer> list = new ArrayList<>();

		int min = 100;
		for(int i = 0; i < 7; i++){

			int num = Integer.parseInt(br.readLine());

			if(isOdd(num)){
				list.add(num);
				min = Math.min(min, num);
			}
		}

		if(list.isEmpty()){
			System.out.println(-1);
			return;
		}
		
		int sum = 0;
		for(int val : list){
			sum += val;
		}

		System.out.println(sum);
		System.out.println(min);
	}

	static boolean isOdd(int n){
		return n % 2 == 1;
	}
}