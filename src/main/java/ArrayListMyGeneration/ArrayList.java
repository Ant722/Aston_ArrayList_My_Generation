package ArrayListMyGeneration;

import java.util.Arrays;

import static java.lang.System.*;

public class ArrayList<T> implements ListMyGeneration {

    private T[] array;
    private final int DEFAULT_CAPACITY = 8;
    private int size;


    public ArrayList() {
        array = (T[]) new Object[DEFAULT_CAPACITY];
    }

    public ArrayList(int initialCapacity) {
        if (validArgumentCapacity(initialCapacity)) {
            array = (T[]) new Object[initialCapacity];
        }
    }

    private boolean validArgumentCapacity(int initialCapacity) {
        if (initialCapacity <= 0) {
            throw new IllegalArgumentException(
                    "Размер должен быть больше 0, было введено: initialCapacity = " + initialCapacity);
        }
        return true;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean add(Object o) {
        int numberElementsAdd = 1;
        checkingFullness(numberElementsAdd);
        array[size] = (T) o;
        size++;
        return true;
    }

    @Override
    public void add(int index, Object element) {
        validIndexObjException(index);
        shiftToRightOfIndex(index);
        array[index] = (T) element;
        size++;
    }

    private void validIndexObjException(int index) {
        if (!validIndexObject(index)) {
            throw new IndexOutOfBoundsException(
                    "Превышен размер списка(size = " + size + ") или Index < 0, было введено: index = " + index);
        }
    }

    private void shiftToRightOfIndex(int index) {
        int numberElementsAdd = 1;
        checkingFullness(numberElementsAdd);
        for (int i = size; i > index; i--) {
            array[i] = array[i - 1];
        }
    }

    private void checkingFullness(int numberElementsAdd) {
        int spareCells = 1;
        int desiredSize = size + numberElementsAdd + spareCells;
        if (desiredSize >= array.length) {
            increasedCapacity();
        }
    }

    private void increasedCapacity() {
        int enlargedCapacity = array.length * 2;
        T[] enlargedArray = (T[]) new Object[enlargedCapacity];
        arraycopy(array, 0, enlargedArray, 0, array.length);
        array = enlargedArray;
    }

    @Override
    public boolean remove(Object o) {
        int indexObject = searchObjectInArray(o);
        if (indexObject != -1) {
            shiftLeftByIndexCell(indexObject);
            return true;
        }
        return false;
    }

    private int searchObjectInArray(Object o) {
        int indexObject = -1;
        if (o != null) {
            for (int i = 0; i < size; i++) {
                if (array[i].equals(o)) {
                    indexObject = i;
                }
            }
        }
        return indexObject;
    }

    @Override
    public Object remove(int index) {
        Object o = null;
        if (validIndexObject(index)) {
            o = array[index];
            shiftLeftByIndexCell(index);
        }
        return o;
    }

    private boolean validIndexObject(int index) {
        return index >= 0 && index < size;
    }

    private void shiftLeftByIndexCell(int index) {
        for (int i = index; i < size; i++) {
            int nextCell = i + 1;
            array[i] = array[nextCell];
        }
        size--;
    }

    @Override
    public void clear() {
        array = (T[]) new Object[array.length];
        size = 0;
    }

    @Override
    public Object get(int index) {
        if (validIndexObject(index)) return array[index];
        return null;
    }

    @Override
    public Object set(int index, Object element) {

        return validIndexObject(index) ? array[index] = (T) element : null;
    }


    @Override
    public boolean contains(Object o) {
        int indexCell = searchObjectInArray(o);
        return indexCell != -1;
    }

    @Override
    public void sort() {
        quickSort(array, 0, size - 1);
    }

    private void quickSort(T[] sortArray, int begin, int end) {

        if (size == 0 || end <= begin) return;
        T pivotObj = sortArray[(begin + end) / 2];
        int leftIndex = begin;
        int rightIndex = end;

        while (leftIndex <= rightIndex) {

            while (sortArray[leftIndex].toString().compareTo(pivotObj.toString()) < 0) leftIndex++;

            while (sortArray[rightIndex].toString().compareTo(pivotObj.toString()) > 0) rightIndex--;

            if (leftIndex <= rightIndex) {
                swapCell(sortArray, leftIndex, rightIndex);
                leftIndex++;
                rightIndex--;
            }
        }
        if (begin < rightIndex) quickSort(sortArray, begin, rightIndex);
        if (end > leftIndex) quickSort(sortArray, leftIndex, end);
    }

    private void swapCell(T[] arrayMutable, int indexCell1, int indexCell2) {
        T temp = arrayMutable[indexCell1];
        arrayMutable[indexCell1] = arrayMutable[indexCell2];
        arrayMutable[indexCell2] = temp;
    }

    public String toString() {
        return Arrays.toString(Arrays.copyOf(array, size));
    }

}
