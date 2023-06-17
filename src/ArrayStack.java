import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;

/**
 * this class represents stack
 * @param <E> class that implements the cloneable interface
 */
public class ArrayStack<E extends Cloneable> implements Stack<E>{
    private Cloneable[] stackArray;// an array of cloneable objects
    private final int maxCapacity;//the max capacity of the stack
    private int size; // the actual size of the stack
    private int head_Index;//the index of the had object in the stack

    /**
     * The arrayStack constructor
     * @param maxCapacity the max capacity of the stack
     * @throws NegativeCapacityException if the max capacity is negative
     */
    public ArrayStack(int maxCapacity) {
        if(maxCapacity<0)
            throw new NegativeCapacityException();
        this.maxCapacity = maxCapacity;
        this.stackArray=new Cloneable[maxCapacity];
    }

    /**
     * push an object to the stack
     * @param element the object we push to the stack
     * @throws StackOverflowException if the stack is full
     */
    @Override
    public void push(E element) {
        if(size<maxCapacity) {
            stackArray[size++] = element;
            head_Index=size-1;
        }
        else throw new StackOverflowException();

    }

    /**
     * it retrieves the head object and remove it from the stack
     * @return the head object
     * @throws EmptyStackException if the stack is empty
     */
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

    /**
     * it retrieves the head object in the stack
     * @return the head object
     * @throws  if the stack is empty it throws an exception
     */
    @Override
    public E peek() {
        if(size>0){
            return (E) stackArray[head_Index];
        }
        throw new EmptyStackException();
    }

    /**
     * check the actual size of the stack
     * @return the actual size
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * check if the stack is empty
     * @return true if empty else false
     */
    @Override
    public boolean isEmpty() {
        return size==0;
    }

    /**
     * clones the stack
     * @return a deep copy of the stack
     */
    @Override
    public ArrayStack<E> clone() {
        try {
            ArrayStack<E> copyStack = (ArrayStack<E>) super.clone();
            Cloneable[] copyArr = stackArray.clone();
            for (int i=0;i<size;i++) {
                copyArr[i] = (Cloneable) stackArray[i].getClass().getMethod("clone").invoke(stackArray[i]);
                //using invoke because we dont know if the clone() method of the object is protected or not
            }
            copyStack.stackArray = copyArr;
            return copyStack;
        } catch (CloneNotSupportedException | NoSuchMethodException | IllegalAccessException |
                 InvocationTargetException exception) {
            return null;
        }
    }

    /**
     * it creates an iterator of the stack array
     * @return an iterator
     */
    @Override
    public Iterator<E> iterator() {
        return new StackIterator();
    }

    /**
     * an inner class of the stackArrray that represents an iterator object
     */
    public class StackIterator implements Iterator<E>{
        private int Index;//the index of the next object

        /**
         * intialize the index to be the stack size minus 1
         */
        public StackIterator() {
            this.Index =size-1;
        }

        /**
         * check if there is next element in the stack
         * @return true if there is next element else false
         */
        @Override
        public boolean hasNext() {
            return Index >=0;
        }

        /**
         * it returnes the next element
         * @return an object from the class
         */
        @Override
        public E next() {
            return (E)stackArray[Index--];
        }
    }
}
