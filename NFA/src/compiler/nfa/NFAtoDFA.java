package compiler.nfa;

import java.util.LinkedList;

import compiler.DFA.entity.DFA;
import compiler.DFA.entity.DFA_Map;
import compiler.nfa.entity.NFA;
import compiler.util.MyLinkedList;

/**
 * 
 * @author Allen
 * 
 */

public class NFAtoDFA {
	// static NFA Cur = new NFA();
	private static DFA Start;
	private static DFA End;
	private static Character DFA_Name = 'A';
	private static Character Cur_ch;
	private static MyLinkedList List;
	private static LinkedList<DFA> DFA_Set = new LinkedList<DFA>();
	private static LinkedList<DFA_Map> DFA_Map = new LinkedList<DFA_Map>();
	private static LinkedList<LinkedList<Integer>> States_Set = new LinkedList<LinkedList<Integer>>();// DFA状态集
	private static LinkedList<Character> Receive_List = new LinkedList<Character>();
	static int i;// 用于定位在NFA集合里的指针
	static int j;// 用于定位在States_Set集合里的指针
	static int k=-1;// 用于定位在DFA_Set集合里的指针

	public static void setList(MyLinkedList list) {
		List = list;
	}

	public static MyLinkedList getList() {
		return List;
	}

	public static void run(MyLinkedList List) {
		System.out.println("获取到一个MyLinkedList");
		System.out.println("长度为：" + List.size() + "\n");
		LinkedList<Integer> Temp_list = new LinkedList<Integer>();
		NFAtoDFA.setList(List);
		getReceive_Set();
		System.out.print("符号集合为:");
		Print(Receive_List);
		Temp_list = NFAtoDFA.Clourse(List.get(0), Temp_list);
		System.out.print("当前：");
		Print(Temp_list);
		// addStates_Set(Temp_list);
		States_Set.add(Temp_list);
		// DFA temp = new DFA('S');
		// DFA_Set.add(temp);
		Temp_list = new LinkedList<Integer>();
		ChangeService(States_Set.get(j), Receive_List, Temp_list);
		// Change(States_Set.getFirst(), 'a', Temp_list);
		// Temp_list = new LinkedList<Integer>();
		// Change(States_Set.getFirst(), 'b', Temp_list);
		System.out.println(States_Set.size());
		for (int k = 0; k < States_Set.size(); k++)
			Print(States_Set.get(k));
	}

	private static void ChangeService(LinkedList<Integer> first,
			LinkedList<Character> receiveList, LinkedList<Integer> tempList) {
		// TODO Auto-generated method stub
		if (j < States_Set.size()) {
			// Print(States_Set);
			for (int index = 0; index < receiveList.size(); index++) {
				Change(first, receiveList.get(index), tempList);
				tempList = new LinkedList<Integer>();
			}
			j = j + 1;
			if (j < States_Set.size())
				ChangeService(States_Set.get(j), receiveList, tempList);
		} else
			return;
	}

	private static void getReceive_Set() {
		// TODO Auto-generated method stub
		for (int index = 0; index < getList().size(); index++) {
			if (Check(Receive_List, getList().get(index).getReceive())
					&& getList().get(index).getReceive() != '#')
				Receive_List.add(getList().get(index).getReceive());
		}
	}

	private static boolean addStates_Set(LinkedList<Integer> tempList) {// 插入DFA状态集,重复的不会被插入
		// TODO Auto-generated method stub
		for (int m = 0; m < States_Set.size(); m++) {
			if (States_Set.get(m).size() == tempList.size()) {
				for (int n = 0; n < States_Set.get(m).size(); n++)
					if (States_Set.get(m).get(n) == tempList.get(n)) {
						if (n + 1 < tempList.size()) {
							continue;
						} else {// 完全相等并指向了最后一个元素
							return false;
						}
					} else {
						break;
					}
			} else
				continue;
		}
		System.out.println("转化：" + Cur_ch);
		States_Set.add(tempList);
		System.out.print("集合：");
		Print(States_Set);
		return true;
	}

	private static void Print(LinkedList tempList) {
		// TODO Auto-generated method stub
		System.out.print("{ ");
		for (int index = 0; index < tempList.size(); index++)
			System.out.print(tempList.get(index).toString() + " ");
		System.out.println("}");
	}

	public static LinkedList<Integer> Clourse(NFA cur,
			LinkedList<Integer> Temp_list) {// ε_Clourse闭包
		// TODO Auto-generated method stub
		// System.out.println(i+" "+j);
		boolean Continue = false;
		if (Check(Temp_list, cur.getFrom()))
			Temp_list.add(cur.getFrom());
		// for (int index = 0; index < cur.getReceive().length(); index++)
		if (cur.getReceive() == '#') {
			System.out.println(cur.getFrom() + " recieve " + cur.getReceive()
					+ " to " + cur.getTo());
			Temp_list.add(cur.getTo());
			Continue = Search(cur.getTo(), '#');
			if (Continue)
				Clourse(getList().get(i), Temp_list);
			else
				return Temp_list;
		}
		// else {
		// continue;
		// }
		return Temp_list;

	}

	private static boolean Check(LinkedList<Integer> Temp_list, int l) {// 查看是否有重复元素
		// TODO Auto-generated method stub
		for (int k = 0; k < Temp_list.size(); k++) {
			if (Temp_list.get(k) == l)
				return false;// 找到重复元素
		}
		return true;// 没有找到重复元素
	}

	private static boolean Check(LinkedList<Character> Temp_list, Character ch) {// 查看是否有重复元素
		// TODO Auto-generated method stub
		for (int k = 0; k < Temp_list.size(); k++) {
			if (Temp_list.get(k) == ch)
				return false;// 找到重复元素
		}
		return true;// 没有找到重复元素
	}

