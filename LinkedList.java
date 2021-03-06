import java.util.HashMap;
import java.util.Hashtable;


class Node
{
	Node next;
	int data;
	public Node ( int data )
	{
		this.data = data;
	}

	void addList(int data)
	{
		Node n = this;
		while(n.next  != null )
		{
			n = n.next;
		}
		n.next = new Node (data);
		
	}
	Node removeList(int data)
	{
		Node head = this;
		Node n = this;
		if(n.data == data)
		{
			System.out.println("head remove");
			return n.next;
		}
		while(n != null){
			if(n.next.data == data)
			{	
				n.next = n.next.next;
				break;					
			}
			n = n.next;
		}
		return head;
		
	}

	void displayList ( )
	{
		Node n = this;
		while(n != null){
			System.out.println(n.data);
			n  = n.next;
		}	
	}

	void displayListRec(Node n)
	{
		if(n == null)
			return;
		System.out.println(n.data);
		displayListRec(n.next);
	}

	void removeDuplicates()
	{
		Node i = this;
		Node j;
		while( i != null && i.next != null )
		{
			j = i;
			while ( j.next != null )
			{
				if(i.data == j.next.data)
				{
					j.next = j.next.next;
				}
				else
				{
					j = j.next;
				}
			}
			
			i = i.next;
		}
	}
	void removeDupsHash(){
		Hashtable<Integer,Boolean> hm = new Hashtable<Integer,Boolean>();
		Node n = this;
		

		hm.put(n.data, new Boolean(true));
		while(n.next!=null){
			if(hm.containsKey(n.next.data)){
					n.next = n.next.next;
			}
			else{
				hm.put(n.next.data, new Boolean(true));
			}
			n=n.next;
		}
	}
	
	Node Reverse(){
		// 5 > 4 > 3
		Node n = this;
		Node previous = null;
		Node saveNext = null;
		while(n!=null){
			saveNext = n.next;
			n.next = previous;
			previous = n;
			n = saveNext;
		}
		return previous;
	}
	void Reverse(Node previous){
		Node n  = this.Reverse(previous,this);
		n.displayList();	
	}
	Node Reverse(Node previous, Node n){
		Node saveNext = n.next;
		n.next = previous;
		previous = n;
		if(saveNext == null)
			return n;
		
		return saveNext.Reverse(previous,saveNext);	
	}
	Node nthElement(int n)
	{
		Node i = this;
		Node j = this;
		for(;n>0;n--)
			i = i.next;
		while(i!=null)
		{
			i = i.next;
			j = j.next;
		}
		return j;
	}
	/*
		a b c d
		ace

		k + 2(n-k) = n - k
		2n - k = n - k
           
	*/
	void isCircular()
	{
		Node slow = this;
		Node fast = slow;
		while(fast != null)
		{
			slow = slow.next;
			fast = fast.next.next;
			if(fast == slow)
			{
				break;
			}
		}
		slow = this;
		while(slow != fast)
		{
			slow = slow.next;
			fast = fast.next;
		}
		System.out.println(slow.data);
	}

	// Partition a LL on a given value x, such that all values less than x come before it and all values greater than x come after it
	
	Node partition(int x){
		Node n = this;
		Node smallBegin = null,smallEnd = null ,bigBegin = null,bigEnd = null;
		while(n!=null){
			if(n.data < x){
				if(smallBegin == null){
					smallBegin = n;
					smallEnd = smallBegin;
				}
				else{
					smallEnd.next = n;
					smallEnd = n;
				}
			}
			else{
				if(bigBegin == null){
					bigBegin = n;
					bigEnd = bigBegin;
				}
				else{
					bigEnd.next = n;
					bigEnd = n;
				}
			}
			n = n.next;
			
		}
		bigEnd.next = null;
		smallEnd.next = bigBegin;
		return smallBegin;
	}
	Node addTwoLinkedLists(Node l1, Node l2, int carry){
		
		if(l1==null && l2 == null && carry ==0)
			return null;
		
		int resultData = 0,resultCarry = 0;Node result;

		if(l1!=null)
			resultData += l1.data;
		if(l2!=null)
			resultData += l2.data;

		resultCarry = ( resultData + carry ) / 10;

		resultData += carry;

		result = new Node(resultData %10);
		if(l1!=null || l2 != null)
			result.next = addTwoLinkedLists(l1==null?null:l1.next, l2==null?null:l2.next,resultCarry);

		return result;
	}

}

class LinkedList
{
	public static void main( String args[])
	{
		Node i = new Node (5);	
		i.addList(4);
		i.addList(4);
		i.addList(2);
		i.addList(1);
		i.displayList();
		System.out.println();
		Node j = new Node (5);	
		j.addList(4);
		j.addList(4);
		j.addList(2);
		j.addList(1);
		j.displayList();
		System.out.println();
		i.addTwoLinkedLists(i,j,0).displayList();
		//i.Reverse();
		//i.Reverse(null);
		//i.displayList();
		//Node k = i.partition(3);
		//k.displayList();
		//System.out.println(i.removeList());
		//n.displayList();
		//n.removeDuplicates();
		//System.out.println();
		//n.displayList();
		//Node i = n.nthElement(3);
		//Node j = n.nthElement(1);
		//j.next = i;
		//n.isCircular();
		
	}
	/*
	Node addLL(Node i, Node j, Node res, int c){
		if(i!=null && j!=null){
			int result = i.data + j.data + c;
			int carry = result/10;
			res.data = result % 10;
			addLL(i.next,j.next,res.next,carry);
		}
	}*/
	
}
