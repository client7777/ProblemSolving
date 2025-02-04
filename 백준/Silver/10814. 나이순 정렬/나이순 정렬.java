import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main
{
    public static class Person
    {
        int age;
        String name;

        public Person(int age, String name)
        {
            this.age = age;
            this.name = name;
        }
    }
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        Person[] p = new Person[n];

        for(int i=0; i<n; i++)
        {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int age = Integer.parseInt(st.nextToken());
            String name = st.nextToken();

            p[i] = new Person(age, name);
        }

        Arrays.sort(p, Comparator.comparingInt(o->o.age));
        for(int i=0; i<n; i++)
        {
            System.out.println(p[i].age + " " + p[i].name);
        }
    }
}
