package compiler.DFA.entity;

import java.util.LinkedList;

/**
 * 
 * @author Allen
 * 
 */

public class DFA {
	private LinkedList<Integer> States = new LinkedList<Integer>();
	private Character DFA_Name = 'S';
	private boolean IsStart = false;
	private boolean IsEnd = false;

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

	public boolean isIsStart() {
		return IsStart;
	}

	public void setIsStart(boolean isStart) {
		IsStart = isStart;
	}

	public boolean isIsEnd() {
		return IsEnd;
	}

	public void setIsEnd(boolean isEnd) {
		IsEnd = isEnd;
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

}
