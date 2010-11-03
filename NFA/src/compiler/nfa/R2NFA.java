package compiler.nfa;

import java.util.LinkedList;
import compiler.nfa.entity.NFA;
import compiler.nfa.io.ReadFile;
import compiler.nfa.util.State;
import compiler.util.MyLinkedList;

public class R2NFA {
	
	LinkedList<NFA> NFAlist ;
	
	ReadFile rf=new ReadFile();
	Character ch ;
	int weight=0;
	public R2NFA(String filename){
		rf.setFile(filename);
		NFAlist=MyLinkedList.getNFAlist();
		

	}
	
	
	public void go(){
		System.out.println(rf.getFile());
		ch=rf.getNextChar();
		System.out.println("ch="+ch);
		if(ch!='.'&&ch!='|'&&ch!='*'){
			//charlist.add(c);
			NFA nfa = new NFA(State.weight++,ch,State.weight++);//构造识别字符c的nfa
			NFAlist.push(nfa);//一条弧入队列
			State.statestack.push(nfa);//状态堆栈中也要放入
			//构造新的开始节点和终态节点，将其放入 状态栈
			System.out.println("刚入队列的弧");
			System.out.print(NFAlist.peek().getFrom());
			System.out.print("__");
			System.out.print(NFAlist.peek().getReceive());
			System.out.print("__");
			System.out.println(NFAlist.peek().getTo());
			
			
		}
		else if(ch=='.'){
			//当读到小数点，说明这里是连接，将够着a.b这样的NFA
			//状态站中两个top出堆栈
			NFA nfa2=State.statestack.pop();
			NFA nfa1=State.statestack.pop();
			
			//进行连接运算，并且将整体放入状态堆栈
			State.statestack.push(nfa1.connect(nfa2));
	
		}
		else if(ch=='|'){
		  //状态堆栈中的两个元素出栈
			NFA nfa2=State.statestack.pop();
			NFA nfa1=State.statestack.pop();
			State.statestack.push(nfa1.or(nfa2));
			
		}else if(ch=='*'){
			//状态堆栈中的堆栈顶元素出栈
			NFA nfa= State.statestack.pop();
			State.statestack.push(nfa.bibao());
			
		
		}
		
		
		if(rf.getCurrentChar()!=null){
			go();
		}
			
			
	}
	

}

