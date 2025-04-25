import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.PriorityQueue;

public class Main
{
    static HashSet<Integer> set = new HashSet<>();
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        PriorityQueue<Student> k = new PriorityQueue<>();
        PriorityQueue<Student> e = new PriorityQueue<>();
        PriorityQueue<Student> m = new PriorityQueue<>();
        PriorityQueue<Student> s = new PriorityQueue<>();

        for(int i=0; i<n; i++)
        {
            String[] str = br.readLine().split(" ");
            int studentNum = Integer.parseInt(str[0]);
            int korea = Integer.parseInt(str[1]);
            int eng = Integer.parseInt(str[2]);
            int math = Integer.parseInt(str[3]);
            int science = Integer.parseInt(str[4]);

            k.add(new Student(studentNum, korea));
            e.add(new Student(studentNum, eng));
            m.add(new Student(studentNum, math));
            s.add(new Student(studentNum, science));
        }

        StringBuilder sb = new StringBuilder();
        sb.append(pollValidStudent(k)).append(" ");
        sb.append(pollValidStudent(e)).append(" ");
        sb.append(pollValidStudent(m)).append(" ");
        sb.append(pollValidStudent(s)).append(" ");

        System.out.print(sb);
    }

    private static int pollValidStudent(PriorityQueue<Student> pq)
    {
        while (!pq.isEmpty())
        {
            Student cur = pq.poll();
            if (!set.contains(cur.num))
            {
                set.add(cur.num);
                return cur.num;
            }
        }

        return -1;
    }

    static class Student implements Comparable<Student>
    {
        int num;
        int score;

        public Student(int num, int score)
        {
            this.num = num;
            this.score = score;
        }

        @Override
        public int compareTo(Student o)
        {
            if(this.score == o.score)
            {
                return Integer.compare(this.num, o.num);
            }

            return Integer.compare(o.score, this.score);
        }
    }
}
