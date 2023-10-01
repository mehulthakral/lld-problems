public class MyHashMap<K, V> {

    private final Entry[] entries;
    private final int defaultSize = 16;
    private final int maxCapacity = 1 << 30;
    private final int size;

    public MyHashMap() {
        size = defaultSize;
        entries = new Entry[defaultSize];
    }

    public MyHashMap(int cap) {
        size = getHashMapCap(cap);
        System.out.printf("Creating map of size %d%n", size);
        entries = new Entry[size];
    }

    private int getHashMapCap(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return n < 0 ? 1 : (n > maxCapacity ? maxCapacity : n+1);
    }

    public static class Entry<K,V> {
        K key;
        V value;
        Entry next;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }
    }

    public void put(K key, V value) {
        int hashCode = key.hashCode() % size;
        if (entries[hashCode] == null) {
            Entry entry = new Entry(key, value);
            entries[hashCode] = entry;
        } else {
            Entry prev = null, curr = entries[hashCode];
            while (curr != null) {
                if (curr.key == key) {
                    curr.value = value;
                    return;
                }
                prev = curr;
                curr = curr.next;
            }
            prev.next = new Entry(key, value);
        }
    }

    public V get(K key) {
        int hashCode = key.hashCode() % size;
        if (entries[hashCode] == null) {
            return null;
        } else {
            Entry curr = entries[hashCode];
            while (curr != null) {
                if (curr.key == key) {
                    return (V) curr.value;
                }
                curr = curr.next;
            }
            return null;
        }
    }

}