	public static void Change(LinkedList<Integer> list, Character ch,
			LinkedList<Integer> Temp_list) {// X弧转化
		// TODO Auto-generated method stub
		System.out.print("\t");
		Print(list);
		Start = CreateDFA(list,true);// 构造开始DFA节点
		 add_DFA_Set(Start);
//		DFA_Set.add(Start);
		System.out.println("\t" + DFA_Set.size());
		for (int index = 0; index < list.size(); index++) {
			if (Search(list.get(index), ch)) {
				System.out.println(getList().get(i).getFrom() + " recieve "
						+ getList().get(i).getReceive() + " to "
						+ getList().get(i).getTo());
				Cur_ch = ch;
				if (Check(Temp_list, getList().get(i).getTo()))
					Temp_list.add(getList().get(i).getTo());
				ChangeNull(getList().get(i).getTo(), '#', Temp_list);
			} else
				continue;
		}
		System.out.print("当前：");
		Print(Temp_list);
		// DFA temp = new DFA(Cur_ch);
		// if (!contain(DFA_Set, temp.getDFA_Name())) {// 未包含
		End = CreateDFA(Temp_list,false);// 构造结束DFA节点
		 add_DFA_Set(End);
//		DFA_Set.add(End);
		// // DFA_Set.add(new DFA(temp.getDFA_Name()));
		// // Cur_ch++;
		// } else {// 包含
		//
		// }

		DFA_Map tempMap = new DFA_Map();
		tempMap.setStart(Start);
		tempMap.setEnd(End);
		tempMap.setChange(Cur_ch);
		DFA_Map.add(tempMap);
		PrintDFA_Map(DFA_Map);

		addStates_Set(Temp_list);
		// if (addStates_Set(Temp_list)) {
		// Start = CreateDFA(list);
		// End = CreateDFA(Temp_list);
		// DFA_Map temp = new DFA_Map();
		// temp.setStart(Start);
		// temp.setEnd(End);
		// temp.setChange(Cur_ch);
		// DFA_Map.add(temp);
		// PrintDFA_Map(DFA_Map);
		// }
	}

	// private static boolean contain(LinkedList<DFA> DFASet, Character curCh)
	// {// 查找DFA_Set里是否包含当前DFA状态
	// // TODO Auto-generated method stub
	// for (int index = 0; index < DFASet.size(); index++) {
	// if (DFASet.get(index).getDFA_Name() == curCh) {
	// return true;
	// }
	// }
	// return false;
	// }

	private static void add_DFA_Set(DFA dfa) {
		// TODO Auto-generated method stub
		for (int index = 0; index < DFA_Set.size(); index++) {
			if (DFA_Set.get(index).equals(DFA_Set.get(index), dfa) >= 0)
				return;
			else
				continue;
		}
		DFA_Set.add(dfa);
	}

	private static void PrintDFA_Map(LinkedList<DFA_Map> DFAMap) {
		// TODO Auto-generated method stub
		for (int index = 0; index < DFAMap.size(); index++) {
			System.out.println("\t"
					+ DFAMap.get(index).getStart().getDFA_Name() + " receive "
					+ DFAMap.get(index).getChange() + " to "
					+ DFAMap.get(index).getEnd().getDFA_Name());
		}
	}

	private static DFA CreateDFA(LinkedList<Integer> list,boolean IsStart) {
		// TODO Auto-generated method stub
		DFA Cur = new DFA();
		for (int index = 0; index < list.size(); index++) {
			if (list.get(index) == 0) {
				Cur.setIsStart(true);
				Print(list);
				System.out.println("true");

			}
			if (list.get(index) == -1)
				Cur.setIsEnd(true);
		}
		Cur.setStates(list);
		if (!Cur.isStart()) {
			if (DFA_Set.size() != 0)
				if (contain(Cur)&&!IsStart)
					Cur.setDFA_Name(DFA_Set.get(k).getDFA_Name());
				else {
					Cur.setDFA_Name(DFA_Name);
					DFA_Name++;
					DFA_Set.add(Cur);
				}
		} 
		return Cur;
	}

	private static boolean contain(DFA Cur) {
		// TODO Auto-generated method stub
		for (int index = 0; index < DFA_Set.size(); index++) {
			int num = DFA_Set.get(index).equals(DFA_Set.get(index), Cur);
			if (num < 0) {// 不包含
				continue;
			} else {
				System.out.println("找到相同元素");
				System.out.println("k："+k);
				k = num;
				return true;
			}
		}
		return false;
	}

	private static void ChangeNull(int from, Character receive,
			LinkedList<Integer> Temp_list) {
		// TODO Auto-generated method stub
		if (Search(from, receive)) {
			System.out.println(getList().get(i).getFrom() + " recieve "
					+ getList().get(i).getReceive() + " to "
					+ getList().get(i).getTo());
			if (Check(Temp_list, getList().get(i).getTo()))
				Temp_list.add(getList().get(i).getTo());
			ChangeNull(getList().get(i).getTo(), '#', Temp_list);
		} else
			return;
	}

	private static boolean Search(int from, Character receieve) {// 查找并定位i指针
		// TODO Auto-generated method stub
		for (i = 0; i < getList().size(); i++) {
			if (getList().get(i).getFrom() == from
					&& getList().get(i).getReceive() == receieve) {
				// System.out.println(getList().get(i).getFrom() + " "
				// + getList().get(i).getReceive());
				return true;
			} else {
				// System.out.println(getList().get(i).getFrom() + " "
				// + getList().get(i).getReceive());
				continue;
			}
		}
		return false;
	}
}
