package compiler.nfa.io;

import java.util.Stack;
/**/
import compiler.nfa.util.Priority;

public class ReadFile {
     private  String file;
     public  int row=0;
//	private static ReadFile readFile;
//     static {
//    	 
//    	 readFile = new ReadFile();
//     }
//     
//     
//     public static ReadFile getReadFile(){
//    	 return readFile;
//     }
    
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
 			 
// 			 System.out.println(Priority.getPriority().isFrist(stack.peek(),c));
 			 
 		  switch(Priority.getPriority().isFrist(stack.peek(),c)){
 		  
 			 case 0:stack.push(c);
 			         System.out.println(c+"��ջ");
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
 				 System.out.println("���������");
 				 sb.append(stack.pop());
 				 state=2;
 				 break;
 			 case -1:System.out.println(stack.pop());
 			        state=0;
 			         return null;
 			 case 5:
 				System.out.println("����״̬��"+c);sb.append(c);
 				state=0;
 				break;
 			 }
 			 System.out.println(stack.isEmpty());
 		 }
 		
     }
}
