package test;

import compiler.DFA.entity.DFA;
import compiler.nfa.NFAtoDFA;
import compiler.nfa.entity.NFA;
import compiler.util.MyLinkedList;

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
		Cur = new NFA();
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
		System.out.println(List.size());
		NFAtoDFA NtD=new NFAtoDFA();
		DFA Start_dfa=new DFA();
		NtD.setList(List);
		Start_dfa=NtD.Clourse((NFA) List.getFirst(),Start_dfa);
		System.out.println(Start_dfa.getStates().toString());
//		NtD.Change(cur, ch);
		
		
	}
}
