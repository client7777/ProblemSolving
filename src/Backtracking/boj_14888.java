package Backtracking;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class boj_14888
{
    static int max = Integer.MIN_VALUE;
    static int min = Integer.MAX_VALUE;
    static ArrayList<Integer> num = new ArrayList<>();
    static int[] operation = new int[4];
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++)
        {
            num.add(Integer.parseInt(st.nextToken()));
        }

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<4; i++)
        {
            operation[i] = Integer.parseInt(st.nextToken());
        }
        backTrack(0,num.get(0));

        System.out.println(max);
        System.out.println(min);
    }
    static void backTrack(int depth,int result)
    {
        if(depth == num.size()-1)
        {
            max = Math.max(max, result);
            min = Math.min(min,result);

            return;
        }
        for(int i=0; i<4; i++)
        {
            if(operation[i] != 0)
            {
                operation[i]--;
                int nextResult = result;
                switch (i)
                {
                    case 0:
                        nextResult += num.get(depth + 1);
                        break;
                    case 1:
                        nextResult -= num.get(depth + 1);
                        break;
                    case 2:
                        nextResult *= num.get(depth + 1);
                        break;
                    case 3:
                        if(result < 0)
                        {
                            nextResult = -(-nextResult / num.get(depth + 1));
                        }
                        else
                            nextResult /= num.get(depth+1);
                        break;
                }
                backTrack(depth + 1, nextResult);
                operation[i]++; // 연산자 복구는 switch문 밖에서 한번만
            }
        }
    }
}
/*
주어진 숫자의 순서도 고려할 경우

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int max = Integer.MIN_VALUE;
    static int min = Integer.MAX_VALUE;
    static ArrayList<Integer> num = new ArrayList<>();
    static int[] operation = new int[4];
    static boolean[] used;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            num.add(Integer.parseInt(st.nextToken()));
        }

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < 4; i++) {
            operation[i] = Integer.parseInt(st.nextToken());
        }

        used = new boolean[n]; // 숫자 순서 변경용 방문 배열
        permuteNumbers(new ArrayList<>());

        System.out.println(max);
        System.out.println(min);
    }

    // 숫자의 순열 생성
    static void permuteNumbers(ArrayList<Integer> permutation) {
        if (permutation.size() == num.size()) {
            // 각 순열에 대해 백트래킹 함수 호출
            backTrack(0, permutation.get(0), permutation);
            return;
        }

        for (int i = 0; i < num.size(); i++) {
            if (!used[i]) {
                used[i] = true;
                permutation.add(num.get(i));
                permuteNumbers(permutation);
                permutation.remove(permutation.size() - 1);
                used[i] = false;
            }
        }
    }

    // 주어진 숫자 순열과 연산자로 가능한 모든 결과 계산
    static void backTrack(int depth, int result, ArrayList<Integer> permutation) {
        if (depth == permutation.size() - 1) {
            max = Math.max(max, result);
            min = Math.min(min, result);
            return;
        }

        for (int i = 0; i < 4; i++) {
            if (operation[i] != 0) {
                operation[i]--;
                int nextResult = result;

                switch (i) {
                    case 0: nextResult += permutation.get(depth + 1); break;
                    case 1: nextResult -= permutation.get(depth + 1); break;
                    case 2: nextResult *= permutation.get(depth + 1); break;
                    case 3:
                        if (nextResult < 0) {
                            nextResult = -(-nextResult / permutation.get(depth + 1));
                        } else {
                            nextResult /= permutation.get(depth + 1);
                        }
                        break;
                }

                backTrack(depth + 1, nextResult, permutation);
                operation[i]++;
            }
        }
    }
}
*/
