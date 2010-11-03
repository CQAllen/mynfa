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
			NFA nfa = new NFA(State.weight++,ch,State.weight++);//����ʶ���ַ�c��nfa
			NFAlist.push(nfa);//һ���������
			State.statestack.push(nfa);//״̬��ջ��ҲҪ����
			//�����µĿ�ʼ�ڵ����̬�ڵ㣬������� ״̬ջ
			System.out.println("������еĻ�");
			System.out.print(NFAlist.peek().getFrom());
			System.out.print("__");
			System.out.print(NFAlist.peek().getReceive());
			System.out.print("__");
			System.out.println(NFAlist.peek().getTo());
			
			
		}
		else if(ch=='.'){
			//������С���㣬˵�����������ӣ�������a.b������NFA
			//״̬վ������top����ջ
			NFA nfa2=State.statestack.pop();
			NFA nfa1=State.statestack.pop();
			
			//�����������㣬���ҽ��������״̬��ջ
			State.statestack.push(nfa1.connect(nfa2));
	
		}
		else if(ch=='|'){
		  //״̬��ջ�е�����Ԫ�س�ջ
			NFA nfa2=State.statestack.pop();
			NFA nfa1=State.statestack.pop();
			State.statestack.push(nfa1.or(nfa2));
			
		}else if(ch=='*'){
			//״̬��ջ�еĶ�ջ��Ԫ�س�ջ
			NFA nfa= State.statestack.pop();
			State.statestack.push(nfa.bibao());
			
		
		}
		
		
		if(rf.getCurrentChar()!=null){
			go();
		}
			
			
	}
	

}

