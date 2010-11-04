package compiler.nfa.entity;


import compiler.nfa.util.State;
import compiler.util.MyLinkedList;

public class NFA {
	private int from;
	private char receive;
	private int to;

	public NFA(int from,char receive,int to){
		this.from=from;
		this.receive=receive;
		this.to=to;
	}
	public int getFrom() {
		return from;
	}
	public void setFrom(int from) {
		this.from = from;
	}
	public char getReceive() {
		return receive;
	}
	public void setReceive(char receive) {
		this.receive = receive;
	}
	public int getTo() {
		return to;
	}
	public void setTo(int to) {
		this.to = to;
	}
	
	public NFA connect(NFA nfa2){
		/*
		 * 且连接
		 */
//		NFA end= State.statestack.pop();
//		NFA start = State.statestack.pop();//取出状态堆栈中的两个元素进行连接运算
		
		NFA nfa=new NFA(to,'#',nfa2.getFrom());
		MyLinkedList.getNFAlist().push(nfa);
		//将刚才进行连接的两个nfa和成一个整体
		NFA all=new NFA(from,'-',nfa2.getTo());
		
		
		return all;//将得到的整体返回
	}
	
	public NFA or(NFA n){
		/*
		 * 或连接
		 */
		//构造两个终太节点
		Note start = new Note(State.weight++);
		Note end = new Note(State.weight++);
		//构造4个空弧转换
		NFA nfa1=new NFA(to,'#',end.getWeight());
		NFA nfa2=new NFA(start.getWeight(),'#',from);
		NFA nfa3=new NFA(n.getTo(),'#',end.getWeight());
		NFA nfa4=new NFA(start.getWeight(),'#',n.getFrom());
		
		//将新弧放到堆栈中
		MyLinkedList.getNFAlist().push(nfa1);
		MyLinkedList.getNFAlist().push(nfa2);
		MyLinkedList.getNFAlist().push(nfa3);
		MyLinkedList.getNFAlist().push(nfa4);
		
		//再构造整体
		NFA all = new NFA(start.getWeight(),'-',end.getWeight());
		return all;
	}
	
	
	public  NFA bibao(){
		/*
		 * 闭包连接
		 */
		//先构造两 个新的开始节点个终态节点
		Note start = new Note(State.weight++);
		Note end = new Note(State.weight++);
	
		//构造top的自身返回弧
		NFA nfa1=new NFA(to,'#',from);
		//构造新节点到当前弧的开始
		NFA nfa2= new NFA(start.getWeight(),'#',from);
		NFA nfa3= new NFA(to,'#',end.getWeight());
		NFA nfa4= new NFA(start.getWeight(),'#',end.getWeight());
		//将新的弧放入堆栈中
		MyLinkedList.getNFAlist().push(nfa1);
		MyLinkedList.getNFAlist().push(nfa2);
		MyLinkedList.getNFAlist().push(nfa3);
		MyLinkedList.getNFAlist().push(nfa4);
		
		//构造整体
		NFA all= new NFA(start.getWeight(),'-',end.getWeight());
		return all;
	}
	

}
