package compiler.nfa.io;

import java.util.Stack;
/**/
import compiler.nfa.util.Priority;

public class ReadFile {
     private  String file;
     public  int row=0;

    
     public  String getFile() {
 		return file;
 	}
 	public void setFile(String file) {
               this.file = file;
 		
 	}
 	
 	public Character getNextChar(){
 		if(row==file.length()){
 			
 			return null;
 		}
 		System.out.println("filelength="+file.length());
 		System.out.println("row="+row);
 		return this.file.charAt(row++);
 		
 	}
 	public Character getCurrentChar(){
	if(row==file.length()){
 			
 			return null;
 		}
 		return this.file.charAt(row);
 	}
 	public Character getLastChar(){
 		if(row<0){
 			return null;
 		}
 		return this.file.charAt(--row);
 	}
 	
 	public int returnto(int i){
 		
 		return row=row-i;
 	}
 	
 	
 	 static Character c;
 	 public boolean isin(Character c){
 		 if(('a'<=c&&c<='z')||(c>='A'&&c<='Z')||(c>=0&&c<=9)||c=='.'||c=='|'||c=='*'||c=='#'){
 			 return true;
 		 }
 		return false;
 	}
	public  String isCorrect(){
		Stack<Character> stack= new Stack<Character>();
		  
		  StringBuffer sb=new StringBuffer();
 		 stack.push('#');
 		 int state=0;
 		 while(true){
 			 if(state==0){
 			  c=this.getNextChar();
 			}
 			 if(c==null){
 				
 				 return null;
 			 } 
 			 if(c=='|'&&(getCurrentChar()==')'||getCurrentChar()=='|'
 				 ||this.getCurrentChar()=='*'||getCurrentChar()=='#'||
 				getCurrentChar()=='.'))
 			      {
 				      return null;
 			      }
 			 
 			if(!isin(c))//检查是否有非法字符
			      {
				      return null;
			      }
 			//字符之间没有输入符号
 			if((('a'<=c&&c<='z')||(c>='A'&&c<='Z'))&&(('a'<=getCurrentChar()&&getCurrentChar()<='z')||(getCurrentChar()>='A'&&getCurrentChar()<='Z'))){
 				return null;
 			}
            	  
            
 			 
 		  switch(Priority.getPriority().isFrist(stack.peek(),c)){
 		  
 			 case 0:stack.push(c);
 			         System.out.println(c+"入栈");
 			         state=0;
 			         break;
 			 
 			 case 1:if(c=='#'&&stack.peek()=='#'){
 				   state=0;
 				   return sb.toString();
 			 }else if(c==')'&&stack.peek()=='('){
 				 state=0;
 				 stack.pop();
 				 
 			 } break;
 			 
 			 
 			 case 2:
 				 System.out.println("存入运算符");
 				 sb.append(stack.pop());
 				 state=2;
 				 break;
 			 case -1:System.out.println(stack.pop());
 			        state=0;
 			         return null;
 			 case 5:
 				System.out.println("存入状态符"+c);sb.append(c);
 				state=0;
 				break;
 			 }
 			 System.out.println(stack.isEmpty());
 		 }
 		
     }
}
