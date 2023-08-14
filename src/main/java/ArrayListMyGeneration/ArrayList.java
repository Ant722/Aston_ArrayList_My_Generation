package ArrayListMyGeneration;

import java.util.Arrays;

import static java.lang.System.*;

/**
 * @param <T>
 * Реализация интерфейса ListMyGeneration с возможностью изменения размера массива. Реализует все необязательные операции со списком и разрешает все элементы, включая null. В дополнение к реализации интерфейса ListMyGeneration, этот класс предоставляет методы для управления размером массива, который используется внутри для хранения списка.
 * Операции size, isEmpty, get, set выполняются за постоянное время. Операция добавления выполняется за амортизированное постоянное время, то есть для добавления n элементов требуется O(n) времени. Все остальные операции выполняются за линейное время (грубо говоря). Постоянный коэффициент является низким по сравнению с коэффициентом для LinkedList.
 * Каждый экземпляр ArrayList имеет определенную емкость. Емкость - это размер массива, используемого для хранения элементов в списке. Он всегда, по крайней мере, равен размеру списка. По мере добавления элементов в ArrayList его емкость автоматически увеличивается. Детали политики увеличения не уточняются, за исключением того факта, что добавление элемента сопряжено с постоянными амортизированными временными затратами.
 * Приложение может увеличить емкость экземпляра ArrayList перед добавлением большого количества элементов, используя операцию checkingFullness.
 * Обратите внимание, что эта реализация не синхронизирована. Если несколько потоков обращаются к экземпляру ArrayList одновременно и по крайней мере один из потоков структурно изменяет список, он должен быть синхронизирован извне. (Структурная модификация - это любая операция, которая добавляет или удаляет один или несколько элементов или явно изменяет размер базового массива; простая установка значения элемента не является структурной модификацией.) Обычно это достигается путем синхронизации с каким-либо объектом, который естественным образом инкапсулирует список.
 * Этот класс не является членом Java Collections Framework.
 * Автор:
 * Малыхин Антон
 * Параметры типа:
 * <Т> – тип элементов в этом списке
 */
public class ArrayList<T> implements ListMyGeneration {

    /**
     * Буфер массива, в котором хранятся элементы ArrayList. Емкость ArrayList равна длине этого буфера массива. Любой пустой список ArrayList с данными элемента == ДАННЫЕ по умолчанию будут расширены до DEFAULT_CAPACITY при создании экземпляра класса.
     */
    private T[] array;
    /**
     * Начальная емкость по умолчанию.
     */
    private final int DEFAULT_CAPACITY = 8;
    /**
     * Размер ArrayList (количество элементов, которые он содержит).
     */
    private int size;


    /**
     * Создает пустой список с начальной емкостью восемь.
     */
    public ArrayList() {
        array = (T[]) new Object[DEFAULT_CAPACITY];
    }

    /**
     * @param initialCapacity
     * Создает пустой список с указанной начальной емкостью.
     * Параметры:
     * initialCapacity – начальная емкость списка
     * Бросает:
     * Исключение IllegalArgumentException – если указанная начальная емкость отрицательна
     */
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
    /**
     * Возвращает количество элементов в этом списке. Если этот список содержит количество целочисленного значение больше нуля.
     * Возвращается:
     * количество элементов в этом списке
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Возвращается:
     * true, если этот список не содержит элементов
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * @param o
     * Добавляет указанный элемент в конец этого списка.
     * Списки, поддерживающие эту операцию, могут накладывать ограничения на то, какие элементы могут быть добавлены в этот список. В частности, некоторые списки будут отказываться добавлять нулевые элементы, а другие будут налагать ограничения на тип элементов, которые могут быть добавлены. Классы списков должны четко указывать в своей документации любые ограничения на то, какие элементы могут быть добавлены.
     * Параметры:
     * электронный элемент, который будет добавлен к этому списку
     * Возвращается:
     * true
     * Бросает:
     * UnsupportedOperationException – если операция добавления не поддерживается этим списком
     * ClassCastException – если класс указанного элемента препятствует его добавлению в этот список
     * Исключение NullPointerException – если указанный элемент равен null и этот список не допускает нулевых элементов
     * Исключение IllegalArgumentException – если какое-либо свойство этого элемента препятствует его добавлению в этот список
     */
    @Override
    public boolean add(Object o) {
        int numberElementsAdd = 1;
        checkingFullness(numberElementsAdd);
        array[size] = (T) o;
        size++;
        return true;
    }

    /**
     * @param index
     * @param element
     * Вставляет указанный элемент в указанную позицию в этом списке (необязательная операция). Сдвигает элемент, находящийся в данный момент в этой позиции (если таковой имеется), и все последующие элементы вправо (добавляет единицу к их индексам).
     * Параметры:
     * index – индекс, по которому должен быть вставлен указанный элемент element – вставляемый элемент
     * Бросает:
     * UnsupportedOperationException – если операция добавления не поддерживается этим списком
     * ClassCastException – если класс указанного элемента препятствует его добавлению в этот список
     * Исключение NullPointerException – если указанный элемент равен null и этот список не допускает нулевых элементов
     * Исключение IllegalArgumentException – если какое-либо свойство указанного элемента препятствует его добавлению в этот список
     * IndexOutOfBoundsException – если индекс находится вне диапазона (index < 0 || index > size())
     */
    @Override
    public void add(int index, Object element) {
        validIndexObjException(index);
        shiftToRightOfIndex(index);
        array[index] = (T) element;
        size++;
    }

