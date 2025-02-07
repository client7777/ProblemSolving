package Sort;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;

public class boj_1431
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        ArrayList<Info> arr = new ArrayList<>();

        for(int i=0; i<n; i++)
        {
            String str = br.readLine();
            int length = str.length();
            int sum = 0;

            char[] ch = str.toCharArray();

            for(int j=0; j<ch.length; j++)
            {
                if(ch[j] >= '0' && ch[j] <= '9')
                {
                    sum += ch[j] - '0';
                }
            }

            arr.add(new Info(str, length, sum));
        }

        arr.sort(new Comparator<Info>() {
            @Override
            public int compare(Info o1, Info o2)
            {
                if(o1.length == o2.length) // 두 문자열의 길이가 같은 경우
                {
                    if(o1.sum == o2.sum) // 길이도 같고 합도 같은 경우
                    {
                        return o1.str.compareTo(o2.str); // 문자열의 사전순 정렬
                    }
                    else
                        return o1.sum - o2.sum; // 합을 기준으로 오름차순 정렬
                }
                else // 길이가 다른 경우 길이를 기준으로 오름차순 정렬
                {
                    return o1.length - o2.length;
                }
            }
        });

        StringBuilder sb = new StringBuilder();

        for(Info val:arr)
        {
            sb.append(val.str).append('\n');
        }

        System.out.print(sb);
    }

    static class Info
    {
        String str;
        int length;
        int sum;

        public Info(String str, int length, int sum)
        {
            this.str = str;
            this.length = length;
            this.sum = sum;
        }
    }
}
