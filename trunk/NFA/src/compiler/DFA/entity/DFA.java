package compiler.DFA.entity;

import java.util.LinkedList;
import java.util.Random;

/**
 * 
 * @author Allen
 * 
 */

public class DFA {
	private LinkedList<Integer> States;
	private Character DFA_Name;
	private boolean Start;
	private boolean End;

	// private int size=States.size();

	public DFA() {
		States = new LinkedList<Integer>();
		DFA_Name = 'S';
		Start = false;
		End = false;
	}

	public DFA(Character name) {
		States = new LinkedList<Integer>();
		DFA_Name = name;
		Start = false;
		End = false;
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

	public void setStart(boolean Start) {
		this.Start = Start;
	}

	public void check() {
		checkStart();
		checkEnd();
	}

	public void checkStart() {
		for (int index = 0; index < this.States.size(); index++)
			if (this.States.get(index) == 0)
				this.Start = true;
	}

	public void checkEnd() {
		for (int index = 0; index < this.States.size(); index++)
			if (this.States.get(index) == -1)
				this.End = true;

	}

	public boolean isEnd() {
		return End;
	}

	public void setEnd(boolean End) {
		this.End = End;
	}

	public boolean equals(DFA dfa1, DFA dfa2) {
		int index;
		if (dfa1.States.size() == dfa2.States.size()) {
			for (index = 0; index < dfa1.States.size(); index++) {
				if (dfa1.States.get(index) == dfa2.States.get(index)) {
					continue;
				} else
					return false;
			}
			return true;
		} else
			return false;
	}

	public static boolean compare(DFA dfa1, DFA dfa2) {
		if (!dfa1.getDFA_Name().equals(dfa2.getDFA_Name())) {
			return false;
		}
		if (dfa1.getStates().size() != dfa2.getStates().size()) {
			return false;
		}
		if (dfa1.End != dfa2.End) {
			return false;
		}
		if (dfa1.Start != dfa2.Start) {
			return false;
		}
		return true;
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
