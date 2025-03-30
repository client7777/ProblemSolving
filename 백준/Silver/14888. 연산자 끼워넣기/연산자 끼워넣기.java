import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main
{
    static int max = Integer.MIN_VALUE;
    static int min = Integer.MAX_VALUE;
    static ArrayList<Integer> num = new ArrayList<>();
    static int[] operation = new int[4];
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++)
        {
            num.add(Integer.parseInt(st.nextToken()));
        }

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<4; i++)
        {
            operation[i] = Integer.parseInt(st.nextToken());
        }
        backTrack(0,num.get(0));

        System.out.println(max);
        System.out.println(min);
    }
    static void backTrack(int depth,int result)
    {
        if(depth == num.size()-1)
        {
            max = Math.max(max, result);
            min = Math.min(min,result);

            return;
        }
        for(int i=0; i<4; i++)
        {
            if(operation[i] != 0)
            {
                operation[i]--;
                switch (i)
                {
                    case 0:
                        backTrack(depth + 1,result + num.get(depth+1));
                        operation[i]++;
                        break;
                    case 1:
                        backTrack(depth + 1,result - num.get(depth+1));
                        operation[i]++;
                        break;
                    case 2:
                        backTrack(depth + 1,result * num.get(depth+1));
                        operation[i]++;
                        break;
                    case 3:
                        if(result < 0)
                        {
                            backTrack(depth+1, -(-result / num.get(depth+1)));
                        }
                        else
                            backTrack(depth+1,result / num.get(depth+1));
                            operation[i]++;
                        break;
                }
            }
        }
    }
}
