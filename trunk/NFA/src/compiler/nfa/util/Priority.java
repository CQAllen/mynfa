package compiler.nfa.util;

public class Priority {
	/*
	 *�������ȼ�
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
		 * c1���ȼ�����c2�ͷ���2
		 * ���c1���ȼ�����c2�ͷ���1
		 * ���c1���ȼ�С��c2�ͷ���0
		 * ���󷵻�-1
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
