class Solution 
{
   public boolean solution(String s)
    {
        if(!(s.length() == 4 || s.length() == 6)) return false;
        
        char[] ch = s.toCharArray();
        
        for(char c : ch)
        {
            if(!Character.isDigit(c)) return false;
        }
        
        return true;
    }
}