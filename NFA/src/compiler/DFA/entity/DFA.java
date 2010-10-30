package compiler.DFA.entity;

import java.util.LinkedList;
/**
 * 
 * @author Allen
 *
 */

public class DFA {
	private LinkedList<Character> States;
	private String DFA_Name;
	private boolean IsStart;
	private boolean IsEnd;

	public LinkedList<Character> getStates() {
		if(States==null)
			States=new LinkedList<Character>();
		return States;
	}

	public void setStates(LinkedList<Character> states) {
		this.States = states;
	}

	public String getDFA_Name() {
		return DFA_Name;
	}

	public void setDFA_Name(String dFAName) {
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
