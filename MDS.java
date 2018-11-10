/** Starter code for LP3
 *  @author
 *  Abdelmounaim Hafid
 */


//axh170730
package axh170730;

import java.util.List;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.TreeMap;
import java.util.TreeSet;

public class MDS {
    // Add fields of MDS here
	/*
	 * This class is used to hold description and price values
	 * to be used in hash table
	 */
	public class Item{
		private TreeSet<Integer> desc = new TreeSet<>();
		private int addDesc;
		private int price;
		
		public Item() {
			price = 0;
		}
		
		public Item(TreeSet<Integer> list, int newPrice) {
			
			addDesc = 0;
			price = newPrice;
			
			for(Integer x : list) {
				Integer num = new Integer(x.intValue());
				desc.add(num);
				
			}
		}
		
		public int getPrice() {
			return price;
		}
		
		public TreeSet<Integer> getDesc(){
			return desc;
		}
		
		public int addNumbersInDescription() {
			addDesc = 0;
			for(Integer x : desc) {
				addDesc += x;
			}
			
			return addDesc;
		}
	}
	/*
	 * Key represents id, 
	 * Item class hold description and price
	 */
	Map<Integer, Item> map = new HashMap<>();
	Map<Integer, Integer> priceMap = new HashMap<>();
	//TreeMap<Integer, Integer> priceTree = new TreeMap<>();
	
    // Constructors
    public MDS() {}

    /* Public methods of MDS. Do not change their signatures.
       __________________________________________________________________
       a. Insert(id,price,list): insert a new item whose description is given
       in the list.  If an entry with the same id already exists, then its
       description and price are replaced by the new values, unless list
       is null or empty, in which case, just the price is updated. 
       Returns 1 if the item is new, and 0 otherwise.
    */
    public int insert(int id, int price, java.util.List<Integer> list) {
    	//long sTime = System.currentTimeMillis();
    	
    	TreeSet<Integer> tree = new TreeSet<>();
    	
    	priceMap.put(id, price);
    	//priceTree.put(id, price);
    	
    	if(map.containsKey(id)) {
    		
    		if(!list.isEmpty()) {
    			for(Integer x : list) {
    				Integer num = new Integer(x.intValue());
            		tree.add(num);
            	}
    			Item newItem = new Item(tree, price);
    			map.put(id, newItem);
    		}
    		else {
    			
    			Item newItem = new Item(map.get(id).getDesc(), price);
    			
    			map.put(id, newItem);
    			
    		}
    		//System.out.println(System.currentTimeMillis() - sTime);
    		
    		return 0;
    	}
    	else {
    		for(Integer x : list) {
    			Integer num = new Integer(x.intValue());
        		tree.add(num);
        	}
    		Item newItem = new Item(tree, price);
    		map.put(id, newItem);
    		//System.out.println(System.currentTimeMillis() - sTime);
    		
    		return 1;
    	}
    }

    // b. Find(id): return price of item with given id (or 0, if not found).
    public int find(int id) {
    	//long sTime = System.currentTimeMillis();
    	/*
    	if(map.get(id) == null) {
    		return 0;
    	}
    	else {
    		//System.out.println(System.currentTimeMillis() - sTime);
    		return map.get(id).getPrice();
    	}
    	*/
    	if(priceMap.containsKey(id)) {
    		return priceMap.get(id);
    	}
    	else {return 0;}
    }

    /* 
       c. Delete(id): delete item from storage.  Returns the sum of the
       ints that are in the description of the item deleted,
       or 0, if such an id did not exist.
    */
    public int delete(int id) {
    	//long sTime = System.currentTimeMillis();
    	if(!map.containsKey(id)) {return 0;}
    	else {
    		int sum = map.get(id).addNumbersInDescription();
    		map.remove(id);
    		priceMap.remove(id);
    		//priceTree.remove(id);
    		//System.out.println(System.currentTimeMillis() - sTime);
    		return sum;
    	}
    			
	
    }

