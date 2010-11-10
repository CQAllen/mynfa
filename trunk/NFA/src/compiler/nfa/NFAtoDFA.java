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
	private static DFA Start;
	private static DFA End;
	private static Character DFA_Name = 'A';
	private static Character Cur_ch;
	private static MyLinkedList List;
	private static LinkedList<DFA> DFA_Set = new LinkedList<DFA>();
	private static LinkedList<DFA_Map> DFA_Map = new LinkedList<DFA_Map>();
	private static LinkedList<LinkedList<Integer>> States_Set = new LinkedList<LinkedList<Integer>>();// DFA״̬��
	private static LinkedList<Character> Receive_List = new LinkedList<Character>();
	static int i;// ���ڶ�λ��NFA�������ָ��
	static int j;// ���ڶ�λ��States_Set�������ָ��
	static int k = -1;// ���ڶ�λ��DFA_Set������Ĳ���ָ��
	static int point = 0;// DFA_Set�����ﵱǰָ��

	public static LinkedList<DFA_Map> getDFA_Map() {
		return DFA_Map;
	}

	public static void setList(MyLinkedList list) {
		List = list;
	}

	public static MyLinkedList getList() {
		return List;
	}

	public static void run(MyLinkedList List) {
		System.out.println("��ȡ��һ��MyLinkedList");
		System.out.println("����Ϊ��" + List.size() + "\n");
		LinkedList<Integer> Temp_list = new LinkedList<Integer>();
		NFAtoDFA.setList(List);
		getReceive_Set();
		System.out.print("���ż���Ϊ:");
		Print(Receive_List);
		Temp_list = NFAtoDFA.Clourse(List.get(0), Temp_list);
		System.out.print("��ǰ��");
		Print(Temp_list);
		// addStates_Set(Temp_list);
		States_Set.add(Temp_list);
		// createStartDFA('S',Temp_list);
		DFA temp = new DFA('S');
		temp.setStates(Temp_list);
		temp.check();
		System.out.println("Start " + temp.isStart() + " End " + temp.isEnd());
		DFA_Set.add(temp);
		Temp_list = new LinkedList<Integer>();
		ChangeService(States_Set.get(j), Receive_List, Temp_list);
		System.out.println(States_Set.size());
		for (int k = 0; k < States_Set.size(); k++)
			Print(States_Set.get(k));
	}

	private static DFA createStartDFA(Character c, LinkedList<Integer> tempList) {
		// TODO Auto-generated method stub
		DFA temp = new DFA(c);
		temp.setStates(tempList);
		temp.check();
		System.out.println("Start " + temp.isStart() + " End " + temp.isEnd());
		return temp;
	}

	private static void ChangeService(LinkedList<Integer> first,
			LinkedList<Character> receiveList, LinkedList<Integer> tempList) {
		// TODO Auto-generated method stub

		Start = createStartDFA(DFA_Set.get(point++).getDFA_Name(), first);
		if (j < States_Set.size()) {
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

	private static boolean addStates_Set(LinkedList<Integer> tempList) {// ����DFA״̬��,�ظ��Ĳ��ᱻ����
		// TODO Auto-generated method stub
		for (int m = 0; m < States_Set.size(); m++) {
			if (States_Set.get(m).size() == tempList.size()) {
				for (int n = 0; n < States_Set.get(m).size(); n++)
					if (States_Set.get(m).get(n) == tempList.get(n)) {
						if (n + 1 < tempList.size()) {
							continue;
						} else {// ��ȫ��Ȳ�ָ�������һ��Ԫ��
							return false;
						}
					} else {
						break;
					}
			} else
				continue;
		}
		System.out.println("ת����" + Cur_ch);
		States_Set.add(tempList);
		System.out.print("���ϣ�");
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
			LinkedList<Integer> Temp_list) {// ��_Clourse�հ�
		// TODO Auto-generated method stub
		boolean Continue = false;
		if (Check(Temp_list, cur.getFrom()))
			Temp_list.add(cur.getFrom());
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
		return Temp_list;

	}

	private static boolean Check(LinkedList<Integer> Temp_list, int l) {// �鿴�Ƿ����ظ�Ԫ��
		// TODO Auto-generated method stub
		for (int k = 0; k < Temp_list.size(); k++) {
			if (Temp_list.get(k) == l)
				return false;// �ҵ��ظ�Ԫ��
		}
		return true;// û���ҵ��ظ�Ԫ��
	}

	private static boolean Check(LinkedList<Character> Temp_list, Character ch) {// �鿴�Ƿ����ظ�Ԫ��
		// TODO Auto-generated method stub
		for (int k = 0; k < Temp_list.size(); k++) {
			if (Temp_list.get(k) == ch)
				return false;// �ҵ��ظ�Ԫ��
		}
		return true;// û���ҵ��ظ�Ԫ��
	}

	public static void Change(LinkedList<Integer> list, Character ch,
			LinkedList<Integer> Temp_list) {// X��ת��
		// TODO Auto-generated method stub
		System.out.print("\t");
		Print(list);
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
		System.out.print("��ǰ��");
		Print(Temp_list);
		End = CreateEndDFA(Temp_list);// �������DFA�ڵ�
		add_DFA_Set(End);
		DFA_Map tempMap = new DFA_Map();
		tempMap.setStart(Start);
		tempMap.setEnd(End);
		tempMap.setChange(Cur_ch);
		DFA_Map.add(tempMap);
		PrintDFA_Map(DFA_Map);

		addStates_Set(Temp_list);
		// for (int index = 0; index < DFA_Set.size(); index++)
		// System.out.println(DFA_Set.get(index).getDFA_Name());
	}

	private static void add_DFA_Set(DFA dfa) {
		// TODO Auto-generated method stub
		for (int index = 0; index < DFA_Set.size(); index++) {
			if (DFA_Set.get(index).equals(DFA_Set.get(index), dfa))
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

	private static DFA CreateEndDFA(LinkedList<Integer> list) {
		// TODO Auto-generated method stub
		DFA Cur = new DFA();
		Cur.setStates(list);
		Cur.check();
		if (contain(Cur)) {
			Cur.setDFA_Name(DFA_Set.get(k).getDFA_Name());
//			System.out.println("____*" + DFA_Set.get(k).getDFA_Name());
		} else {
//			System.out.println("____" + DFA_Name);
			Cur.setDFA_Name(DFA_Name);
			DFA_Name++;
			DFA_Set.add(Cur);
		}
		return Cur;
	}

	private static boolean contain(DFA Cur) {
		// TODO Auto-generated method stub
		for (int index = 0; index < DFA_Set.size(); index++) {
			if (!DFA_Set.get(index).equals(DFA_Set.get(index), Cur)) {// ������
				continue;
			} else {
//				System.out.println("�ҵ���ͬԪ��");
//				System.out.println("index��" + index);
				k = index;
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

	private static boolean Search(int from, Character receieve) {// ���Ҳ���λiָ��
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
