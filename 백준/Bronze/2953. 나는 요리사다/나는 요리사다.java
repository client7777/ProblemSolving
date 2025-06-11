import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int answer = 0;
        int sum = 0;

        for(int i = 1; i <=5; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            int curSum = 0;

            for(int j = 0; j < 4; j++){
                curSum += Integer.parseInt(st.nextToken());
            }

            if(curSum > sum){
                answer = i;
                sum = curSum;
            }
        }

        System.out.print(answer + " " + sum);
    }
}
