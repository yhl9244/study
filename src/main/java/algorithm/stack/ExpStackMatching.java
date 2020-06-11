package algorithm.stack;
import java.util.Stack;

public class ExpStackMatching {
    
    public boolean matching(String expression)
    {
        if(expression==null||expression=="")
        {
            System.out.println( "输入表达式为空或没有输入表达式" ) ;
        }
        
        Stack<Character> stack = new Stack<Character>() ;
        
        for(int index=0 ; index<expression.length();index++)
        {
            switch(expression.charAt(index))
            {
                case '(':
                    stack.push(expression.charAt(index)) ;
                    break ;
                case '{':
                    stack.push(expression.charAt(index)) ;
                    break ;
                case ')':
                    if(!stack.empty()&&stack.peek()=='(')
                    {
                        stack.pop() ;
                    }
                    break ;    
                    
                case '}':
                    if(!stack.empty()&&stack.peek()=='{')
                    {
                        stack.pop();
                    }
            }    
        }
        
        if(stack.empty())
            return true ;
        return false ;
    }
    
    public static void main(String[] args) {
        
        String expression = "{{{{}}}} " ;
        
        ExpStackMatching oj = new ExpStackMatching() ;
        
        boolean flag = oj.matching(expression) ;
        
        if(flag)
        {
            System.out.println("匹配成功！") ;
        }
        else
        {
            System.out.println(" 匹配失败 ");
        }
    }
    
    
    
}