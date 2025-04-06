import java.io.*;
import java.util.StringTokenizer;

public class Main
{
    static int n;
    static int[] lc = new int[30];
    static int[] rc = new int[30];
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        StringTokenizer st;
        for(int i=1; i<=n; i++)
        {
            st = new StringTokenizer(br.readLine());
            char c,l,r;
            c = st.nextToken().charAt(0);
            l = st.nextToken().charAt(0);
            r = st.nextToken().charAt(0);

            if(l != '.') lc[c-'A'+1] = l-'A'+1;
            if(r != '.') rc[c-'A'+1] = r-'A'+1;
        }
        preOrder(1);
        System.out.println();
        inOrder(1);
        System.out.println();
        postOrder(1);
    }
    static void preOrder(int cur)
    {
        System.out.print((char)(cur+'A'-1));
        if(lc[cur] != 0) preOrder(lc[cur]);
        if(rc[cur] != 0) preOrder(rc[cur]);
    }
    static void inOrder(int cur)
    {
        if(lc[cur] != 0) inOrder(lc[cur]);
        System.out.print((char)(cur+'A'-1));
        if(rc[cur] != 0) inOrder(rc[cur]);
    }
    static void postOrder(int cur)
    {
        if(lc[cur] != 0) postOrder(lc[cur]);
        if(rc[cur] != 0) postOrder(rc[cur]);
        System.out.print((char)(cur+'A'-1));
    }
}
