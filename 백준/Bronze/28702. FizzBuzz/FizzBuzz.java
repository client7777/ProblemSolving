import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String num1 = br.readLine();
        String num2 = br.readLine();
        String num3 = br.readLine();

        int num = 0;

        if(isNum(num1)) num = Integer.parseInt(num1) + 3;
        else if(isNum(num2)) num = Integer.parseInt(num2) + 2;
        else num = Integer.parseInt(num3) + 1;

        System.out.print(ans(num));

    }

    static String ans(int num)
    {
        if(num % 15 == 0) return "FizzBuzz";
        else if(num % 3 == 0) return "Fizz";
        else if(num % 5 == 0) return "Buzz";
        else return Integer.toString(num);
    }

    static boolean isNum(String string)
    {
        return !string.equals("Fizz") && !string.equals("Buzz") && !string.equals("FizzBuzz");
    }
}
