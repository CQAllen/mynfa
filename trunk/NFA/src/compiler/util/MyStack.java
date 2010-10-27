package compiler.util;

import java.util.Stack;

import compiler.nfa.entity.NFA;

public class MyStack {
	private static Stack<NFA> myStack ;
    static {
    	myStack = new Stack<NFA>();
    }
    
    public static Stack<NFA> getMyStack(){
    	return myStack;
    }
    
    
}
