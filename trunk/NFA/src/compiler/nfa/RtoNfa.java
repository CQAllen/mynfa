package compiler.nfa;

import java.util.LinkedList;
import compiler.nfa.entity.NFA;
import compiler.nfa.io.ReadFile;
import compiler.util.MyLinkedList;


public class RtoNfa {
	
	
	int weight=0;//定义每个状态的权值
	ReadFile rf=ReadFile.getReadFile();
//	ReadFile rf=new ReadFile();
	Character c;
	StringBuffer sb=new StringBuffer();
//	LinkedList<NFA> NFAlist=new LinkedList<NFA>();//这个链式队列用来存放状态转换的NFA
	LinkedList<NFA> NFAlist ;
	LinkedList<Character> charlist=new LinkedList<Character>();
	LinkedList<NFA> otherlist=new LinkedList<NFA>();//用来存放临时的NFA
	
	public RtoNfa(String file){
		System.out.println("file="+file);
		rf.setFile(file);//更换正规式为去除括号了的后缀表达式
		NFAlist=MyLinkedList.getNFAlist();
		
//		NFA x=new NFA();
//    	x.setFrom(weight);
//    	x.setReceive('#');
//    	x.setTo(++weight);
//    	NFAlist.add(x);//先放入一个初始状态
	}
	
	
	
    public void go(){//将isCorrect()处理后得到的字符串传过来
    	c=rf.getNextChar();
    	System.out.println("读到了字符"+c);
    	if(c==null){
    		return;
    	}
    	else if(c!='.'&&c!='*'&&c!='|'){
    		System.out.println("存入字符"+c);
    	 charlist.add(c);
       }else if(c=='.'&&rf.getCurrentChar()!='|'){
    	    
    	    if(charlist.size()>=2){
    	    Character c1=charlist.getLast();//拿到符号
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
    	    //给刚才加进去的NFA添加初始态个总结态
    	    NFA start=NFAlist.peekFirst();
    	    NFA end=NFAlist.peekLast();
    	    NFA x7=new NFA();//建立新的初始节点
        	x7.getFrom().append(weight);
    		x7.getReceive().append('#');
    		x7.getTo().append(start.getFrom().charAt(0));
    	    ++weight;
    	    NFAlist.addFirst(x7);
    	    
    	    NFA x8=new NFA();//建立新的终态节点
        	x8.getFrom().append(weight);
    		x8.getReceive().append('#');
    		x8.getTo().append(++weight);
        	end.getTo().append((x8.getFrom().charAt(0)));
        	NFAlist.addLast(x8);
        	
        	//进行下一个连接
        	//构造另一个新的初始节点，但是这个节点除to值不同外其他都与本来的初始节点相同

        	Character from=NFAlist.peekFirst().getFrom().charAt(0);
        	Character receive=NFAlist.peekFirst().getReceive().charAt(0);
        	NFAlist.peekFirst().getFrom().append(from);
        	NFAlist.peekFirst().getReceive().append(receive);
        	NFAlist.peekFirst().getTo().append(otherlist.peekFirst().getFrom().charAt(0));
         	
            //构造另一个新的终态节点
            //from 要改变 to不改变 receive不改变
        	Character to1=NFAlist.peekLast().getTo().charAt(0);
        	Character receive1=NFAlist.peekLast().getReceive().charAt(0);
        	Character from1 = NFAlist.peekLast().getFrom().charAt(0);
        	NFAlist.peekLast().getFrom().append(from1);
        	NFAlist.peekLast().getReceive().append(receive1);
        	NFAlist.peekLast().getTo().append(to1);
         	otherlist.peekLast().getTo().setCharAt(0, NFAlist.peekLast().getFrom().charAt(0));//将另外个队列里面的状态连接到当前的终态节点上
            //在依次保存otherlist里面的nfa对象
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
    	   
       
     
        //递归调用自己
           go();
      
    }
}
