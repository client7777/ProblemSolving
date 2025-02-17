import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main
{
    static int n,s;
    static long ans;
    static int[] arr;
    static ArrayList<Integer> left = new ArrayList<>();
    static ArrayList<Integer> right = new ArrayList<>();
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());

        arr = new int[n];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++)
        {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        //배열을 절반으로 나눠 각각 부분집합의 합의 리스트를 구한다.
        dfs(0,n/2, 0, left);
        dfs(n/2,n, 0, right);

        left.sort(Comparator.comparingInt(o->o));
        right.sort(Comparator.comparingInt(o->o));

        twoPointer();

        System.out.print(s == 0 ? ans - 1 : ans);

    }

    static void twoPointer()
    {
        int leftPos = 0;
        int rightPos = right.size() -1;

        while (leftPos < left.size() && rightPos >= 0)
        {
            int leftVal = left.get(leftPos);
            int rightVal = right.get(rightPos);

            if(leftVal + rightVal == s)
            {
                long cntL = 0;
                long cntR = 0;

                while (leftPos < left.size() && leftVal == left.get(leftPos))
                {
                    cntL++;
                    leftPos++;
                }

                while (rightPos >= 0 && rightVal == right.get(rightPos))
                {
                    cntR++;
                    rightPos--;
                }
                ans += cntL * cntR;
            }

            if(leftVal + rightVal < s)
                leftPos++;
            if(leftVal + rightVal > s)
                rightPos--;
        }
    }

    static void dfs(int depth, int end, int sum, ArrayList<Integer> list)
    {
        if(depth == end)
        {
            list.add(sum);
            return;
        }

        dfs(depth + 1, end, sum, list);
        dfs(depth + 1, end, sum + arr[depth], list);
    }
}
