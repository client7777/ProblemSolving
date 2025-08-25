import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int test_case = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        for(int i = 1; i <= test_case; i++) {

            int n = Integer.parseInt(br.readLine());

            HashMap<Integer, Integer> map = new HashMap<>();

            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                int num = Integer.parseInt(st.nextToken());
                map.put(num, map.getOrDefault(num, 0) + 1);
            }


            for(int key : map.keySet()){
                if(map.get(key) == 1){
                    sb.append("Case #").append(i).append(": ").append(key).append("\n");
                }
            }
        }

        System.out.println(sb);
    }
}
