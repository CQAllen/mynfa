package test;

import compiler.nfa.RtoNfa;
import compiler.nfa.entity.NFA;
import compiler.nfa.io.ReadFile;
import compiler.util.MyLinkedList;

public class Test1 {
	public static void main(String args[]){
		String s="(b.a)*#";
//		String s="a.b.c|e.g|r.h#";
//		String s1="a|a.b#";
		ReadFile.getReadFile().setFile(s);
		String file=ReadFile.isCorrect();
		System.out.println(file);
		ReadFile.row=0;
		new RtoNfa(file).go();
		NFA nfa;
		System.out.println("存放NFA的队列长度:"+MyLinkedList.getNFAlist().size());
		System.out.println(MyLinkedList.getNFAlist().getFirst().toString());
//		for(int i=0;i<MyLinkedList.getNFAlist().size();i++){
//			nfa=MyLinkedList.getNFAlist().getFirst();
//			for(int j=0;j<=nfa.getFrom().length();j++){
//				System.out.print("from :"+nfa.getFrom().charAt(j)+"| receive :"+nfa.getReceive().charAt(j)+"| to:"+nfa.getTo().charAt(j));
//			}
//		}
	}

}