    private boolean validIndexObject(int index) {
        return index >= 0 && index < size;
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

    /**
     * @param o
     * Удаляет первое вхождение указанного элемента из этого списка, если оно присутствует (необязательная операция). Если этот список не содержит элемента, он остается неизменным. Более формально, удаляет элемент с наименьшим индексом i таким образом, что Objects.equals(o, get(i)) (если такой элемент существует). Возвращает значение true, если этот список содержал указанный элемент (или, что эквивалентно, если этот список изменился в результате вызова).
     * Параметры:
     * o – элемент, подлежащий удалению из этого списка, если он присутствует
     * Возвращается:
     * истинно, если этот список содержал указанный элемент
     * Бросает:
     * ClassCastException – если тип указанного элемента несовместим с этим списком (необязательно)
     * Исключение NullPointerException – если указанный элемент равен null и этот список не допускает элементы null (необязательно)
     */
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

    /**
     * @param index
     * Удаляет элемент в указанной позиции в этом списке (необязательная операция). Сдвигает все последующие элементы влево (вычитает единицу из их индексов). Возвращает элемент, который был удален из списка.
     * Параметры:
     * индекс – индекс удаляемого элемента
     * Возвращается:
     * элемент, ранее находившийся в указанном положении
     * Бросает:
     * UnsupportedOperationException – если операция удаления не поддерживается этим списком
     * Исключение IndexOutOfBoundsException – если индекс находится вне диапазона (index < 0 || index >= size())
     */
    @Override
    public Object remove(int index) {

        validIndexObjException(index);
        T o = array[index];
        shiftLeftByIndexCell(index);
        return o;
    }


    private void shiftLeftByIndexCell(int index) {
        for (int i = index; i < size; i++) {
            int nextCell = i + 1;
            array[i] = array[nextCell];
        }
        size--;
    }

    /**
     * Удаляет все элементы из этого списка (необязательная операция). Список будет пуст после возврата этого вызова. Для быстроты операции, метод просто создает новый массив.
     * Бросает:
     * UnsupportedOperationException – если операция очистки не поддерживается этим списком
     */
    @Override
    public void clear() {
        array = (T[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    /**
     * @param index
     * Возвращает элемент в указанной позиции в этом списке.
     * Параметры:
     * index – индекс возвращаемого элемента
     * Возвращается:
     * элемент в указанной позиции в этом списке
     * Бросает:
     * Исключение IndexOutOfBoundsException – если индекс находится вне диапазона (index < 0 || index >= size())
     */
    @Override
    public Object get(int index) {
        if (validIndexObject(index)) return array[index];
        return null;
    }

    /**
     * @param index
     * @param element
     * Заменяет элемент в указанной позиции в этом списке указанным элементом (необязательная операция).
     * Параметры:
     * index – индекс элемента для замены element – элемент, который будет сохранен в указанной позиции
     * Возвращается:
     * элемент, ранее находившийся в указанном положении
     * Бросает:
     * UnsupportedOperationException – если заданная операция не поддерживается этим списком
     * ClassCastException – если класс указанного элемента препятствует его добавлению в этот список
     * Исключение NullPointerException – если указанный элемент равен null и этот список не допускает нулевых элементов
     * Исключение IllegalArgumentException – если какое-либо свойство указанного элемента препятствует его добавлению в этот список
     * Исключение IndexOutOfBoundsException – если индекс находится вне диапазона (index < 0 || index >= size())
     */
    @Override
    public Object set(int index, Object element) {

        validIndexObjException(index);
        T oldElement = array[index];
        array[index] = (T) element;

        return oldElement;
    }


    /**
     * @param o
     * Возвращает значение true, если этот список содержит указанный элемент. Более формально возвращает значение true тогда и только тогда, когда этот список содержит хотя бы один элемент e, такой, что Objects.equals(o, e).
     * Параметры:
     * o – элемент, присутствие которого в этом списке подлежит проверке
     * Возвращается:
     * истинно, если этот список содержит указанный элемент
     * Бросает:
     * ClassCastException – если тип указанного элемента несовместим с этим списком (необязательно)
     */
    @Override
    public boolean contains(Object o) {
        int indexCell = searchObjectInArray(o);
        return indexCell != -1;
    }


    /**
     * Сортирует указанный массив объектов в порядке возрастания в соответствии с естественным расположением его элементов. Все элементы в массиве должны реализовывать сопоставимый интерфейс. Кроме того, все элементы в массиве должны быть взаимно сопоставимы (то есть e1.compareTo(e2) не должен вызывать исключение ClassCastException для любых элементов e1 и e2 в массиве).
     * Такая сортировка гарантированно будет стабильной: равные элементы не будут переупорядочены в результате сортировки.
     * Примечание по реализации: Эта реализация представляет собой быструю сортировку, которая имеет среднюю сложность O (n log n) и является одним из наиболее часто используемых алгоритмов сортировки, особенно для больших объемов данных. При сравнении двух элементов вызывает у них метод toString(), после чего методом compareTo сравниваем результат.
     * Параметры:
     * array – массив, подлежащий сортировке
     * Бросает:
     * ClassCastException – если массив содержит элементы, которые не являются взаимосопоставимыми (например, строки и целые числа)
     * Исключение IllegalArgumentException – (необязательно), если обнаружено, что естественный порядок элементов массива нарушает сопоставимый контракт
     */
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

    /**
     * Возвращает строковое представление содержимого указанного массива. Строковое представление состоит из списка элементов массива, заключенных в квадратные скобки ("[]"). Соседние элементы разделяются символами ", " (запятая, за которой следует пробел). Элементы преобразуются в строки с помощью String.valueOf(float). Возвращает "null", если array пуст.
     * Параметры:
     * array – массив, строковое представление которого необходимо вернуть
     * Возвращается:
     * строковое представление
     */
    public String toString() {
        return Arrays.toString(Arrays.copyOf(array, size));
    }

}
