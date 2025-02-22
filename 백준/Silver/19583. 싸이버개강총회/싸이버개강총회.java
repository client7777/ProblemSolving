import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int s = timeToInt(st.nextToken());
        int e = timeToInt(st.nextToken());
        int q = timeToInt(st.nextToken());

        HashSet<String> start_list = new HashSet<>(); // 입장 회원
        HashSet<String> end_list = new HashSet<>(); // 정상 출석 회원

        String input;

        while ((input = br.readLine()) != null)
        {
            st = new StringTokenizer(input);
            
            int time = timeToInt(st.nextToken());
            String name = st.nextToken();

            if(time <= s) start_list.add(name);
            if(time >= e && time <= q && start_list.contains(name)) end_list.add(name);
        }

        System.out.print(end_list.size());

    }

    static int timeToInt(String string)
    {
        return Integer.parseInt(string.substring(0, 2)) * 60 + Integer.parseInt(string.substring(3));
    }
}