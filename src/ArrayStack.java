import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;


public class ArrayStack<E extends Cloneable> implements Stack<E>{
    private Cloneable[] stackArray;
    private final int maxCapacity;
    private int size;
    private int head_Index;

    public ArrayStack(int maxCapacity) {
        if(maxCapacity<0)
            throw new NegativeCapacityException();
        this.maxCapacity = maxCapacity;
        this.stackArray=new Cloneable[maxCapacity];
    }

    @Override
    public void push(E element) {
        if(size<maxCapacity) {
            stackArray[size++] = element;
            head_Index=size-1;
        }
        else throw new StackOverflowException();

    }
    @Override
    public E pop() {
        if (size != 0) {
            E val = (E) stackArray[head_Index];
            stackArray[head_Index--] = null;
            size--;
            return val;
        }
        throw new EmptyStackException();
    }

    @Override
    public E peek() {
        if(size>0){
            return (E) stackArray[head_Index];
        }
        throw new EmptyStackException();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    @Override
    public ArrayStack<E> clone() {
        try {
            ArrayStack<E> copyStack = (ArrayStack<E>) super.clone();
            Cloneable[] copyArr = stackArray.clone();
            for (int i=0;i<size;i++) {
                copyArr[i] = (Cloneable) stackArray[i].getClass().getMethod("clone").invoke(stackArray[i]);
            }
            copyStack.stackArray = copyArr;
            return copyStack;
        } catch (CloneNotSupportedException | NoSuchMethodException | IllegalAccessException |
                 InvocationTargetException exception) {
            return null;
        }
    }
    @Override
    public Iterator<E> iterator() {
        return new StackIterator();
    }
    private class StackIterator implements Iterator<E>{
        private int Index;

        public StackIterator() {
            this.Index =size-1;
        }

        @Override
        public boolean hasNext() {
            return Index >=0;
        }

        @Override
        public E next() {
            return (E)stackArray[Index--];
        }
    }
}
