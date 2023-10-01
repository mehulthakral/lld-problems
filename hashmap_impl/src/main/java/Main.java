import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        MyHashMap<Integer, String> map = new MyHashMap<>(8);
        System.out.println(map.get(10));
        map.put(10, "hi");
        System.out.println(map.get(10));
    }
}
