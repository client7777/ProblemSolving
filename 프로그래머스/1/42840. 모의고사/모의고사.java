import java.util.*;

class Solution 
{
    public ArrayList<Integer> solution(int[] answers)
    {
        int[] stu1 = {1,2,3,4,5};
        int[] stu2 = {2, 1, 2, 3, 2, 4, 2, 5};
        int[] stu3 = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};

        int[] score = {0,0,0};
        for(int i=0; i<answers.length; i++)
        {
            if(answers[i] == stu1[i%5]) score[0]++;
            if(answers[i] == stu2[i%8]) score[1]++;
            if(answers[i] == stu3[i%10]) score[2]++;
        }
        
        int maxScore = score[0];
        for(int i=1; i<=2; i++)
        {
            maxScore = Math.max(maxScore, score[i]);
        }
        
        ArrayList<Integer> arr = new ArrayList<>();
        for(int i=0; i< score.length; i++)
        {
            if(score[i] == maxScore) arr.add(i+1);
        }
        
        return arr;
    }
}