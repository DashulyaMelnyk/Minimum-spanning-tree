package p3;

import java.util.Arrays;
/**
 * Represents the minimum heap that saves generic type elements. Extends Comparable that allows to use method sort for it.
 * @author Daria Melnyk
 */
public class MinHeap<E extends Comparable<E>> {
    private E[] array;
    private int size;
    /**
     * Creates Minimum heap of particular size
     * @param capacity - the size of array
     */
    @SuppressWarnings("uncheked")
    public MinHeap(int capacity) {
        array = (E[]) new Comparable[capacity];
        size = 0;
    }
    /**
     * Inserts an element in array
     * @param item - an element that should de added in heap
     */
    public void addToArray(E item) {
        E[] arr = (E[]) new Comparable[size+1];
        for (int i = 0; i < size; i++){
            arr[i] = array[i];
        }
        arr[size++] = item;
        array = arr;
    }

    /**
     * Removes minimum element from heap that is the first
     * @return minimum element
     */
    @SuppressWarnings("uncheked")
    public E removeMin(){
        E r = array[0];
        E[] arr = (E[]) new Comparable[size-1];
        for (int i = 0; i < size-1; i++){
            arr[i] = array[i+1];
        }
        array = arr;
        size--;
        return r;
    }


    /**
     * Checks if the heap has no elements inside
     * @return true if the heap size is 0
     */
    public boolean isEmpty(){
        return size == 0;
    }

    /**
     * Sorts an array in an increasing way
     */
    public void sort() {
        Arrays.sort(array);
    }

    /**
     * Gets the array of elements
     * @return array of generics
     */
    public E[] getArray() {
        return array;
    }
}