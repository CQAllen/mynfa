package compiler.nfa;

import java.util.List;

import compiler.DFA.entity.DFA_Map;

/**
 * DFA最小化
 * 
 * @author Cheng
 * 
 */
public class DFAMinimize {

	private List<DFA_Map> DFA_Map;

	/**
	 * 构造函数，需要通过NFA转化来的DFA
	 */
	public DFAMinimize(List<DFA_Map> DFA_Map) {
		this.DFA_Map = DFA_Map;
	}

	/**
	 * 返回最小化之后的DFA
	 * 
	 * @return
	 */
	public List<DFA_Map> minimizing() {
		return null;
	}
}
