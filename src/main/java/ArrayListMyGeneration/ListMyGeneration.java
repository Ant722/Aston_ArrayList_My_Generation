package ArrayListMyGeneration;

public interface ListMyGeneration<T extends Object> {
    int size();

    boolean isEmpty();

    boolean add(Object o);

    void add(int index, Object element);

    boolean remove(Object o);


    void clear();

    Object get(int index);

    Object set(int index, Object element);

    Object remove(int index);

    boolean contains(Object o);

    void sort();


}
