package compiler.nfa;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
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
	private List<DFA> allDFA;// 所有的DFA节点
	private Character[] changes;// 所有可能的读入字符

	private List<DFA> terminal;// 终态节点
	private List<DFA> non_terminal;// 非终态节点

	/**
	 * 构造函数，需要通过NFA转化来的DFA图和所有的DFA节点
	 */
	public DFAMinimize(List<DFA_Map> DFA_map, List<DFA> dfa) {
		this.DFA_map = DFA_map;
		this.allDFA = dfa;
		terminal = new ArrayList<DFA>();
		non_terminal = new ArrayList<DFA>();
		for (DFA map : allDFA) {
			if (map.isEnd()) {
				terminal.add(map);
			} else {
				non_terminal.add(map);
			}
		}
		Set<Character> c = new HashSet<Character>();
		for (DFA_Map map : DFA_map) {
			c.add(map.getChange());
		}
		int size = c.size(), i = 0;
		changes = new Character[size];
		for (Iterator<Character> it = c.iterator(); it.hasNext();) {
			changes[i++] = it.next();
		}
		c.clear();
		c = null;
	}

	/**
	 * 返回最小化之后的DFA
	 * 
	 * @return
	 */
	public List<DFA_Map> minimizing() {
		/* 通过最小化得到的节点集合 */
		List<List<DFA>> sets = new ArrayList<List<DFA>>();
		List<List<DFA>> list = new ArrayList<List<DFA>>();
		list.add(non_terminal);
		sets.addAll(split(list, 0, false));
		list.clear();
		list.add(terminal);
		sets.addAll(split(list, 0, true));
		/* 将得到的集合生成DFA最小化图并返回 */
		return generate(sets);
	}

	/* 将得到的集合生成DFA最小化图并返回 */
	private List<DFA_Map> generate(List<List<DFA>> set) {
		for (int i = 0; i < set.size(); i++) {
			if (set.get(i).size() == 0 || set.get(i).size() == 1) {
				set.remove(i--);// 除去一些不会受影响的状态
			}
		}
		for (int i = 0; i < DFA_map.size(); i++) {
			for (List<DFA> list : set) {
				DFA rep = searchRepresentative(list);
				if (list.contains(DFA_map.get(i).getStart())) {
					DFA_map.get(i).setEnd(rep);
					DFA_map.get(i).setStart(rep);
				} else if (list.contains(DFA_map.get(i).getEnd())) {
					DFA_map.get(i).setEnd(rep);
				}
			}
		}
//		return DFA_map;
		return trim(DFA_map);
	}

	private List<List<DFA>> split(List<List<DFA>> list, int index,
			boolean isTerminal) {
		if (index == changes.length) {
			return list;
		}
		List<DFA> d;
		if (isTerminal) {
			d = terminal;
		} else {
			d = non_terminal;
		}
		List<List<DFA>> set = new ArrayList<List<DFA>>();
		List<DFA> tmp1 = new ArrayList<DFA>();
		List<DFA> tmp2 = new ArrayList<DFA>();
		Character ch = changes[index];
		for (List<DFA> dfas : list) {
			if (dfas.size() == 0 || dfas.size() == 1) {
				set.add(dfas);
				continue;
			}
			for (DFA dfa : dfas) {
				DFA df = findEndByStartAndChange(dfa, ch);
				if (d.contains(df)) {// 如果该节点和弧指向本身状态集
					tmp1.add(dfa);
				} else {
					tmp2.add(dfa);
				}
			}
		}
		if (tmp1.size() != 0) {
			set.add(tmp1);
		}
		if (tmp2.size() != 0) {
			set.add(tmp2);
		}
		return split(set, ++index, isTerminal);
	}

	/**
	 * 根据起始节点和弧查在DFA图中查找节点对，未找到则返回空
	 * 
	 * @param start
	 * @param change
	 * @return
	 */
	private DFA findEndByStartAndChange(DFA start, Character change) {
		for (DFA_Map m : DFA_map) {
			if (DFA.compare(m.getStart(), start)
					&& change.equals(m.getChange())) {
				return m.getEnd();
			}
		}
		return null;
	}

	/**
	 * 从一个集合中选出一个做代表
	 * 
	 * @param dfa
	 * @return
	 */
	private DFA searchRepresentative(List<DFA> dfa) {
		return dfa.iterator().next();
	}

	/**
	 * 除去图中完全重复的
	 * 
	 * @param DFA_map
	 */
	private List<DFA_Map> trim(List<DFA_Map> DFA_map) {
		for (int i = 0; i < DFA_map.size(); i++) {
			for (int j = i+1; j < DFA_map.size(); j++) {
				if (compare(DFA_map.get(i), DFA_map.get(j))) {
					DFA_map.remove(j--);
				}
			}
		}
		return DFA_map;
	}

	private boolean compare(DFA_Map one, DFA_Map other) {
		if (!one.getChange().equals(other.getChange())) {
			return false;
		}
		if (!DFA.compare(one.getEnd(), other.getEnd())) {
			return false;
		}
		if (!DFA.compare(one.getStart(), other.getStart())) {
			return false;
		}
		return true;
	}

	public static void main(String[] args) {
		List<DFA> dfa = new ArrayList<DFA>();
		DFA s = new DFA();s.setDFA_Name('S');s.setStart(true);s.setEnd(false);dfa.add(s);
		DFA a = new DFA();a.setDFA_Name('A');a.setStart(false);a.setEnd(false);dfa.add(a);
		DFA b = new DFA();b.setDFA_Name('B');b.setStart(false);b.setEnd(false);dfa.add(b);
		DFA c = new DFA();c.setDFA_Name('C');c.setStart(false);c.setEnd(true);dfa.add(c);
		DFA d = new DFA();d.setDFA_Name('D');d.setStart(false);d.setEnd(true);dfa.add(d);
		DFA e = new DFA();e.setDFA_Name('E');e.setStart(false);e.setEnd(true);dfa.add(e);
		DFA f = new DFA();f.setDFA_Name('F');f.setStart(false);f.setEnd(true);dfa.add(f);
		List<DFA_Map> map = new ArrayList<DFA_Map>();
		DFA_Map s2a = new DFA_Map();s2a.setChange('a');s2a.setEnd(a);s2a.setStart(s);map.add(s2a);
		DFA_Map s2b = new DFA_Map();s2b.setChange('b');s2b.setEnd(b);s2b.setStart(s);map.add(s2b);
		DFA_Map a2c = new DFA_Map();a2c.setChange('a');a2c.setEnd(c);a2c.setStart(a);map.add(a2c);
		DFA_Map a2b = new DFA_Map();a2b.setChange('b');a2b.setEnd(b);a2b.setStart(a);map.add(a2b);
		DFA_Map b2a = new DFA_Map();b2a.setChange('a');b2a.setEnd(a);b2a.setStart(b);map.add(b2a);
		DFA_Map b2d = new DFA_Map();b2d.setChange('b');b2d.setEnd(d);b2d.setStart(b);map.add(b2d);
		DFA_Map c2c = new DFA_Map();c2c.setChange('a');c2c.setEnd(c);c2c.setStart(c);map.add(c2c);
		DFA_Map c2e = new DFA_Map();c2e.setChange('b');c2e.setEnd(e);c2e.setStart(c);map.add(c2e);
		DFA_Map d2f = new DFA_Map();d2f.setChange('a');d2f.setEnd(f);d2f.setStart(d);map.add(d2f);
		DFA_Map d2d = new DFA_Map();d2d.setChange('b');d2d.setEnd(d);d2d.setStart(d);map.add(d2d);
		DFA_Map e2f = new DFA_Map();e2f.setChange('a');e2f.setEnd(f);e2f.setStart(e);map.add(e2f);
		DFA_Map e2d = new DFA_Map();e2d.setChange('b');e2d.setEnd(d);e2d.setStart(e);map.add(e2d);
		DFA_Map f2c = new DFA_Map();f2c.setChange('a');f2c.setEnd(c);f2c.setStart(f);map.add(f2c);
		DFA_Map f2e = new DFA_Map();f2e.setChange('b');f2e.setEnd(e);f2e.setStart(f);map.add(f2e);
		for (DFA_Map m : map) {
			System.out.println(m.getStart().getDFA_Name() + "---"
					+ m.getChange() + "---" + m.getEnd().getDFA_Name());
		}
		System.out.println("------------------最小化----------------------");
		List<DFA_Map> min = new DFAMinimize(map, dfa).minimizing();
		for (DFA_Map m : min) {
			System.out.println(m.getStart().getDFA_Name() + "---"
					+ m.getChange() + "---" + m.getEnd().getDFA_Name());
		}
	}
}
