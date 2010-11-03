package test;

import java.util.LinkedList;
import compiler.DFA.entity.DFA;
import compiler.nfa.NFAtoDFA;
import compiler.nfa.entity.NFA;
import compiler.nfa.entity.NFA2;
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
		Cur.setFrom(new StringBuffer().append("0"));
		Cur.setReceive(new StringBuffer().append("#"));
		Cur.setTo(new StringBuffer().append("1"));
		List.add(Cur);
		Cur = new NFA2();
		Cur.setFrom(new StringBuffer().append("111"));
		Cur.setReceive(new StringBuffer().append("#ab"));
		Cur.setTo(new StringBuffer().append("211"));
		List.add(Cur);
		Cur = new NFA();
		Cur.setFrom(new StringBuffer().append("22"));
		Cur.setReceive(new StringBuffer().append("ab"));
		Cur.setTo(new StringBuffer().append("34"));
		List.add(Cur);
		Cur = new NFA();
		Cur.setFrom(new StringBuffer().append("3"));
		Cur.setReceive(new StringBuffer().append("a"));
		Cur.setTo(new StringBuffer().append("5"));
		List.add(Cur);
		Cur = new NFA();
		Cur.setFrom(new StringBuffer().append("4"));
		Cur.setReceive(new StringBuffer().append("b"));
		Cur.setTo(new StringBuffer().append("5"));
		List.add(Cur);
		Cur = new NFA();
		Cur.setFrom(new StringBuffer().append("5"));
		Cur.setReceive(new StringBuffer().append("#"));
		Cur.setTo(new StringBuffer().append("6"));
		List.add(Cur);
		Cur.setFrom(new StringBuffer().append("666"));
		Cur.setReceive(new StringBuffer().append("#ab"));
		Cur.setTo(new StringBuffer().append("#66"));
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
