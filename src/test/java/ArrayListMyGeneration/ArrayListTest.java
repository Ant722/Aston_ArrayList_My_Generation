package ArrayListMyGeneration;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ArrayListTest {

    @Test
    void size() {
        ListMyGeneration<Integer> list = new ArrayList<>();
        int a = 1;
        int b = 34;
        int expected = 0;
        Assertions.assertEquals(expected, list.size());
        list.add(a);
        list.add(b);
        expected = 2;
        Assertions.assertEquals(expected, list.size());
    }

    @Test
    void isEmpty() {
        ListMyGeneration<Integer> list = new ArrayList<>();
        Assertions.assertTrue(list.isEmpty());
        list.add(1);
        list.add(32);
        Assertions.assertFalse(list.isEmpty());
    }

    @Test
    void add() {
        ListMyGeneration<String> list = new ArrayList<>();
        list.add("Кран");
        list.add("Cross");
        list.add("тиран");
        list.add("boss");
        list.add("ран");
        list.add("tross");
        list.add("парус");
        list.add("sod");
        list.add("Клан");
        list.add("капрре");
        int excepted = 10;
        Assertions.assertEquals(excepted, list.size());
        list.add(2, "Баран");
        String expectedString = "Баран";
        Assertions.assertEquals(expectedString, list.get(2));

    }

    @Test
    void addByIndex() {
        ListMyGeneration<String> list = new ArrayList<>();
        list.add("Кран");
        list.add("Cross");
        list.add("тиран");
        list.add("boss");
        list.add(2, "Баран");
        String expectedString = "Баран";
        Assertions.assertEquals(expectedString, list.get(2));
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> list.add(13, "Сорян"));
    }

    @Test
    void remove() {
        ListMyGeneration<String> list = new ArrayList<>();
        String str = "Кран";
        list.add(str);
        list.add("Cross");
        list.add("тиран");
        list.add("boss");
        Assertions.assertTrue(list.remove(str));
        int excepted = 3;
        Assertions.assertEquals(excepted, list.size());
    }

    @Test
    void byIndexRemove() {
        ListMyGeneration<String> list = new ArrayList<>();
        list.add("Кран");
        list.add("Cross");
        list.add("тиран");
        list.add("boss");
        list.remove(1);
        list.remove(0);
        int excepted = 2;
        Assertions.assertEquals(excepted, list.size());
        Assertions.assertFalse(list.contains("Cross"));
    }

    @Test
    void clear() {
        ListMyGeneration<String> list = new ArrayList<>();
        list.add("Кран");
        list.add("Cross");
        list.add("тиран");
        list.add("boss");
        list.add("ран");
        list.add("tross");
        list.add("парус");
        list.add("sod");
        list.add("Клан");
        list.add("капрре");
        list.clear();
        int excepted = 0;
        Assertions.assertEquals(excepted, list.size());

    }

    @Test
    void get() {
        ListMyGeneration<String> list = new ArrayList<>();
        list.add("Кран");
        list.add("Cross");
        list.add("тиран");
        list.add("boss");
        list.add("ран");
        list.add("tross");
        list.add("парус");
        list.add("sod");
        list.add("Клан");
        list.add("капрре");
        String excepted = "Клан";
        Assertions.assertEquals(excepted, list.get(8));
    }

    @Test
    void set() {
        ListMyGeneration<String> list = new ArrayList<>();
        list.add("Кран");
        list.add("Cross");
        list.add("тиран");
        list.add("boss");
        list.add("ран");
        list.add("tross");
        list.add("парус");
        list.add("sod");
        list.add("Клан");
        list.set(2, "Клон");
        String excepted = "Клон";
        Assertions.assertEquals(excepted, list.get(2));
    }

    @Test
    void contains() {
        ListMyGeneration<String> list = new ArrayList<>();
        ListMyGeneration<String> list3 = new ArrayList<>();
        ListMyGeneration<String> list2 = new ArrayList<>();
        list.add("Кран");
        list.add("Cross");
        list.add("тиран");
        list.add("boss");
        list2.add("ран");
        list2.add("tross");
        list3.add("парус");
        list3.add("sod");
        list3.add("Клан");
        ListMyGeneration<ArrayList> listArray = new ArrayList<>();
        listArray.add(list);
        listArray.add(list2);
        Assertions.assertTrue(listArray.contains(list2));
        Assertions.assertFalse(listArray.contains(list3));

    }

    @Test
    void sort() {
        ListMyGeneration<String> list = new ArrayList<>();
        list.add("Кран");
        list.add("Cross");
        list.add("тиран");
        list.add("boss");
        list.add("ран");
        list.add("tross");
        list.add("парус");
        list.add("sod");
        list.add("Клан");
        list.add("капрре");
        String excepted = "sod";
        list.sort();
        Assertions.assertEquals(excepted, list.get(2));

    }

    @Test
    void testToString() {
        ListMyGeneration<String> list = new ArrayList<>();
        list.add("Кран");
        list.add("Cross");
        list.add("тиран");
        list.add("boss");
        list.add("ран");
        list.add("tross");
        String excepted = "[Кран, Cross, тиран, boss, ран, tross]";
        String actual = list.toString();
        Assertions.assertEquals(excepted, actual);
    }
}