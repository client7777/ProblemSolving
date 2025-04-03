package zzz;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj_11189
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        int up = 0;
        int down = 0;
        int preferred = 0;

        char init = str.charAt(0);
        char[] ch = str.toCharArray();

        // 항상 좌석을 'U'로 유지하는 경우
        char cur = init;
        for (int i = 1; i < ch.length; i++)
        {
            if (ch[i] == 'U')
            {
                if (cur == 'D')
                {
                    up++; // 좌석을 올려야 함
                }
            }
            else
            { // ch[i] == 'D'
                if (cur == 'U')
                {
                    up++; // 좌석을 내려야 함
                }
                up++; // 다시 'U'로 맞춰둬야 함
            }
            cur = 'U'; // 항상 'U' 유지
        }

        // 항상 좌석을 'D'로 유지하는 경우
        cur = init;
        for (int i = 1; i < ch.length; i++)
        {
            if (ch[i] == 'D')
            {
                if (cur == 'U')
                {
                    down++; // 좌석을 내려야 함
                }
            }
            else
            {
                // ch[i] == 'U'
                if (cur == 'D')
                {
                    down++; // 좌석을 올려야 함
                }
                down++; // 다시 'D'로 맞춰둬야 함
            }

            cur = 'D'; // 항상 'D' 유지
        }

        // 사용자 선호에 맞추는 경우
        cur = init;
        for (int i = 1; i < ch.length; i++)
        {
            if (cur != ch[i])
            {
                preferred++;
            }
            cur = ch[i];
        }

        System.out.println(up);
        System.out.println(down);
        System.out.println(preferred);
    }
}
