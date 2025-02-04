import java.io.*;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main
{
    static int n,r,m,k;
    static double INF = 100 * 10000 + 1;
    static HashMap<String, Integer> hashMap = new HashMap<>();
    static double[][] ticket, no_ticket;
    static String[] target;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        ticket = new double[n][n];
        no_ticket = new double[n][n];

        int idx = 0;
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++)
        {
            String city = st.nextToken();
            if(!hashMap.containsKey(city))
            {
                hashMap.put(city, idx++);
            }
        }

        m = Integer.parseInt(br.readLine());
        target = new String[m];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<m; i++)
        {
            target[i] = st.nextToken();
        }

        for(int i=0; i<n; i++)
        {
            for(int j=0; j<n; j++)
            {
                if(i == j) continue;
                ticket[i][j] = INF;
                no_ticket[i][j] = INF;
            }
        }
        k = Integer.parseInt(br.readLine());
        for(int i=0; i<k; i++)
        {
            st = new StringTokenizer(br.readLine());
            String type = st.nextToken();
            String startCity = st.nextToken();
            String endCity = st.nextToken();
            double cost = Double.parseDouble(st.nextToken());

            int startNum = hashMap.get(startCity);
            int endNum = hashMap.get(endCity);

            if(type.equals("Mugunghwa") || type.equals("ITX-Saemaeul") || type.equals("ITX-Cheongchun"))
            {
                ticket[startNum][endNum] = 0;
                ticket[endNum][startNum] = 0;
            }
            else if(type.equals("S-Train") || type.equals("V-Train"))
            {
                double discount = cost / 2.0;
                ticket[startNum][endNum] = Math.min(ticket[startNum][endNum], discount);
                ticket[endNum][startNum] = Math.min(ticket[endNum][startNum], discount);
            }
            else
            {
                ticket[startNum][endNum] = Math.min(ticket[startNum][endNum], cost);
                ticket[endNum][startNum] = Math.min(ticket[endNum][startNum], cost);
            }
            no_ticket[startNum][endNum] = Math.min(no_ticket[startNum][endNum], cost);
            no_ticket[endNum][startNum] = Math.min(no_ticket[endNum][startNum], cost);
        }

        double ticketCost = floyd(ticket) + r;
        double no_ticketCost = floyd(no_ticket);
        System.out.println(ticketCost >= no_ticketCost ? "No" : "Yes");
    }
    static double floyd(double[][] map)
    {
        for(int l=0; l<n; l++)
        {
            for(int i=0; i<n; i++)
            {
                for(int j=0; j<n; j++)
                {
                    if(map[i][j] > map[i][l] + map[l][j])
                    {
                        map[i][j] = map[i][l] + map[l][j];
                    }
                }
            }
        }

        double tmp = 0;
        for(int i=0; i<m-1; i++)
        {
            tmp += map[hashMap.get(target[i])][hashMap.get(target[i+1])];
        }

        return tmp;
    }
}
