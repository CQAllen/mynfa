package compiler.nfa;

import java.util.LinkedList;
import compiler.nfa.entity.NFA;
import compiler.nfa.io.ReadFile;
import compiler.util.MyLinkedList;


public class RtoNfa {
	
	
	int weight=0;//����ÿ��״̬��Ȩֵ
	ReadFile rf=ReadFile.getReadFile();
//	ReadFile rf=new ReadFile();
	Character c;
	StringBuffer sb=new StringBuffer();
//	LinkedList<NFA> NFAlist=new LinkedList<NFA>();//�����ʽ�����������״̬ת����NFA
	LinkedList<NFA> NFAlist ;
	LinkedList<Character> charlist=new LinkedList<Character>();
	LinkedList<NFA> otherlist=new LinkedList<NFA>();//���������ʱ��NFA
	
	public RtoNfa(String file){
		System.out.println("file="+file);
		rf.setFile(file);//��������ʽΪȥ�������˵ĺ�׺���ʽ
		NFAlist=MyLinkedList.getNFAlist();
		
//		NFA x=new NFA();
//    	x.setFrom(weight);
//    	x.setReceive('#');
//    	x.setTo(++weight);
//    	NFAlist.add(x);//�ȷ���һ����ʼ״̬
	}
	
	
	
    public void go(){//��isCorrect()�����õ����ַ���������
    	c=rf.getNextChar();
    	System.out.println("�������ַ�"+c);
    	if(c==null){
    		return;
    	}
    	else if(c!='.'&&c!='*'&&c!='|'){
    		System.out.println("�����ַ�"+c);
    	 charlist.add(c);
       }else if(c=='.'&&rf.getCurrentChar()!='|'){
    	    
    	    if(charlist.size()>=2){
    	    Character c1=charlist.getLast();//�õ�����
    	    Character c2=charlist.getLast();
    		NFA x1=new NFA();
        	x1.getFrom().append(weight);
        	x1.getReceive().append(c2);
        	x1.getTo().append(++weight);
        	NFAlist.add(x1);
        	NFA x2=new NFA();
        	x2.getFrom().append(weight);
        	x2.getReceive().append(c1);
        	x2.getTo().append(++weight);
        	NFAlist.add(x2);
    	    }else if(charlist.size()==1){
    	    	NFA x3=new NFA();
            	x3.getFrom().append(weight);
            	x3.getReceive().append(charlist.getLast());
            	x3.getTo().append(++weight);
            	NFAlist.add(x3);
    	    }
    	    
           
       }else if(c=='.'&&rf.getCurrentChar()=='|'){
    	     
    	    
    	     
    	    if(charlist.size()>=2){
        	    Character c1=charlist.getLast();
        	    Character c2=charlist.getLast();
        		NFA x4=new NFA();

        		x4.getFrom().append(weight);
        		x4.getReceive().append(c2);
        		x4.getTo().append(++weight);
            	otherlist.add(x4);
            	 NFA x5=new NFA();
            	x5.getFrom().append(weight);
        		x5.getReceive().append(c1);
        		x5.getTo().append(++weight);
            	otherlist.add(x5);
        	    }else if(charlist.size()==1){
        	    	NFA x6=new NFA();
                	x6.getFrom().append(weight);
            		x6.getReceive().append(charlist.getLast());
            		x6.getTo().append(++weight);
                	otherlist.add(x6);
        	    }
    	    //���ղżӽ�ȥ��NFA��ӳ�ʼ̬���ܽ�̬
    	    NFA start=NFAlist.peekFirst();
    	    NFA end=NFAlist.peekLast();
    	    NFA x7=new NFA();//�����µĳ�ʼ�ڵ�
        	x7.getFrom().append(weight);
    		x7.getReceive().append('#');
    		x7.getTo().append(start.getFrom().charAt(0));
    	    ++weight;
    	    NFAlist.addFirst(x7);
    	    
    	    NFA x8=new NFA();//�����µ���̬�ڵ�
        	x8.getFrom().append(weight);
    		x8.getReceive().append('#');
    		x8.getTo().append(++weight);
        	end.getTo().append((x8.getFrom().charAt(0)));
        	NFAlist.addLast(x8);
        	
        	//������һ������
        	//������һ���µĳ�ʼ�ڵ㣬��������ڵ��toֵ��ͬ���������뱾���ĳ�ʼ�ڵ���ͬ

        	Character from=NFAlist.peekFirst().getFrom().charAt(0);
        	Character receive=NFAlist.peekFirst().getReceive().charAt(0);
        	NFAlist.peekFirst().getFrom().append(from);
        	NFAlist.peekFirst().getReceive().append(receive);
        	NFAlist.peekFirst().getTo().append(otherlist.peekFirst().getFrom().charAt(0));
         	
            //������һ���µ���̬�ڵ�
            //from Ҫ�ı� to���ı� receive���ı�
        	Character to1=NFAlist.peekLast().getTo().charAt(0);
        	Character receive1=NFAlist.peekLast().getReceive().charAt(0);
        	Character from1 = NFAlist.peekLast().getFrom().charAt(0);
        	NFAlist.peekLast().getFrom().append(from1);
        	NFAlist.peekLast().getReceive().append(receive1);
        	NFAlist.peekLast().getTo().append(to1);
         	otherlist.peekLast().getTo().setCharAt(0, NFAlist.peekLast().getFrom().charAt(0));//����������������״̬���ӵ���ǰ����̬�ڵ���
            //�����α���otherlist�����nfa����
         	while(true){
         		if(otherlist.isEmpty()){
         			break;
         		}
         		NFAlist.add(otherlist.getFirst());
         	}
         	if(rf.getCurrentChar()=='*'){
//         		Character to2=NFAlist.peekLast().getTo().charAt(0);
            	Character receive2=NFAlist.peekLast().getReceive().charAt(0);
            	Character from2 = NFAlist.peekLast().getFrom().charAt(0);
            	NFAlist.peekLast().getFrom().append(from2);
            	NFAlist.peekLast().getReceive().append(receive2);
            	NFAlist.peekLast().getTo().append(NFAlist.peekFirst().getFrom().charAt(0));
         	}
       }
    	   
       
     
        //�ݹ�����Լ�
           go();
      
    }
}
