package compiler.DFA.entity;

import java.util.LinkedList;
/**
 * 
 * @author Allen
 *
 */

public class DFA {
	private StringBuffer States;
	private String DFA_Name;
	private boolean IsStart;
	private boolean IsEnd;

	public StringBuffer getStates() {
		if(States==null)
			States=new StringBuffer();
		return States;
	}

	public void setStates(StringBuffer states) {
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
