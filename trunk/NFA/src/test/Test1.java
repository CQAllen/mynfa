package test;

import compiler.nfa.R2NFA;
import compiler.nfa.entity.NFA;
import compiler.nfa.io.ReadFile;
import compiler.util.MyLinkedList;

public class Test1 {
	public static void main(String args[]){
		ReadFile rf = new ReadFile();
		String s="(a|b)*.(a.a|b.b)#";
//		String s="a.b.c|e.g|r.h#";
//		String s1="a|a.b#";
		rf.setFile(s);
		String file=rf.isCorrect();
		System.out.println(file);
		
		new R2NFA(file).go();
		NFA nfa;
		System.out.print("F");
		System.out.print( " ___ " );
		System.out.print("G");
		System.out.print( " ___ " );
		System.out.println("E");
		for(int i=0;i<MyLinkedList.getNFAlist().size();i++){
			nfa=MyLinkedList.getNFAlist().get(i);
			
			System.out.print(nfa.getFrom());
			System.out.print( " --- " );
			System.out.print(nfa.getReceive());
			System.out.print( " --- " );
			System.out.println(nfa.getTo());
		}
	}

}
