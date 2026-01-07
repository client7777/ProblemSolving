import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String id = ":" + br.readLine() + ":";
		String fan = ":fan:";

		int idx = 0;
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++){
				idx++;
				if(idx == 5){
					sb.append(id);
				}
				else{
					sb.append(fan);
				}
			}
			sb.append('\n');
		}

		System.out.println(sb.toString());
	}
}