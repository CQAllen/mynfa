package compiler.nfa;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import compiler.DFA.entity.DFA;
import compiler.DFA.entity.DFA_Map;

/**
 * DFA最小化
 * 
 * @author Cheng
 * 
 */
public class DFAMinimize {

	private List<DFA_Map> DFA_map;// DFA图
	private List<DFA> dfa;// 所有的DFA节点
	private Character[] changes;// 所有可能的读入字符

	/**
	 * 构造函数，需要通过NFA转化来的DFA图和所有的DFA节点
	 */
	public DFAMinimize(List<DFA_Map> DFA_map, List<DFA> dfa) {
		this.DFA_map = DFA_map;
		this.dfa = dfa;
		Set<Character> c = new HashSet<Character>();
		for (DFA_Map map : DFA_map) {
			c.add(map.getChange());
		}
		changes = (Character[]) c.toArray();
		c.clear();
		c = null;
	}

	/**
	 * 返回最小化之后的DFA
	 * 
	 * @return
	 */
	public List<DFA_Map> minimizing() {
		List<DFA> terminal = new ArrayList<DFA>();
		List<DFA> non_terminal = new ArrayList<DFA>();
		for (DFA map : dfa) {
			if (map.isIsEnd()) {
				terminal.add(map);
			} else {
				non_terminal.add(map);
			}
		}
		List<List<DFA>> set = execute(terminal, non_terminal);
		return null;
	}

	/**
	 * 进过分割之后的各个子集
	 */
	private List<List<DFA>> set;

	/**
	 * 将两个初步分隔后的DFA集合进行进一步的细致的分隔
	 * 
	 * @param terminal
	 *            终态节点集合
	 * @param non_terminal
	 *            非终态节点集合
	 * @return
	 */
	private List<List<DFA>> execute(List<DFA> terminal, List<DFA> non_terminal) {
		set = new ArrayList<List<DFA>>();
		return set;
	}

	public static void main(String[] args) {
		List l = new ArrayList();
		l.add(1);
		l.add(2);
		System.out.println(l.indexOf(2));
	}
}
