package leetcode;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * @author ZhouJie
 * @date 2020年4月8日 下午9:55:11 
 * @Description: 146. LRU缓存机制
 *
	运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制。它应该支持以下操作： 获取数据 get 和 写入数据 put 。
	
	获取数据 get(key) - 如果密钥 (key) 存在于缓存中，则获取密钥的值（总是正数），否则返回 -1。
	写入数据 put(key, value) - 如果密钥已经存在，则变更其数据值；如果密钥不存在，则插入该组「密钥/数据值」。当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。
	
	 
	
	进阶:
	
	你是否可以在 O(1) 时间复杂度内完成这两种操作？
	
	 
	
	示例:
	
	LRUCache cache = new LRUCache( 2 );// 缓存容量
	
	cache.put(1,1);cache.put(2,2);cache.get(1); // 返回  1
	cache.put(3,3); // 该操作会使得密钥 2 作废
	cache.get(2); // 返回 -1 (未找到)
	cache.put(4,4); // 该操作会使得密钥 1 作废
	cache.get(1); // 返回 -1 (未找到)
	cache.get(3); // 返回  3
	cache.get(4); // 返回  4
	
	来源：力扣（LeetCode）链接：https://leetcode-cn.com/problems/lru-cache
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

 */
public class LeetCode_0146 {

}

/**
 * @author ZhouJie
 * @date 2020年4月8日 下午10:11:52 
 * @Description: 1-利用LinkedHashMap的特性直接构造一个LRU，缺点：在移除key相同的元素时需要遍历链表
 *
 */
class LRUCache_1 {
	private LinkedHashMap<Integer, Integer> cache;

	public LRUCache_1(int capacity) {
		cache = new LinkedHashMap<Integer, Integer>(capacity, 0.75F, true) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected boolean removeEldestEntry(java.util.Map.Entry<Integer, Integer> eldest) {
				return size() > capacity;
			}
		};

	}

	public int get(int key) {
		Integer value = cache.get(key);
		if (value == null) {
			return -1;
		} else {
			return value;
		}
	}

	public void put(int key, int value) {
		cache.put(key, value);
	}
}

/**
 * @author ZhouJie
 * @date 2020年4月8日 下午10:44:26 
 * @Description: 2-只使用HashMap保存数据，自建Node双向链表来记录访问顺序；
 *
 */
class LRUCache_2 {
	/**
	 * @author ZhouJie
	 * @date 2020年4月8日 下午10:47:19 
	 * @Description: 定义双链表的Node节点，_0146_2，后缀为题号_方法号
	 *
	 */
	class Node_0146_2 {
		Node_0146_2 pre;
		Node_0146_2 next;
		int k;
		int v;

		public Node_0146_2(int k, int v) {
			this.k = k;
			this.v = v;
		}
	}

	// 双链表的头尾节点
	Node_0146_2 head;
	Node_0146_2 tail;
	private int capacity;
	private HashMap<Integer, Node_0146_2> cache;

	public LRUCache_2(int capacity) {
		this.capacity = capacity;
		this.cache = new HashMap<Integer, Node_0146_2>();
	}

	public int get(int key) {
		Node_0146_2 node = cache.get(key);
		if (node != null) {
			moveToTail(node);
			return node.v;
		} else {
			return -1;
		}
	}

	public void put(int key, int value) {
		Node_0146_2 node = cache.get(key);
		// 存在时需要更新map对应值并将节点移到链表尾部
		if (node != null) {
			node.v = value;
			moveToTail(node);
		} else {
			// 不存在时需要先判断容量，满了需要先删除头部节点和对应map中的数据，然后再新建节点放到链表的尾部并放入map
			if (cache.size() == capacity) {
				Node_0146_2 oldHead = removeHead();
				if (oldHead != null) {
					cache.remove(oldHead.k);
				}
			}
			Node_0146_2 newNode = new Node_0146_2(key, value);
			addToTail(newNode);
			cache.put(key, newNode);
		}
	}

	/**
	 * @author: ZhouJie
	 * @date: 2020年4月8日 下午11:27:17 
	 * @param: @return
	 * @return: Node_0146_2
	 * @Description: 缓存满时需要删除最旧的数据，对应到方法是丢弃头部节点，返回的节点供map删除对应数据
	 *
	 */
	private Node_0146_2 removeHead() {
		if (head == null) {
			return null;
		} else {
			Node_0146_2 temp = head;
			head = head.next;
			temp.next = null;
			head.pre = null;
			return temp;
		}
	}

	/**
	 * @author: ZhouJie
	 * @date: 2020年4月8日 下午11:28:53 
	 * @param: @param node
	 * @return: void
	 * @Description: get命中时需要更新缓存，对应到方法是移动节点到尾部
	 *
	 */
	private void moveToTail(Node_0146_2 node) {
		// 若缓存最多只有一个数据或目标节点就是尾部节点时，什么都不做
		if (head == tail || node == tail) {
			return;
		}
		// 若目标节点是头结点，则需要先更新头结点，否则直接改动上下节点的关联关系
		if (node == head) {
			head = head.next;
			head.pre = null;
		} else {
			node.pre.next = node.next;
			node.next.pre = node.pre;
		}
		// 将目标节点移动到尾部
		tail.next = node;
		node.pre = tail;
		node.next = null;
		tail = node;
	}

	/**
	 * @author: ZhouJie
	 * @date: 2020年4月8日 下午11:31:34 
	 * @param: @param node
	 * @return: void
	 * @Description: 新建缓存总是放到尾部
	 *
	 */
	private void addToTail(Node_0146_2 node) {
		// 首个数据
		if (tail == null) {
			head = tail = node;
		}
		tail.next = node;
		node.pre = tail;
		tail = node;
	}

}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */