import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int test_cases = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        while (test_cases-- > 0) {
            //합이 val이 되는 쌍의 수
            int val = Integer.parseInt(br.readLine());

            sb.append("Pairs for ").append(val).append(": ");

			int cnt = 0;

            for(int i = 1; i <= val-1; i++) {
                for(int j = i + 1; j <= val; j++) {
                    if(i + j == val){
						if(cnt == 0){
							sb.append(i).append(" ").append(j);
							cnt ++;
						}
						else {
							sb.append(", ").append(i).append(" ").append(j);
						}
                    }
                }
            }

            sb.append('\n');
        }

        System.out.print(sb);
    }
}