package compiler.nfa;

import java.util.List;

import compiler.DFA.entity.DFA_Map;

/**
 * DFA��С��
 * 
 * @author Cheng
 * 
 */
public class DFAMinimize {

	private List<DFA_Map> DFA_Map;

	/**
	 * ���캯������Ҫͨ��NFAת������DFA
	 */
	public DFAMinimize(List<DFA_Map> DFA_Map) {
		this.DFA_Map = DFA_Map;
	}

	/**
	 * ������С��֮���DFA
	 * 
	 * @return
	 */
	public List<DFA_Map> minimizing() {
		return null;
	}
}
