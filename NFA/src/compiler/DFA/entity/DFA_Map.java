package compiler.DFA.entity;

public class DFA_Map {
	DFA Start;
	DFA End;
	Character Change;

	public DFA getStart() {
		return Start;
	}

	public void setStart(DFA start) {
		Start = start;
	}

	public DFA getEnd() {
		return End;
	}

	public void setEnd(DFA end) {
		End = end;
	}

	public Character getChange() {
		return Change;
	}

	public void setChange(Character change) {
		Change = change;
	}

}
