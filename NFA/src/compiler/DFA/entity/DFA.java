package compiler.DFA.entity;

import java.util.LinkedList;
/**
 * 
 * @author Allen
 *
 */

public class DFA {
	private LinkedList<Integer> States;
	private Character DFA_Name='S';
	private boolean IsStart=false;
	private boolean IsEnd=false;

	public LinkedList<Integer> getStates() {
		if(States==null)
			States=new LinkedList<Integer>();
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

}
