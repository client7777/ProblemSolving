import java.util.*;


public class Main{
  public static void main(String[] args) {

    Scanner s = new Scanner(System.in);

    int test_case = s.nextInt();

    Stack<Integer> stack = new Stack<Integer>();

    for(int i=0; i<test_case; i++)
      {
        String command = s.next();

        switch(command)
          {
              case "push" :
              int item = s.nextInt();
              stack.push(item);
              break;
              
              case "pop" :
              if(!stack.empty())
              {
                System.out.println(stack.pop());
              }
              else
              {
                System.out.println(-1);
              }
              break;
              
              case "empty" :
              if(!stack.empty())
              {
                System.out.println(0);
              }
              else
              {
                System.out.println(1);
              }
              break;
              
              case "size" :
              System.out.println(stack.size());
              break;
              
              case "top" :
              if(!stack.empty())
              {
                System.out.println(stack.peek());
              }
              else
              {
                System.out.println(-1);
              }
              break;
                
          }
        
        
      }
  }

 
}