    /* 
       d. FindMinPrice(n): given an integer, find items whose description
       contains that number (exact match with one of the ints in the
       item's description), and return lowest price of those items.
       Return 0 if there is no such item.
    */
    public int findMinPrice(int n) {
    	//long sTime = System.currentTimeMillis();
    	PriorityQueue<Integer> pricePQ = new PriorityQueue<>();
    	/*
    	for(Entry<Integer, Item> x : map.entrySet()) {
    		if(x.getValue().getDesc().contains(n)) {
    			pricePQ.offer(x.getValue().getPrice());
    		}
    	}*/
    	
    	
    	for(Item x : map.values()) {
    		if(x.getDesc().contains(n)) {
    			pricePQ.offer(x.getPrice());
    		}
    	}
    	
    	if(pricePQ.isEmpty()) {
    	//	System.out.println(System.currentTimeMillis() - sTime);
    		return 0;
    	}
    	else {
    	//	System.out.println(System.currentTimeMillis() - sTime);
    		return pricePQ.poll();
    	}
    	
    }

    /* 
       e. FindMaxPrice(n): given an integer, find items whose description
       contains that number, and return highest price of those items.
       Return 0 if there is no such item.
    */
    public int findMaxPrice(int n) {
    	//long sTime = System.currentTimeMillis();
    	PriorityQueue<Integer> pricePQ = new PriorityQueue<>(Comparator.reverseOrder());
    	
		/*
    	for(Entry<Integer, Item> x : map.entrySet()) {
    		if(x.getValue().getDesc().contains(n)) {
    			pricePQ.offer(x.getValue().getPrice());
    		}
    	}*/
    	
    	for(Item x : map.values()) {
    		if(x.getDesc().contains(n)) {
    			pricePQ.offer(x.getPrice());
    		}
    	}
    	
    	if(pricePQ.isEmpty()) {
    	//	System.out.println(System.currentTimeMillis() - sTime);
    		return 0;
    	}
    	else {
    	//	System.out.println(System.currentTimeMillis() - sTime);
    		return pricePQ.poll();
    	}
    	
	
    }

    /* 
       f. FindPriceRange(n,low,high): given int n, find the number
       of items whose description contains n, and in addition,
       their prices fall within the given range, [low, high].
    */
    public int findPriceRange(int n, int low, int high) {
    	//long sTime = System.currentTimeMillis();
    	TreeSet<Integer> prices = new TreeSet<>();
    	/*
    	for(Entry<Integer, Item> x : map.entrySet()) {
    		if(x.getValue().getDesc().contains(n)) {
    			prices.add(x.getValue().getPrice());
    		}
    	}*/
    	/*
    	Map<Integer, Integer> subTree = priceTree.subMap(low, true, high, true);
    	
    	for(Integer x : subTree.keySet()) {
    		if(map.get(x).getDesc().contains(n)) {
    			prices.add(subTree.get(x));
    		}
    	}
    	*/
    	for(Item x : map.values()) {
    		if(x.getDesc().contains(n)) {
    			if(x.getPrice() >= low && x.getPrice() <= high) {
    				prices.add(x.getPrice());
    			}
    		}
    	}
    	
    	return prices.size();
    	/*
    	if(prices.isEmpty()) {
    	//	System.out.println(System.currentTimeMillis() - sTime);
    		return 0;
    	}
    	else {
    		prices = (TreeSet<Integer>) prices.subSet(low, true, high, true);
    	//	System.out.println(System.currentTimeMillis() - sTime);
    		return prices.size();
    	}
    	*/
    }

    /*
      g. RemoveNames(id, list): Remove elements of list from the description of id.
      It is possible that some of the items in the list are not in the
      id's description.  Return the sum of the numbers that are actually
      deleted from the description of id.  Return 0 if there is no such id.
    */
    public int removeNames(int id, java.util.List<Integer> list) {
    	int sum = 0;
    //	long sTime = System.currentTimeMillis();
    	if(map.containsKey(id)) {
    		for(Integer x : list) {
    			if(map.get(id).getDesc().contains(x)) {
    				sum += x;
    				map.get(id).getDesc().remove(x.intValue());
    			}
    		}
    	}
    	//System.out.println(System.currentTimeMillis() - sTime);
    	return sum;
    }
}
