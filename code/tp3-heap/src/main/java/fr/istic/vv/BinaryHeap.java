package fr.istic.vv;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.NoSuchElementException;

class BinaryHeap<T> {	
	
    private ArrayList<T> heap;
    private Comparator<T> cmp;

    public BinaryHeap(Comparator<T> cmp) {
        this.heap=new ArrayList<>();
        this.cmp=cmp;
    }

    /**
     * Removes and returns the minimum element from the heap.
     * @return The minimum element from the heap.
     * @throws NoSuchElementException if the heap is empty.
     */
    
    public T pop() {
        if (heap.isEmpty()) {
            throw new NoSuchElementException("Heap is empty");
        }
        T minElem =heap.get(0);
        T lastElem =heap.remove(heap.size() - 1);
        if (!heap.isEmpty()) {
            heap.set(0,lastElem);
            down(0);  
        }
        return minElem;
    }

    /**
     * Retrieves the minimum element from the heap.
     * @return The minimum element from the heap.
     * @throws NoSuchElementException if the heap is empty.
     */
    public T peek() {
        if (heap.isEmpty()) {
            throw new NoSuchElementException("Heap is empty");
        }
        return heap.get(0);  
    }

    /**
     * Adds a new element to the heap and maintaining the heap property.
     * @param elem The element to be added to the heap.
     */
    public void push(T elem) {
        heap.add(elem);
        up(heap.size()-1);  
    }
    

    /**
     * Returns the number of elements in the heap.
     * @return The number of elements in the heap.
     */
    public int count() {
        return heap.size();
    }

    /**
     * Swaps the elements at the specified indices in the heap.
     * @param i The index of the first element to swap.
     * @param j The index of the second element to swap.
     */
    private void swap(int i, int j) {
        T temp =heap.get(i);
        heap.set(i,heap.get(j));
        heap.set(j,temp);
    }

    
    /**
     * Restores the heap property by moving the element at the specified index up the heap.
     * @param idx The index of the element to move up.
     */
    private void up(int i) {
        int parentIdx = (i-1)/2;
        while ((i>0) && cmp.compare(heap.get(i), heap.get(parentIdx))<0) {
            swap(i,parentIdx);  
            i =parentIdx;
            parentIdx= (i-1)/2;
        }
    }

    /**
     * Restores the heap property by moving the element at the specified index down the heap.
     * @param idx The index of the element to move down.
     */
    private void down(int i) {
        int size=heap.size();
        while (true) {
            int leftIdx = (2*i)+1;
            int rightIdx = (2*i)+2;
            int smallest =i;

            if ((leftIdx<size) && cmp.compare(heap.get(leftIdx), heap.get(smallest))<0) {
            	smallest=leftIdx;
            }

            if ((rightIdx<size) && cmp.compare(heap.get(rightIdx), heap.get(smallest))<0) {
            	smallest=rightIdx;
            }

            if (smallest==i) {
                break;
            }

            swap(i,smallest);  
            i= smallest;
        }
    }
}
