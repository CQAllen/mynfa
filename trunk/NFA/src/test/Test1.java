package test;

import compiler.nfa.io.ReadFile;

public class Test1 {
	public static void main(String args[]){
		String s="(b|a)*((a.a)|(b.b))(a|b)*#";
//		String s="a.b.c|e.g|r.h#";
//		String s1="a|a.b#";
		ReadFile.getReadFile().setFile(s);
		System.out.println(ReadFile.isCorrect());
	}

}
