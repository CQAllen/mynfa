package compiler.nfa;

import java.util.Stack;

import compiler.nfa.entity.NFA;
import compiler.nfa.io.ReadFile;
import compiler.util.MyStack;

public class RtoNfa {
	Stack<NFA> mystack;
	ReadFile rf;
    public void go(String file){//��isCorrect()�����õ����ַ���������
    	rf=ReadFile.getReadFile();
    	rf.setFile(file);
    	mystack=MyStack.getMyStack();//�����ջ�������״̬ת����NFA
    	Character c;
    	StringBuffer sb=new StringBuffer();
    	NFA x=new NFA();
    	x.setFrom(0);
    	x.setReceive('#');
    	x.setTo(1);
    	mystack.push(x);//�ȷ���һ����ʼ״̬
    	NFA y=new NFA();//����һ���ս�״̬
    	
//    	y.setFrom(0);  �ս�״̬��3���������ã��������ս�״̬
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
