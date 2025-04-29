import java.io.*;
import java.util.*;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<k; i++)
        {
            int score = Integer.parseInt(st.nextToken());

            int p = (score * 100) / n;
            sb.append(getGrade(p)).append(" ");
        }

        System.out.print(sb);
    }

    static int getGrade(int p)
    {
        int grade = -1;

        if(p >= 0 && p <= 4)
        {
            grade = 1;
        }
        else if(p > 4 && p <= 11)
        {
            grade = 2;
        }
        else if(p > 11 && p <= 23)
        {
            grade = 3;
        }
        else if(p > 23 && p <= 40)
        {
            grade = 4;
        }
        else if(p > 40 && p <= 60)
        {
            grade = 5;
        }
        else if(p > 60 && p <= 77)
        {
            grade = 6;
        }
        else if(p > 77 && p <= 89)
        {
            grade = 7;
        }
        else if(p > 89 && p <= 96)
        {
            grade = 8;
        }
        else grade = 9;

        return grade;
    }
}