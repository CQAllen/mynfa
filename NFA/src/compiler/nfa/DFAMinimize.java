package compiler.nfa;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import compiler.DFA.entity.DFA;
import compiler.DFA.entity.DFA_Map;

/**
 * DFA��С��
 * 
 * @author Cheng
 * 
 */
public class DFAMinimize {

	private List<DFA_Map> DFA_map;// DFAͼ
	private List<DFA> dfa;// ���е�DFA�ڵ�
	private Character[] changes;// ���п��ܵĶ����ַ�

	/**
	 * ���캯������Ҫͨ��NFAת������DFAͼ�����е�DFA�ڵ�
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
	 * ������С��֮���DFA
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
	 * �����ָ�֮��ĸ����Ӽ�
	 */
	private List<List<DFA>> set;

	/**
	 * �����������ָ����DFA���Ͻ��н�һ����ϸ�µķָ�
	 * 
	 * @param terminal
	 *            ��̬�ڵ㼯��
	 * @param non_terminal
	 *            ����̬�ڵ㼯��
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
