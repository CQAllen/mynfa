package compiler.nfa;

import java.util.Stack;

import compiler.nfa.entity.NFA;
import compiler.nfa.io.ReadFile;
import compiler.util.MyStack;

public class RtoNfa {
	Stack<NFA> mystack;
	ReadFile rf;
    public void go(String file){//将isCorrect()处理后得到的字符串传过来
    	rf=ReadFile.getReadFile();
    	rf.setFile(file);
    	mystack=MyStack.getMyStack();//这个堆栈用来存放状态转换的NFA
    	Character c;
    	StringBuffer sb=new StringBuffer();
    	NFA x=new NFA();
    	x.setFrom(0);
    	x.setReceive('#');
    	x.setTo(1);
    	mystack.push(x);//先放入一个初始状态
    	NFA y=new NFA();//建立一个终结状态
    	
//    	y.setFrom(0);  终结状态这3个都不设置，代表是终结状态
//     	y.setReceive(null);
//    	y.setTo();
    	while (true){
    		c=rf.getNextChar();
    		if(c!='.'||c!='|'||c!='*'){
    			sb.append(c);
    			continue;
    		}else if(c=='.'){
    			
    		}
    	}
    	 
    }
}
