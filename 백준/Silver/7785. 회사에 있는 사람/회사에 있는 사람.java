import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main
{
    static HashSet<String> set = new HashSet<>();
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st;
        for(int i=0; i<n; i++)
        {
            st = new StringTokenizer(br.readLine());

            String name = st.nextToken();
            String log = st.nextToken();

            if(log.equals("enter"))
                set.add(name); // 출근한 기록이 있으면 set에 추가
            else
                set.remove(name); // 퇴근한 기록이 있으면  set에서 제거
        }
        ArrayList<String> ans = new ArrayList<>(set);
        Collections.sort(ans, Collections.reverseOrder());
        for(String name:ans)
        {
            System.out.println(name);
        }
    }
}
