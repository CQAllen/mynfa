package compiler.DFA.entity;

import java.util.LinkedList;
import java.util.Random;

/**
 * 
 * @author Allen
 * 
 */

public class DFA {
	private LinkedList<Integer> States = new LinkedList<Integer>();
	private Character DFA_Name = 'S';
	private boolean Start = false;
	private boolean End = false;

	// private int size=States.size();

	public DFA() {

	}

	public DFA(Character name) {
		DFA_Name = name;
	}

	public LinkedList<Integer> getStates() {
		if (States == null)
			States = new LinkedList<Integer>();
		return States;
	}

	public void setStates(LinkedList<Integer> states) {
		this.States = states;
	}

	public Character getDFA_Name() {
		return DFA_Name;
	}

	public void setDFA_Name(Character dFAName) {
		DFA_Name = dFAName;
	}

	public boolean isStart() {
		return Start;
	}

	public void setIsStart(boolean Start) {
		this.Start = Start;
	}

	public boolean isEnd() {
		return End;
	}

	public void setIsEnd(boolean End) {
		this.End = End;
	}

	public int equals(DFA dfa1, DFA dfa2) {
		int index;
		if (dfa1.States.size() == dfa2.States.size()) {
			for (index = 0; index < dfa1.States.size(); index++) {
				if (dfa1.States.get(index) == dfa2.States.get(index)) {
					continue;
				} else
					return -1;
			}
			return index;
		} else
			return -1;
	}

	@Override
	public int hashCode() {
		int a = 13;
		Random ran = new Random();
		a *= (States.size() + 7) * ran.nextInt();
		if (Start) {
			a *= 17;
		} else {
			a *= 19;
		}
		return a;
	}
}
