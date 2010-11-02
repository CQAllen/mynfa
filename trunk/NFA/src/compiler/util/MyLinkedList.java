package compiler.util;

import java.util.LinkedList;


import compiler.nfa.entity.NFA2;




@SuppressWarnings("serial")
public class MyLinkedList extends LinkedList<NFA2>{
	private static MyLinkedList NFAlist ;
	static{
		NFAlist=new MyLinkedList();
	}
	
   public  static MyLinkedList getNFAlist(){
	   return NFAlist;
   }
}
