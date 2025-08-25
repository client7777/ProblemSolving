import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        int[] d = new int[n+1];

        for(int i = 1; i <= n; i++) {
            d[i] = d[i-1] + 1;

            if(i - a -1 >= 0){
                d[i] = Math.min(d[i], d[i-a-1] + 1);
            }

            if(i - b - 1 >= 0){
                d[i] = Math.min(d[i], d[i-b-1] + 1);
            }
        }

        System.out.print(d[n]);
    }
}
