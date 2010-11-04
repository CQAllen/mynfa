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
		 * ������
		 */
//		NFA end= State.statestack.pop();
//		NFA start = State.statestack.pop();//ȡ��״̬��ջ�е�����Ԫ�ؽ�����������
		
		NFA nfa=new NFA(to,'#',nfa2.getFrom());
		MyLinkedList.getNFAlist().push(nfa);
		//���ղŽ������ӵ�����nfa�ͳ�һ������
		NFA all=new NFA(from,'-',nfa2.getTo());
		
		
		return all;//���õ������巵��
	}
	
	public NFA or(NFA n){
		/*
		 * ������
		 */
		//����������̫�ڵ�
		Note start = new Note(State.weight++);
		Note end = new Note(State.weight++);
		//����4���ջ�ת��
		NFA nfa1=new NFA(to,'#',end.getWeight());
		NFA nfa2=new NFA(start.getWeight(),'#',from);
		NFA nfa3=new NFA(n.getTo(),'#',end.getWeight());
		NFA nfa4=new NFA(start.getWeight(),'#',n.getFrom());
		
		//���»��ŵ���ջ��
		MyLinkedList.getNFAlist().push(nfa1);
		MyLinkedList.getNFAlist().push(nfa2);
		MyLinkedList.getNFAlist().push(nfa3);
		MyLinkedList.getNFAlist().push(nfa4);
		
		//�ٹ�������
		NFA all = new NFA(start.getWeight(),'-',end.getWeight());
		return all;
	}
	
	
	public  NFA bibao(){
		/*
		 * �հ�����
		 */
		//�ȹ����� ���µĿ�ʼ�ڵ����̬�ڵ�
		Note start = new Note(State.weight++);
		Note end = new Note(State.weight++);
	
		//����top�������ػ�
		NFA nfa1=new NFA(to,'#',from);
		//�����½ڵ㵽��ǰ���Ŀ�ʼ
		NFA nfa2= new NFA(start.getWeight(),'#',from);
		NFA nfa3= new NFA(to,'#',end.getWeight());
		NFA nfa4= new NFA(start.getWeight(),'#',end.getWeight());
		//���µĻ������ջ��
		MyLinkedList.getNFAlist().push(nfa1);
		MyLinkedList.getNFAlist().push(nfa2);
		MyLinkedList.getNFAlist().push(nfa3);
		MyLinkedList.getNFAlist().push(nfa4);
		
		//��������
		NFA all= new NFA(start.getWeight(),'-',end.getWeight());
		return all;
	}
	

}
