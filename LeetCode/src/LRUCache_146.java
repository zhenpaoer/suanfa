/**
 * Created by zhangzhen on 2020/5/25
 */

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * LRU缓存机制
 * @Description: TODO
 * 运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制。它应该支持以下操作： 获取数据 get 和 写入数据 put 。
 *
 * 获取数据 get(key) - 如果密钥 (key) 存在于缓存中，则获取密钥的值（总是正数），否则返回 -1。
 * 写入数据 put(key, value) - 如果密钥已经存在，则变更其数据值；如果密钥不存在，则插入该组「密钥/数据值」。当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。
 *你是否可以在 O(1) 时间复杂度内完成这两种操作？
 **/
//示例：
//LRUCache cache = new LRUCache( 2 /* 缓存容量 */ );
//
//		cache.put(1, 1);
//		cache.put(2, 2);
//		cache.get(1);       // 返回  1
//		cache.put(3, 3);    // 该操作会使得密钥 2 作废
//		cache.get(2);       // 返回 -1 (未找到)
//		cache.put(4, 4);    // 该操作会使得密钥 1 作废
//		cache.get(1);       // 返回 -1 (未找到)
//		cache.get(3);       // 返回  3
//		cache.get(4);       // 返回  4

public class LRUCache_146 {
	//todo 使用linkedHashMap 解题
	/*static class LRU<K, V> extends LinkedHashMap<K, V> {
		public int capacity;
		public LRU(int capacity) {
			super(capacity, 0.75f, true);
			this.capacity = capacity;
		}
		@Override
		protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
			return size() > capacity;
		}
	}
	public LRU lru;
	public LRUCache_146(int capacity) {
		lru = new LRU(capacity);
	}

	public int get(int key) {
		if (lru.containsKey(key)) {
			return (int) lru.get(key);
		}
		return -1;
	}

	public void put(int key, int value) {
		lru.put(key, value);
	}*/
	//todo 使用hashmap+ node解题
	public Node head;
	public Node tail;
	public int size;
	public HashMap<Integer,Node> hashMap ;
	public LRUCache_146(int capacity){

		hashMap = new HashMap();
		head = new Node(0,0);
		tail = new Node(0,0);
		head.next = tail;
		tail.prev = head;
		this.size = capacity;
	}
	public void put(int key ,int value){
		if (hashMap.containsKey(key)){
			Node node = hashMap.get(key);
			delete(node);
			node.value = value;
			add(node);
		}else {
			Node node = new Node(key,value);
			add(node);
			hashMap.put(node.key,node);
		}

	}
	public int get(int key){
		if (hashMap.containsKey(key)){
			Node node = hashMap.get(key);
			delete(node);
			add(node);
			return node.value;
		}else {
			return -1;
		}
	}
	public void add(Node node){
		insertQueue(node);
		hashMap.put(node.key,node);
		if (hashMap.size() > size){
			Node toBeDelete = tail.prev;
			delete(toBeDelete);
		}
	}
	public void insertQueue(Node newNode){
		Node oldFirst = head.next;
		head.next = newNode;
		oldFirst.prev = newNode;
		newNode.prev = head;
		newNode.next = oldFirst;

	}

	public void delete(Node node){
		Node prev = node.prev;
		Node next = node.next;
		prev.next = next;
		next.prev = prev;
		hashMap.remove(node.key);
	}

	public static class Node{
		Node prev;
		Node next;
		int key;
		int value;
		public Node(int key,int value){
			this.key = key;
			this.value = value;
		}
	}

	public static void main(String[] args) {
		LRUCache_146 cache = new LRUCache_146(2);
		cache.put(1, 2);
		cache.put(2, 3);
		System.out.println(cache.get(1));
		cache.put(3, 4);    // 该操作会使得密钥 2 作废
		System.out.println(cache.get(2));
		cache.put(4, 5);    // 该操作会使得密钥 1 作废
		System.out.println(cache.get(1));
		System.out.println(cache.get(3));
		System.out.println(cache.get(4));


	}

}
