import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main 
{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int set = 1;

        while (true) {
            int size = Integer.parseInt(br.readLine());

            if (size == 0) break;

            int[] heights = new int[size];
            int sum = 0;

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < size; i++) {
                heights[i] = Integer.parseInt(st.nextToken());
                sum += heights[i];
            }

            int avg = sum / size;
            int cnt = 0;

            for (int height : heights) {
                if (height > avg) cnt += height - avg;
            }
            
            sb.append("Set #").append(set).append("\n");
            sb.append("The minimum number of moves is ").append(cnt).append(".\n");
            sb.append('\n');

            set++;
        }

        System.out.print(sb);
    }
}
