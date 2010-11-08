package compiler.nfa.util;

public class Priority {
	/*
	 *定义优先级
	 */
	
	private static Priority pri;
	static {
		pri= new Priority();
	}
	public static Priority getPriority(){
		return pri;
	}
	
	public int isFrist(char c1,char c2){
		/*
		 * c1优先级高于c2就返回2
		 * 如果c1优先级等于c2就返回1
		 * 如果c1优先级小与c2就返回0
		 * 错误返回-1
		jj
		 */
		switch (c1){
		
		
		case '.':if(c2=='.'||c2==')'||c2=='|'||c2=='#'){
			         return 2;
		          }else if(c2=='('||c2=='*'){
		        	  return 0;
		          }
		
		case '|':if(c2==')'||c2=='|'||c2=='#'){
	         return 2;
         }else if(c2=='('||c2=='.'||c2=='*'){
       	  return 0;
         }
		
		case '(':if(c2=='.'||c2=='|'||c2=='('||c2=='*'){
	         return 0;
        }else if(c2==')'){
        	
      	  return 1;
        }
        else if(c2=='#'){
        	return -1;
        }
		
		case ')':if(c2=='.'||c2=='|'||c2=='('||c2=='#'||c2=='*'){
	         return 2;
       }else if(c2==')'){
     	  return -1;
       }
		
		case '#':if(c2=='.'||c2=='|'||c2=='('||c2=='*'){
			
	         return 0;
      }else if(c2==')'){
    	   return -1;
      }else if(c2=='#'){
    	  return 1;
       }
		case '*': if(c2=='.'||c2=='|'||c2==')'||c2=='*'||c2=='#'||c2=='*'){
			return 2;
		}else if(c2=='('){
			return 2;
		}
		}
		
		return 5;
	}

}
