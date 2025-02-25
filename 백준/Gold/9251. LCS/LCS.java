import java.io.*;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        int[][] d = new int[1005][1005]; // d[i][j] = a의 i-1번째 글자와 b의 i-1번째 글자까지 최장 공통 부분 수열

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String a = br.readLine();
        String b = br.readLine();

        int len_a = a.length();
        int len_b = b.length();

        char[] ch_a = a.toCharArray();
        char[] ch_b = b.toCharArray();
        
        for(int i=1; i<=a.length(); i++)
        {
            for(int j=1; j<=b.length(); j++)
            {
                if(ch_a[i-1] == ch_b[j-1])
                {
                    d[i][j] = d[i-1][j-1] + 1;
                }
                else d[i][j] = Math.max(d[i-1][j], d[i][j-1]);
            }
        }
        System.out.print(d[len_a][len_b]);
    }
}
