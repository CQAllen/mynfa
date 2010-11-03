package compiler.nfa.util;

import java.util.Stack;

import compiler.nfa.entity.NFA;

public class State {
	public static int weight=0;
	public static Stack<NFA>  statestack= new Stack<NFA>();
	int from;
	int to;

}
