package compiler.nfa;

import java.util.LinkedList;
import java.util.Stack;


import compiler.nfa.entity.NFA;
import compiler.nfa.io.ReadFile;
import compiler.util.MyLinkedList;

public class R2NFA {
	 Stack<Note> statestack;//状态栈，存放每次的开始节点和终态节点,
	LinkedList<NFA> NFAlist ;
	LinkedList<Character> charlist;
	ReadFile rf=new ReadFile();
	Character ch ;
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
		ch=rf.getNextChar();
		System.out.println("ch="+ch);
		if(ch!='.'&&ch!='|'&&ch!='*'){
			//charlist.add(c);
			NFA nfa = new NFA();//构造识别字符c的nfa
			
			nfa.setFrom(statestack.pop().getWeight());
			nfa.setReceive(ch);
			nfa.setTo(statestack.pop().getWeight());
			
			NFAlist.push(nfa);//一条弧入队列
			//构造新的开始节点和终态节点，将其放入 状态栈
			System.out.println("刚入队列的弧");
			System.out.print(NFAlist.peek().getFrom());
			System.out.print("__");
			System.out.print(NFAlist.peek().getReceive());
			System.out.print("__");
			System.out.println(NFAlist.peek().getTo());
			Note start = new Note(weight++);
			Note end = new Note(weight++);
			statestack.push(end);
			statestack.push(start);
			
		}
		else if(ch=='.'){
			//当读到小数点，说明这里是连接，将够着a.b这样的NFA
			NFA nfa = new NFA();
			Note oldstart =  statestack.pop();
			Note oldend = statestack.pop();
			System.out.println("oldstart="+oldstart.getWeight());
			NFA nfa2= NFAlist.pop();
			nfa.setTo(nfa2.getTo()-1);
			nfa.setReceive(nfa2.getReceive());
			nfa.setFrom(nfa2.getFrom()-1);
			NFAlist.push(nfa);
			//z在构造新节点和新终态节点
			Note start = new Note(oldstart.getWeight()-1);
			Note end =  new Note(oldend.getWeight()-1);
			System.out.println("start="+start.getWeight());
			statestack.push(end);
			statestack.push(start);
	
		}
		else if(ch=='|'){
			int start1=NFAlist.peek().getTo();
			int end1 =-1;
			int start2;
			int end2=-1;
			int i=0;
			for(;i<NFAlist.size()-1;i++){
				if(NFAlist.get(i).getReceive()!=NFAlist.get(i+1).getTo()){
					end1=NFAlist.get(i).getReceive();
					break;
				}
			}
			
			start2=NFAlist.get(i+1).getTo();
			for(int j=i+1;j<NFAlist.size();j++){
				if(j==NFAlist.size()-1){
					
					end2=NFAlist.get(j).getFrom();
					break;
				}else
				   if(NFAlist.get(j).getFrom()!=NFAlist.get(j+1).getTo()){
					  end2=NFAlist.get(j).getFrom();
					  break;
				}
			}
			
			System.out.println("end1="+end1);
			System.out.println("start1="+start1);
			System.out.println("end2="+end2);
			System.out.println("start2="+start2);
//			NFA2 nfa1 = NFAlist.pop();
//			NFA2 nfa2 = NFAlist.pop();//取出NFA堆栈里面的弧
			NFA  nfa3 = new NFA();//够着新的#弧
			NFA  nfa4 = new NFA();
			NFA  nfa5 = new NFA();
			NFA  nfa6 = new NFA();
			int weight1=statestack.pop().getWeight();//拿出状态堆栈里面 的节点——终态节点
			int weight2=statestack.pop().getWeight()  ;//开始节点
			nfa3.setFrom(start1);
			nfa3.setReceive('#');
			nfa3.setTo(weight1);
			nfa4.setFrom(start2);
			nfa4.setReceive('#');
			nfa4.setTo(weight1);
			
			nfa5.setFrom(weight2);
			nfa5.setReceive('#');
			nfa5.setTo(end1);
			
			nfa6.setFrom(weight2);
			nfa6.setReceive('#');
			nfa6.setTo(end2);
			
//			NFAlist.push(nfa1);//从新将弧放入堆栈中
//			NFAlist.push(nfa2);
			NFAlist.push(nfa3);
			NFAlist.push(nfa4);
			NFAlist.push(nfa5);
			NFAlist.push(nfa6);
			
			//构造新的开始节点和终态节点，放入状态堆栈中
			Note start = new Note(weight++);
			Note end = new Note(weight++);
			statestack.push(start);
			statestack.push(end);
			
		}else if(ch=='*'){
			//构造返回弧
			NFA nfa1 =  new NFA();//S*返回弧
			NFA nfa2 = new NFA();//状态堆栈中开始节点到终态节点的#弧
			NFA nfa3 = new NFA();//新的开始节点到以前开始节点的#弧
			NFA nfa4 = new NFA();//以前终态节点到新终态节点的#弧
			
			
			if(NFAlist.size()>=2){
				NFA s_end =  NFAlist.peekLast();//s的终态节点
				
				NFA s_start = NFAlist.peekFirst();//s的开始节点
				
				//构造从终态节点到开始节点的#弧
				nfa1.setFrom(s_end.getTo());
				nfa1.setReceive('#');
				nfa1.setTo(s_start.getFrom());
				
				//构造从新的初始节点到新的终态节点的#弧
				int weight1=statestack.pop().getWeight();//拿出状态堆栈里面 的节点——终态节点
				int weight2=statestack.pop().getWeight();//开始节点
				nfa2.setFrom(weight2);
				nfa2.setReceive('#');
				nfa2.setTo(weight1);
				
				nfa3.setFrom(weight2);
				nfa3.setReceive('#');
				nfa3.setTo(s_start.getFrom());
				
				nfa4.setFrom(s_end.getTo());
				nfa4.setReceive('#');
				nfa4.setTo(weight1);
				
				//从新将弧放入NFAlist中
			//s	NFAlist.push(s_start);
//				NFAlist.push(s_end);
				NFAlist.push(nfa1);
//				NFAlist.push(nfa3);
				NFAlist.addFirst(nfa3);
				NFAlist.push(nfa2); 
				NFAlist.push(nfa4);
				//构造新的节点放入状态堆栈中
				Note start = new Note(weight++);
				Note end = new Note(weight++);
				statestack.push(start);
				statestack.push(end);
				
			}else{
					NFA s_end =  NFAlist.peek();//s的终态节点
			
			//NFA2 s_start = NFAlist.pop();//s的开始节点
			
			//构造从终态节点到开始节点的#弧
			nfa1.setFrom(s_end.getTo());
			nfa1.setReceive('#');
			nfa1.setTo(s_end.getFrom());
			
			//构造从新的初始节点到新的终态节点的#弧
			int weight1=statestack.pop().getWeight();//拿出状态堆栈里面 的节点——终态节点
			int weight2=statestack.pop().getWeight();//开始节点
			nfa2.setFrom(weight2);
			nfa2.setReceive('#');
			nfa2.setTo(weight1);
			
			nfa3.setFrom(weight2);
			nfa3.setReceive('#');
			nfa3.setTo(s_end.getFrom());
			
			nfa4.setFrom(s_end.getTo());
			nfa4.setReceive('#');
			nfa4.setTo(weight1);
			
			//从新将弧放入NFAlist中
		//s	NFAlist.push(s_start);
//			NFAlist.push(s_end);
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