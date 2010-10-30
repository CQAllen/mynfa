package compiler.nfa;

import java.util.LinkedList;

import compiler.DFA.entity.DFA;
import compiler.nfa.entity.NFA;
import compiler.util.MyLinkedList;

/**
 * 
 * @author Allen
 * 
 */

public class NFAtoDFA {
	// static NFA Cur = new NFA();
	private static MyLinkedList List;
	private static LinkedList<LinkedList> States_Set = new LinkedList<LinkedList>();// DFA״̬��
	static int i;// ���ڶ�λ��NFA�������λ�õ�ָ��
	static int j;// ���ڶ�λ��NFA��ĵڼ���Ԫ���ָ��

	public static void setList(MyLinkedList list) {
		List = list;
	}

	public static MyLinkedList getList() {
		return List;
	}

	public static void run(MyLinkedList List) {
		System.out.println("��ȡ��һ��MyLinkedList");
		System.out.println("����Ϊ��" + List.size() + "\n");
		LinkedList<Character> Temp_list = new LinkedList<Character>();
		NFAtoDFA.setList(List);
		Temp_list = NFAtoDFA.Clourse(List.getFirst(), Temp_list);
		Print(Temp_list);
		// addStates_Set(Temp_list);
		States_Set.add(Temp_list);
		Temp_list = new LinkedList<Character>();
		Change(States_Set.getFirst(), 'a', Temp_list);
		Temp_list = new LinkedList<Character>();
		Change(States_Set.getFirst(), 'b', Temp_list);
		System.out.println(States_Set.size());
		for(int k=0;k<States_Set.size();k++)
			Print(States_Set.get(k));
	}

	private static void addStates_Set(LinkedList<Character> tempList) {// ����DFA״̬��,�ظ��Ĳ��ᱻ����
		// TODO Auto-generated method stub
		for (int m = 0; m < States_Set.size(); m++) {
			if(States_Set.size() == tempList.size())
			for (int n = 0; n < States_Set.get(m).size(); n++)
				if (States_Set.get(m).get(n)==tempList.get(n)) {
//					States_Set.add(tempList);
//					System.out.println("");
					continue;
				} else {
					break;
				}
			else continue;
		}
		States_Set.add(tempList);
	}

	private static void Print(LinkedList<Character> List) {
		// TODO Auto-generated method stub
		System.out.print("{ ");
		for (int index = 0; index < List.size(); index++)
			System.out.print(List.get(index).toString() + " ");
		System.out.println("}");
	}

	public static LinkedList<Character> Clourse(NFA cur,
			LinkedList<Character> Temp_list) {// ��_Clourse�հ�
		// TODO Auto-generated method stub
		// System.out.println(i+" "+j);
		boolean Continue = false;
		if (Check(Temp_list, cur.getFrom().charAt(0)))
			Temp_list.add(cur.getFrom().charAt(0));
		for (int index = 0; index < cur.getReceive().length(); index++)
			if (cur.getReceive().charAt(index) == '#') {
				System.out.println(cur.getFrom().charAt(index) + " recieve "
						+ cur.getReceive().charAt(index) + " to "
						+ cur.getTo().charAt(index));
				Temp_list.add(cur.getTo().charAt(index));
				Continue = Search(cur.getTo().charAt(index), '#');
				if (Continue)
					Clourse(getList().get(i), Temp_list);
				else
					return Temp_list;
			} else {
				continue;
			}
		return Temp_list;

	}

	private static boolean Check(LinkedList<Character> Temp_list, Character ch) {// �鿴�Ƿ����ظ�Ԫ��
		// TODO Auto-generated method stub
		for (int k = 0; k < Temp_list.size(); k++) {
			if (Temp_list.get(k) == ch)
				return false;// �ҵ��ظ�Ԫ��
		}
		return true;// û���ҵ��ظ�Ԫ��
	}

	public static void Change(LinkedList<Character> list, Character ch,
			LinkedList<Character> Temp_list) {// X��ת��
		// TODO Auto-generated method stub
		for (int index = 0; index < list.size(); index++) {
			if (Search(list.get(index), ch)) {
				System.out.println(getList().get(i).getFrom().charAt(j)
						+ " recieve " + getList().get(i).getReceive().charAt(j)
						+ " to " + getList().get(i).getTo().charAt(j));
				if (Check(Temp_list, getList().get(i).getTo().charAt(j)))
					Temp_list.add(getList().get(i).getTo().charAt(j));
				ChangeNull(getList().get(i).getTo().charAt(j), '#', Temp_list);
			} else
				continue;
		}
		Print(Temp_list);
		addStates_Set(Temp_list);
	}

	private static void ChangeNull(Character from, Character receive,
			LinkedList<Character> Temp_list) {
		// TODO Auto-generated method stub
		if (Search(from, receive)) {
			System.out.println(getList().get(i).getFrom().charAt(j)
					+ " recieve " + getList().get(i).getReceive().charAt(j)
					+ " to " + getList().get(i).getTo().charAt(j));
			if (Check(Temp_list, getList().get(i).getTo().charAt(j)))
				Temp_list.add(getList().get(i).getTo().charAt(j));
			ChangeNull(getList().get(i).getTo().charAt(j), '#', Temp_list);
		} else
			return;
	}

	private static boolean Search(Character from, Character receieve) {// ���Ҳ���λijָ��
		// TODO Auto-generated method stub
		for (i = 0; i < getList().size(); i++) {
			for (j = 0; j < getList().get(i).getFrom().length(); j++) {
				if (getList().get(i).getFrom().charAt(j) == from
						&& getList().get(i).getReceive().charAt(j) == receieve)
					return true;

			}
		}
		return false;
	}
}
