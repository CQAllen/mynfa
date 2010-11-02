package compiler.nfa;

import java.util.LinkedList;
import java.util.Stack;


import compiler.nfa.entity.NFA2;
import compiler.nfa.io.ReadFile;
import compiler.util.MyLinkedList;

public class R2NFA {
	 Stack<Note> statestack;//״̬ջ�����ÿ�εĿ�ʼ�ڵ����̬�ڵ�
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
			NFA2 nfa = new NFA2();//����ʶ���ַ�c��nfa
			System.out.println("................."+statestack.size());
			nfa.setStart(statestack.pop().getWeight());
			nfa.setGet(c);
			nfa.setEnd(statestack.pop().getWeight());
			System.out.println("................."+statestack.size());
			NFAlist.push(nfa);//һ���������
			//�����µĿ�ʼ�ڵ����̬�ڵ㣬������� ״̬ջ
			System.out.println("������еĻ�");
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
			//������С���㣬˵�����������ӣ�������a.b������NFA
			NFA2 nfa = new NFA2();
			nfa.setEnd(statestack.pop().getWeight()-1);
			nfa.setGet(NFAlist.pop().getGet());
			nfa.setStart(statestack.pop().getWeight()-1);
			NFAlist.push(nfa);
			//z�ڹ����½ڵ������̬�ڵ�
			Note start = new Note(weight++);
			Note end =  new Note(weight++);
			statestack.push(start);
			statestack.push(end);
			
			
			
		}
		else if(c=='|'){
			NFA2 nfa1 = NFAlist.pop();
			NFA2 nfa2 = NFAlist.pop();//ȡ��NFA��ջ����Ļ�
			NFA2  nfa3 = new NFA2();//�����µ�#��
			NFA2  nfa4 = new NFA2();
			NFA2  nfa5 = new NFA2();
			NFA2  nfa6 = new NFA2();
			int weight1=statestack.pop().getWeight();//�ó�״̬��ջ���� �Ľڵ㡪����̬�ڵ�
			int weight2=statestack.pop().getWeight();//��ʼ�ڵ�
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
			
			NFAlist.push(nfa1);//���½��������ջ��
			NFAlist.push(nfa2);
			NFAlist.push(nfa3);
			NFAlist.push(nfa4);
			NFAlist.push(nfa5);
			NFAlist.push(nfa6);
			
			//�����µĿ�ʼ�ڵ����̬�ڵ㣬����״̬��ջ��
			Note start = new Note(weight++);
			Note end = new Note(weight++);
			statestack.push(start);
			statestack.push(end);
			
		}else if(c=='*'){
			//���췵�ػ�
			NFA2 nfa1 =  new NFA2();//S*���ػ�
			NFA2 nfa2 = new NFA2();//״̬��ջ�п�ʼ�ڵ㵽��̬�ڵ��#��
			NFA2 nfa3 = new NFA2();//�µĿ�ʼ�ڵ㵽��ǰ��ʼ�ڵ��#��
			NFA2 nfa4 = new NFA2();//��ǰ��̬�ڵ㵽����̬�ڵ��#��
			
			
			if(NFAlist.size()>=2){
				NFA2 s_end =  NFAlist.peekLast();//s����̬�ڵ�
				
				NFA2 s_start = NFAlist.peekFirst();//s�Ŀ�ʼ�ڵ�
				
				//�������̬�ڵ㵽��ʼ�ڵ��#��
				nfa1.setStart(s_end.getEnd());
				nfa1.setGet('#');
				nfa1.setEnd(s_start.getStart());
				
				//������µĳ�ʼ�ڵ㵽�µ���̬�ڵ��#��
				int weight1=statestack.pop().getWeight();//�ó�״̬��ջ���� �Ľڵ㡪����̬�ڵ�
				int weight2=statestack.pop().getWeight();//��ʼ�ڵ�
				nfa2.setStart(weight2);
				nfa2.setGet('#');
				nfa2.setEnd(weight1);
				
				nfa3.setStart(weight2);
				nfa3.setGet('#');
				nfa3.setEnd(s_start.getStart());
				
				nfa4.setStart(s_end.getEnd());
				nfa4.setGet('#');
				nfa4.setEnd(weight1);
				
				//���½�������NFAlist��
			//s	NFAlist.push(s_start);
//				NFAlist.push(s_end);
				NFAlist.push(nfa1);
				NFAlist.push(nfa3);
				NFAlist.push(nfa4);
				NFAlist.push(nfa2); 
				
				//�����µĽڵ����״̬��ջ��
				Note start = new Note(weight++);
				Note end = new Note(weight++);
				statestack.push(start);
				statestack.push(end);
				
			}else{
					NFA2 s_end =  NFAlist.pop();//s����̬�ڵ�
			
			//NFA2 s_start = NFAlist.pop();//s�Ŀ�ʼ�ڵ�
			
			//�������̬�ڵ㵽��ʼ�ڵ��#��
			nfa1.setStart(s_end.getEnd());
			nfa1.setGet('#');
			nfa1.setEnd(s_end.getStart());
			
			//������µĳ�ʼ�ڵ㵽�µ���̬�ڵ��#��
			int weight1=statestack.pop().getWeight();//�ó�״̬��ջ���� �Ľڵ㡪����̬�ڵ�
			int weight2=statestack.pop().getWeight();//��ʼ�ڵ�
			nfa2.setStart(weight2);
			nfa2.setGet('#');
			nfa2.setEnd(weight1);
			
			nfa3.setStart(weight2);
			nfa3.setGet('#');
			nfa3.setEnd(s_end.getStart());
			
			nfa4.setStart(s_end.getEnd());
			nfa4.setGet('#');
			nfa4.setEnd(weight1);
			
			//���½�������NFAlist��
		//s	NFAlist.push(s_start);
			NFAlist.push(s_end);
			NFAlist.push(nfa1);
			NFAlist.push(nfa3);
			NFAlist.push(nfa4);
			NFAlist.push(nfa2);
			
			//�����µĽڵ����״̬��ջ��
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


class Note{//����ڵ���
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