package zzz;
import java.io.*;
public class boj_25915
{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();

        int str = s.charAt(0);
        int i = 'I';
        System.out.print(Math.abs(str - i) + 84);

    }
}
