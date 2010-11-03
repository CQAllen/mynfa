package compiler.nfa.entity;

public class Note {

	private int weight;
	 Note(int weight){
		this.weight=weight;
	}

	public  int getWeight() {
		return weight;
	}

	public  void setWeight(int weight) {
		this .weight = weight;
	}
}
