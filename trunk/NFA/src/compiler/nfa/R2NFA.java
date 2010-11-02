package compiler.nfa;

import java.util.LinkedList;
import java.util.Stack;


import compiler.nfa.entity.NFA2;
import compiler.nfa.io.ReadFile;
import compiler.util.MyLinkedList;

public class R2NFA {
	 Stack<Note> statestack;//状态栈，存放每次的开始节点和终态节点
	LinkedList<NFA2> NFAlist ;
	LinkedList<Character> charlist;
	ReadFile rf=new ReadFile();
	Character c ;
	int weight=0;
	public R2NFA(String filename){
		rf.setFile(filename);
		NFAlist=MyLinkedList.getNFAlist();
		statestack=new Stack<Note>();
		charlist=new LinkedList<Character>();
		System.out.println(weight);
		Note start = new Note(weight++);
		System.out.println(weight);
		Note end = new Note(weight++);
		System.out.println(weight);
		System.out.println("start="+start.getWeight());
		System.out.println("end="+end.getWeight());
		
		statestack.add(end);
		statestack.add(start);
	}
	
	
	public void go(){
		System.out.println(rf.getFile());
		c=rf.getNextChar();
		System.out.println("c="+c);
		if(c!='.'&&c!='|'&&c!='*'){
			//charlist.add(c);
			NFA2 nfa = new NFA2();//构造识别字符c的nfa
			System.out.println("................."+statestack.size());
			nfa.setStart(statestack.pop().getWeight());
			nfa.setGet(c);
			nfa.setEnd(statestack.pop().getWeight());
			System.out.println("................."+statestack.size());
			NFAlist.push(nfa);//一条弧入队列
			//构造新的开始节点和终态节点，将其放入 状态栈
			System.out.println("刚入队列的弧");
			System.out.print(NFAlist.peek().getStart());
			System.out.print("__");
			System.out.print(NFAlist.peek().getGet());
			System.out.print("__");
			System.out.println(NFAlist.peek().getEnd());
			Note start = new Note(weight++);
			Note end = new Note(weight++);
			statestack.push(end);
			statestack.push(start);
			
		}
		else if(c=='.'){
			//当读到小数点，说明这里是连接，将够着a.b这样的NFA
			NFA2 nfa = new NFA2();
			nfa.setEnd(statestack.pop().getWeight()-1);
			nfa.setGet(NFAlist.pop().getGet());
			nfa.setStart(statestack.pop().getWeight()-1);
			NFAlist.push(nfa);
			//z在构造新节点和新终态节点
			Note start = new Note(weight++);
			Note end =  new Note(weight++);
			statestack.push(start);
			statestack.push(end);
			
			
			
		}
		else if(c=='|'){
			NFA2 nfa1 = NFAlist.pop();
			NFA2 nfa2 = NFAlist.pop();//取出NFA堆栈里面的弧
			NFA2  nfa3 = new NFA2();//够着新的#弧
			NFA2  nfa4 = new NFA2();
			NFA2  nfa5 = new NFA2();
			NFA2  nfa6 = new NFA2();
			int weight1=statestack.pop().getWeight();//拿出状态堆栈里面 的节点——终态节点
			int weight2=statestack.pop().getWeight();//开始节点
			nfa3.setStart(nfa1.getEnd());
			nfa3.setGet('#');
			nfa3.setEnd(weight1);
			nfa4.setStart(nfa2.getEnd());
			nfa4.setGet('#');
			nfa4.setEnd(weight1);
			
			nfa5.setStart(weight2);
			nfa5.setGet('#');
			nfa5.setEnd(nfa1.getStart());
			
			nfa6.setStart(weight2);
			nfa6.setGet('#');
			nfa6.setEnd(nfa2.getStart());
			
			NFAlist.push(nfa1);//从新将弧放入堆栈中
			NFAlist.push(nfa2);
			NFAlist.push(nfa3);
			NFAlist.push(nfa4);
			NFAlist.push(nfa5);
			NFAlist.push(nfa6);
			
			//构造新的开始节点和终态节点，放入状态堆栈中
			Note start = new Note(weight++);
			Note end = new Note(weight++);
			statestack.push(start);
			statestack.push(end);
			
		}else if(c=='*'){
			//构造返回弧
			NFA2 nfa1 =  new NFA2();//S*返回弧
			NFA2 nfa2 = new NFA2();//状态堆栈中开始节点到终态节点的#弧
			NFA2 nfa3 = new NFA2();//新的开始节点到以前开始节点的#弧
			NFA2 nfa4 = new NFA2();//以前终态节点到新终态节点的#弧
			
			
			if(NFAlist.size()>=2){
				NFA2 s_end =  NFAlist.peekLast();//s的终态节点
				
				NFA2 s_start = NFAlist.peekFirst();//s的开始节点
				
				//构造从终态节点到开始节点的#弧
				nfa1.setStart(s_end.getEnd());
				nfa1.setGet('#');
				nfa1.setEnd(s_start.getStart());
				
				//构造从新的初始节点到新的终态节点的#弧
				int weight1=statestack.pop().getWeight();//拿出状态堆栈里面 的节点——终态节点
				int weight2=statestack.pop().getWeight();//开始节点
				nfa2.setStart(weight2);
				nfa2.setGet('#');
				nfa2.setEnd(weight1);
				
				nfa3.setStart(weight2);
				nfa3.setGet('#');
				nfa3.setEnd(s_start.getStart());
				
				nfa4.setStart(s_end.getEnd());
				nfa4.setGet('#');
				nfa4.setEnd(weight1);
				
				//从新将弧放入NFAlist中
			//s	NFAlist.push(s_start);
//				NFAlist.push(s_end);
				NFAlist.push(nfa1);
				NFAlist.push(nfa3);
				NFAlist.push(nfa4);
				NFAlist.push(nfa2); 
				
				//构造新的节点放入状态堆栈中
				Note start = new Note(weight++);
				Note end = new Note(weight++);
				statestack.push(start);
				statestack.push(end);
				
			}else{
					NFA2 s_end =  NFAlist.pop();//s的终态节点
			
			//NFA2 s_start = NFAlist.pop();//s的开始节点
			
			//构造从终态节点到开始节点的#弧
			nfa1.setStart(s_end.getEnd());
			nfa1.setGet('#');
			nfa1.setEnd(s_end.getStart());
			
			//构造从新的初始节点到新的终态节点的#弧
			int weight1=statestack.pop().getWeight();//拿出状态堆栈里面 的节点——终态节点
			int weight2=statestack.pop().getWeight();//开始节点
			nfa2.setStart(weight2);
			nfa2.setGet('#');
			nfa2.setEnd(weight1);
			
			nfa3.setStart(weight2);
			nfa3.setGet('#');
			nfa3.setEnd(s_end.getStart());
			
			nfa4.setStart(s_end.getEnd());
			nfa4.setGet('#');
			nfa4.setEnd(weight1);
			
			//从新将弧放入NFAlist中
		//s	NFAlist.push(s_start);
			NFAlist.push(s_end);
			NFAlist.push(nfa1);
			NFAlist.push(nfa3);
			NFAlist.push(nfa4);
			NFAlist.push(nfa2);
			
			//构造新的节点放入状态堆栈中
			Note start = new Note(weight++);
			Note end = new Note(weight++);
			statestack.push(start);
			statestack.push(end);
			}
			
		
		}
		
		
		if(rf.getCurrentChar()!=null){
			go();
		}
			
			
	}
	

}


class Note{//定义节点类
	private int weight;
	 Note(int weight){
		this.weight=weight;
	}

	public  int getWeight() {
		return weight;
	}

	public  void setWeight(int weight) {
		this .weight = weight;
	}
}