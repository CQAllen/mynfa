package compiler.nfa;

import java.util.LinkedList;
import java.util.Stack;


import compiler.nfa.entity.NFA;
import compiler.nfa.io.ReadFile;
import compiler.util.MyLinkedList;

public class R2NFA {
	 Stack<Note> statestack;//״̬ջ�����ÿ�εĿ�ʼ�ڵ����̬�ڵ�,
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
			NFA nfa = new NFA();//����ʶ���ַ�c��nfa
			
			nfa.setFrom(statestack.pop().getWeight());
			nfa.setReceive(ch);
			nfa.setTo(statestack.pop().getWeight());
			
			NFAlist.push(nfa);//һ���������
			//�����µĿ�ʼ�ڵ����̬�ڵ㣬������� ״̬ջ
			System.out.println("������еĻ�");
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
			//������С���㣬˵�����������ӣ�������a.b������NFA
			NFA nfa = new NFA();
			Note oldstart =  statestack.pop();
			Note oldend = statestack.pop();
			System.out.println("oldstart="+oldstart.getWeight());
			NFA nfa2= NFAlist.pop();
			nfa.setTo(nfa2.getTo()-1);
			nfa.setReceive(nfa2.getReceive());
			nfa.setFrom(nfa2.getFrom()-1);
			NFAlist.push(nfa);
			//z�ڹ����½ڵ������̬�ڵ�
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
//			NFA2 nfa2 = NFAlist.pop();//ȡ��NFA��ջ����Ļ�
			NFA  nfa3 = new NFA();//�����µ�#��
			NFA  nfa4 = new NFA();
			NFA  nfa5 = new NFA();
			NFA  nfa6 = new NFA();
			int weight1=statestack.pop().getWeight();//�ó�״̬��ջ���� �Ľڵ㡪����̬�ڵ�
			int weight2=statestack.pop().getWeight()  ;//��ʼ�ڵ�
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
			
//			NFAlist.push(nfa1);//���½��������ջ��
//			NFAlist.push(nfa2);
			NFAlist.push(nfa3);
			NFAlist.push(nfa4);
			NFAlist.push(nfa5);
			NFAlist.push(nfa6);
			
			//�����µĿ�ʼ�ڵ����̬�ڵ㣬����״̬��ջ��
			Note start = new Note(weight++);
			Note end = new Note(weight++);
			statestack.push(start);
			statestack.push(end);
			
		}else if(ch=='*'){
			//���췵�ػ�
			NFA nfa1 =  new NFA();//S*���ػ�
			NFA nfa2 = new NFA();//״̬��ջ�п�ʼ�ڵ㵽��̬�ڵ��#��
			NFA nfa3 = new NFA();//�µĿ�ʼ�ڵ㵽��ǰ��ʼ�ڵ��#��
			NFA nfa4 = new NFA();//��ǰ��̬�ڵ㵽����̬�ڵ��#��
			
			
			if(NFAlist.size()>=2){
				NFA s_end =  NFAlist.peekLast();//s����̬�ڵ�
				
				NFA s_start = NFAlist.peekFirst();//s�Ŀ�ʼ�ڵ�
				
				//�������̬�ڵ㵽��ʼ�ڵ��#��
				nfa1.setFrom(s_end.getTo());
				nfa1.setReceive('#');
				nfa1.setTo(s_start.getFrom());
				
				//������µĳ�ʼ�ڵ㵽�µ���̬�ڵ��#��
				int weight1=statestack.pop().getWeight();//�ó�״̬��ջ���� �Ľڵ㡪����̬�ڵ�
				int weight2=statestack.pop().getWeight();//��ʼ�ڵ�
				nfa2.setFrom(weight2);
				nfa2.setReceive('#');
				nfa2.setTo(weight1);
				
				nfa3.setFrom(weight2);
				nfa3.setReceive('#');
				nfa3.setTo(s_start.getFrom());
				
				nfa4.setFrom(s_end.getTo());
				nfa4.setReceive('#');
				nfa4.setTo(weight1);
				
				//���½�������NFAlist��
			//s	NFAlist.push(s_start);
//				NFAlist.push(s_end);
				NFAlist.push(nfa1);
//				NFAlist.push(nfa3);
				NFAlist.addFirst(nfa3);
				NFAlist.push(nfa2); 
				NFAlist.push(nfa4);
				//�����µĽڵ����״̬��ջ��
				Note start = new Note(weight++);
				Note end = new Note(weight++);
				statestack.push(start);
				statestack.push(end);
				
			}else{
					NFA s_end =  NFAlist.peek();//s����̬�ڵ�
			
			//NFA2 s_start = NFAlist.pop();//s�Ŀ�ʼ�ڵ�
			
			//�������̬�ڵ㵽��ʼ�ڵ��#��
			nfa1.setFrom(s_end.getTo());
			nfa1.setReceive('#');
			nfa1.setTo(s_end.getFrom());
			
			//������µĳ�ʼ�ڵ㵽�µ���̬�ڵ��#��
			int weight1=statestack.pop().getWeight();//�ó�״̬��ջ���� �Ľڵ㡪����̬�ڵ�
			int weight2=statestack.pop().getWeight();//��ʼ�ڵ�
			nfa2.setFrom(weight2);
			nfa2.setReceive('#');
			nfa2.setTo(weight1);
			
			nfa3.setFrom(weight2);
			nfa3.setReceive('#');
			nfa3.setTo(s_end.getFrom());
			
			nfa4.setFrom(s_end.getTo());
			nfa4.setReceive('#');
			nfa4.setTo(weight1);
			
			//���½�������NFAlist��
		//s	NFAlist.push(s_start);
//			NFAlist.push(s_end);
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