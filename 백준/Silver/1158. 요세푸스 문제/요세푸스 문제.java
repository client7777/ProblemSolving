import java.util.*;
import java.io.*;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        br.close();
        
        String answer = "<";
        
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        
        LinkedList<String> list = new LinkedList<String>();
        
        for(int i=1; i<=N; i++)
        {
            list.add(String.valueOf(i));
        }
        
        int idx = 0;
        
        while(list.size() > 1)
        {
            idx = (idx + K -1)%list.size();
            answer += list.remove(idx) + ", ";
        }
        answer+=list.remove(0) + ">";
        
        bw.write(answer);
        bw.flush();
        bw.close();
        
    }
    
}