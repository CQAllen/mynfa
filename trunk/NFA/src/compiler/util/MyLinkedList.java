package compiler.util;

import java.util.LinkedList;

import compiler.nfa.entity.NFA;



@SuppressWarnings("serial")
public class MyLinkedList extends LinkedList<NFA>{
	private static MyLinkedList NFAlist ;
	static{
		NFAlist=new MyLinkedList();
	}
	//dfdf
   public  static MyLinkedList getNFAlist(){
	   return NFAlist;
   }
}
