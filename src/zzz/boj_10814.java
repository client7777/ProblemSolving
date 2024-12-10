package zzz;

import java.io.*;
import java.util.*;

public class boj_10814
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

        ArrayList<Person> list = new ArrayList<>();
        for(int i=0; i<n; i++)
        {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int age = Integer.parseInt(st.nextToken());
            String name = st.nextToken();

            list.add(new Person(age, name));
        }
        list.sort(Comparator.comparingInt(o -> o.age));

        for(Person person:list)
        {
            System.out.println(person.age + " " + person.name);
        }
    }
}
