package compiler.nfa.util;

import java.util.Stack;

import compiler.nfa.entity.NFA;

public class State {
	public static int weight=0;
	public static int start=0;//开始节点
	public static int end=0;//终态节点
	public static Stack<NFA>  statestack= new Stack<NFA>();
	public static void clear(){
		weight=0;
		start=0;
		end=0;
		statestack.clear();
	}

}
