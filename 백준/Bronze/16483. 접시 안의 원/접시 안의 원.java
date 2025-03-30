import java.io.*;
import java.util.*;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        float T = Float.parseFloat(br.readLine());

        System.out.print((int)(Math.round(Math.pow(T/2, 2))));
    }
}