import java.io.*;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        HashMap<String, String> map = new HashMap<>();

        for(int i=0; i<n; i++)
        {
            st = new StringTokenizer(br.readLine());
            String key = st.nextToken();
            String value = st.nextToken();

            map.put(key, value);
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<m; i++)
        {
            String find = br.readLine();
            sb.append(map.get(find)).append('\n');
        }
        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
    }
}
