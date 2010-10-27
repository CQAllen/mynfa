 package compiler.nfa.entity;

public class NFA {
	int from;
	int to;
	char receive;
	
	
	public int getFrom() {
		return from;
	}
	public void setFrom(int from) {
		this.from = from;
	}
	public int getTo() {
		return to;
	}
	public void setTo(int to) {
		this.to = to;
	}
	public char getReceive() {
		return receive;
	}
	public void setReceive(char receive) {
		this.receive = receive;
	}
	

}
