import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int p = Integer.parseInt(br.readLine());

		int p1 = p - 500;
		int p2 = (int)(p * 0.9);
		int p3 = p - 2000;
		int p4 = (int)(p * 0.75);

		int answer = p;

		if(n >= 20){
			answer = Math.min(Math.min(p1, p2), Math.min(p3, p4));
		}
		else if(n >= 15){
			answer = Math.min(p3, Math.min(p1, p2));
		}
		else if(n >= 10){
			answer = Math.min(p1, p2);
		}
		else if(n >= 5){
			answer = p1;
		}
		
		if(answer <= 0){
			answer = 0;
		}

		System.out.println(answer);
	}
}