package compiler.nfa;

import compiler.DFA.entity.DFA;
import compiler.nfa.entity.NFA;
import compiler.util.MyLinkedList;

/**
 * 
 * @author Allen
 * 
 */

public class NFAtoDFA {
	// static NFA Cur = new NFA();
	private static MyLinkedList List;
	static int i;
	static int j;

	public static void setList(MyLinkedList list) {
		List = list;
	}

	public static MyLinkedList getList() {
		return List;
	}

	// public static void run(MyLinkedList List) {
	// // Cur=(NFA) list.getFirst();
	// // Cur.setFrom(new StringBuffer().append("0"));
	// // Cur.setReceive(new StringBuffer().append("#"));
	// // Cur.setTo(new StringBuffer().append("1"));
	// // List.add(Cur);
	// // NFA Cur1 = new NFA();
	// // Cur1.setFrom(new StringBuffer().append("1"));
	// // Cur1.setReceive(new StringBuffer().append("#"));
	// // Cur1.setTo(new StringBuffer().append("2"));
	// // List.add(Cur1);
	// Clourse((NFA) List.getFirst());
	// }

	public static DFA Clourse(NFA cur, DFA dfa) {// ¦Å_Clourse±Õ°ü
		// TODO Auto-generated method stub
		boolean Continue = false;
		if (CheckDFA(dfa, cur.getFrom().charAt(0)))
			dfa.getStates().append(cur.getFrom().charAt(0));
		for (int index = 0; index < cur.getReceive().length(); index++)
			if (cur.getReceive().charAt(index) == '#') {
				System.out.println(cur.getFrom().charAt(index) + " recieve "
						+ cur.getReceive().charAt(index) + " to "
						+ cur.getTo().charAt(index));
				dfa.getStates().append(cur.getTo().charAt(index));
				Continue = search(cur.getTo().charAt(index), '#');
				if (Continue)
					Clourse(getList().get(i), dfa);
				else
					return dfa;
			} else {
				continue;
			}
		return dfa;

	}

	private static boolean CheckDFA(DFA dfa, Character ch) {
		// TODO Auto-generated method stub
		for (int k = 0; k < dfa.getStates().length(); k++) {
			if (dfa.getStates().charAt(k) == ch)
				return false;
		}
		return true;
	}

	public static void Change(NFA cur, Character ch,DFA dfa) {// X»¡×ª»¯
		// TODO Auto-generated method stub
		// boolean Continue = false;
		dfa.getStates().append(cur.getFrom().charAt(0));
		for (int index = 0; index < cur.getReceive().length(); index++)
			if (cur.getReceive().charAt(index) == ch) {
				System.out.println(cur.getFrom().charAt(index) + " recieve "
						+ cur.getReceive().charAt(index) + " to "
						+ cur.getTo().charAt(index));
				dfa.getStates().append(cur.getTo().charAt(index));
				// Continue = search(cur.getTo().charAt(index));
				// if (Continue)
				// Clourse(getList().get(i));
				// else
				// return;
			} else {
				continue;
			}

	}

	private static boolean search(Character ch1, Character ch2) {
		// TODO Auto-generated method stub
		for (i = 0; i < getList().size(); i++) {
			for (j = 0; j < getList().get(i).getFrom().length(); j++) {
				if (getList().get(i).getFrom().charAt(j) == ch1
						&& getList().get(i).getReceive().charAt(j) == ch2)
					return true;

			}
		}
		return false;
	}
}
