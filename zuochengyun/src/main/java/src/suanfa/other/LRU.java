package src.suanfa.other;

import java.util.HashMap;

public class LRU {
    HashMap<String,LRUNode> lruMap = new HashMap<>();
    LRUNode tail = null;
    LRUNode head = null;
    int capacity = 0;

    public LRU(int capacity){
        this.capacity = capacity;
    }

    public void put(String key,String value){
        if (head == null){
            LRUNode lruNode = new LRUNode(key, value);
            lruMap.put(key,lruNode);
            head = lruNode;
            tail = lruNode;
        }else {
            LRUNode lruNode = lruMap.get(key);
            if (lruNode == null){
                lruNode = new LRUNode(key, value);
                if (lruMap.size() >= capacity){
                    lruMap.remove(tail.key);
                    tail = tail.pre;
                    tail.next = null;
                }
                lruMap.put(key,lruNode);
                lruNode.next = head;
                head.pre = lruNode;
                head = lruNode;
            }else {
                lruNode.value = value;
                removeAndAdd(lruNode);
            }
        }
    }

    public Object get(String key){
        LRUNode lruNode = lruMap.get(key);
        if (lruNode != null){
            removeAndAdd(lruNode);
            return lruNode.value;
        }
        return null;
    }

    public void removeAndAdd(LRUNode lruNode){
        if (head == lruNode){
            return;
        } else if (tail == lruNode) {
            tail = lruNode.pre;
            tail.next = null;
        }else {
            lruNode.next.pre = lruNode.pre;
            lruNode.pre.next = lruNode.next;
        }
        //插入到头结点
        lruNode.next = head;
        lruNode.pre = null;
        head.pre = lruNode;
        head = lruNode;
    }

    class LRUNode{
        String key;
        Object value;
        LRUNode next;
        LRUNode pre;
        public LRUNode(String key, Object value) {
            this.key = key;
            this.value = value;
        }
    }
}
