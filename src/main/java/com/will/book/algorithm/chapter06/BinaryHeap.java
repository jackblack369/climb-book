package com.will.book.algorithm.chapter06;

/**
 * Created by 2YSP on 2017/12/30.
 * 二叉堆（实现优先队列）
 */
public class BinaryHeap <AnyType extends Comparable<? super AnyType >> {

    private static final int DEFAULT_CAPACITY = 10;

    private int currentSize;
    private AnyType[] array;// the heap array

    public BinaryHeap(){
        this(DEFAULT_CAPACITY);
    }

    public BinaryHeap(int capacity){
        currentSize = 0;
        array = (AnyType[]) new Comparable[capacity + 1];
    }

    /**
     * construct the binary heap given an array of items
     * @param items
     */
    public BinaryHeap(AnyType[] items){
        currentSize = items.length;
        array = (AnyType[]) new Comparable[(currentSize + 2)*11/10];
        int i = 1;
        for(AnyType item : items){
            array[i++] = item;
        }

        buildHeap();
    }


    public void insert(AnyType x){
        if (currentSize == array.length-1){
            enlargeArray(array.length * 2 + 1);
        }

        //上滤
        int hole = ++currentSize;
        for (array[0]=x;x.compareTo(array[hole/2]) < 0;hole /= 2){
            array[hole] = array[hole/2];
        }
        array[hole] = x;
    }
    //扩容
    private void enlargeArray(int newSize) {
        AnyType[] old = array;
        array = (AnyType[]) new Comparable[newSize];
        for (int i =0;i<old.length;i++){
            array[i] = old[i];
        }
    }

    public AnyType findMin(){
        if (isEmpty()){
            throw new IllegalStateException();
        }
        return array[1];//返回根
    }

    /**
     * Find the smallest item in the priority queue
     * @return the smallest item or throw an UnderflowException if empty.
     */
    public AnyType deleteMin(){
        if (isEmpty()){
            throw new IllegalStateException();
        }
        AnyType minItem = findMin();
        array[1] = array[currentSize--];
        percolateDown(1);
        return minItem;
    }

    private boolean isEmpty() {
        return currentSize == 0;
    }

    public void makeEmpty(){
        currentSize = 0;
    }

    /**
     * Establish heap order property from an arbitrary
     * arrangement of items. Runs in linear time.
     */
    private void buildHeap() {
        for (int i = currentSize/2 ; i>0;i--){
            percolateDown(i);
        }
    }

    /**
     * Internal method to percolate down in the heap.
     * @param hole the index at which the percolate begins.
     */
    private void percolateDown(int hole) {
        int child;
        AnyType tmp = array[hole];
        for(;hole*2<=currentSize;hole = child){
            child = hole * 2;
            if (child != currentSize && array[child+1].compareTo(array[child]) < 0){
                child ++ ;
            }

            if (array[child].compareTo(tmp) < 0){
                array[hole] = array[child];
            }else {
                break;
            }
        }

        array[hole] = tmp;
    }


    // Test program
    public static void main( String [ ] args )
    {
        int numItems = 10000;
        BinaryHeap<Integer> h = new BinaryHeap<>( );
        int i = 37;

        for( i = 37; i != 0; i = ( i + 37 ) % numItems )
            h.insert( i );
        for( i = 1; i < numItems; i++ )
            if( h.deleteMin( ) != i )
                System.out.println( "Oops! " + i );
    }
}
