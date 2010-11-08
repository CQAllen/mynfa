package test;

import java.util.LinkedList;
import compiler.DFA.entity.DFA;
import compiler.nfa.NFAtoDFA;
import compiler.nfa.entity.NFA;
import compiler.util.MyLinkedList;
/**
 * 
 * @author Allen
 *
 */

public class Test2 {
	static NFA Cur;
	static MyLinkedList List=new MyLinkedList();
	public static void main(String args[]){
		new Test2().test();
	}

	private void test() {
		// TODO Auto-generated method stub
		NFA Cur=new NFA();
		Cur.setFrom(0);
		Cur.setReceive('#');
		Cur.setTo(1);
		List.add(Cur);
		Cur = new NFA();
		Cur.setFrom(1);
		Cur.setReceive('#');
		Cur.setTo(2);
		List.add(Cur);
		Cur = new NFA();
		Cur.setFrom(1);
		Cur.setReceive('a');
		Cur.setTo(1);
		List.add(Cur);
		Cur = new NFA();
		Cur.setFrom(1);
		Cur.setReceive('b');
		Cur.setTo(1);
		List.add(Cur);
		Cur = new NFA();
		Cur = new NFA();
		Cur.setFrom(2);
		Cur.setReceive('a');
		Cur.setTo(3);
		List.add(Cur);
		Cur = new NFA();
		Cur.setFrom(2);
		Cur.setReceive('b');
		Cur.setTo(4);
		List.add(Cur);
		Cur = new NFA();
		Cur.setFrom(3);
		Cur.setReceive('a');
		Cur.setTo(5);
		List.add(Cur);
		Cur = new NFA();
		Cur.setFrom(4);
		Cur.setReceive('b');
		Cur.setTo(5);
		List.add(Cur);
		Cur = new NFA();
		Cur.setFrom(5);
		Cur.setReceive('#');
		Cur.setTo(6);
		List.add(Cur);
		Cur = new NFA();
		Cur.setFrom(6);
		Cur.setReceive('#');
		Cur.setTo(-1);
		List.add(Cur);
		Cur = new NFA();
		Cur.setFrom(6);
		Cur.setReceive('a');
		Cur.setTo(6);
		List.add(Cur);
		Cur = new NFA();
		Cur.setFrom(6);
		Cur.setReceive('b');
		Cur.setTo(6);
		List.add(Cur);
		NFAtoDFA.run(List);
//		System.out.println(List.size());
//		LinkedList<Character> Start_dfa_list=new LinkedList<Character>();
//		NFAtoDFA.setList(List);
//		Start_dfa_list=NFAtoDFA.Clourse((NFA) List.getFirst(),Start_dfa_list);
//		for(int index=0;index<Start_dfa_list.size();index++)
//		System.out.print(Start_dfa_list.get(index).toString());
		
		
	}
}
