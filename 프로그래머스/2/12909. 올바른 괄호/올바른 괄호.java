import java.util.*;

class Solution 
{
    boolean solution(String s)
    {
       Stack<Character> stack = new Stack<>();

       char[] ch = s.toCharArray();

       for(char val : ch)
       {
           if(val == '(')
               stack.add(val);
           else if(val == ')')
           {
               if(stack.isEmpty())
                   return false;

               stack.pop();
           }
       }
       return stack.isEmpty();
    }
}