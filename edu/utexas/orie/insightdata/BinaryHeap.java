package edu.utexas.orie.insightdata;

import java.util.ArrayList;

/**
 * this class implements some functions of binary heap used in running median
 * @author SifengLin
 *
 */
public abstract class BinaryHeap {
	
	/** number of children each node has**/
	protected static final int d = 2;
	
	/**elements of the heap*/
	protected ArrayList<Integer> heap;
	
	/**Constructor*/
	public BinaryHeap(){
		//initialize the size to be 500
		heap = new ArrayList<Integer>(500);
	}
	
	/**
	 * check if the heap is empty
	 * @return true if the heap is empty, and false otherwise;
	 */
	public boolean isEmpty(){
		return heap.size()==0;
	}
	
	/**
	 * get the size of the heap
	 * @return
	 */
	public int getHeapSize(){
		return heap.size();
	}
	
	/**
	 * get the index of parent of index i
	 * @param i
	 * @return
	 */
	private int parent(int i){
		return (i-1)/d;
	}
	
	/**
	 * get the best element in the heap, i.e., the first one
	 * it is the maximum for the max heap, minimum for the min heap
	 * @return
	 */
	public int getBestElement(){
		return heap.get(0);
	}
	
	/**
	 * find the first element, i.e., the min for max heap, and max for max heap of @heap
	 * @return
	 */
	public int findRoot(){
		if(isEmpty()){
			throw new NullPointerException("The heap is empty");
		}
		
		return heap.get(0);
	}
	
	/**
	 * sift up the node of specific index
	 * @param childIndex the index of the node to siftup
	 */
	private void siftUp(int childIndex){
		int temp = heap.get(childIndex);
		//keep sifting up until the parent is smaller
		while(childIndex > 0 && isBetter(temp, heap.get(parent(childIndex)))  ){
			int parentIndex = parent(childIndex);
			heap.set(childIndex, heap.get(parentIndex));
			childIndex = parentIndex;
		}
		
		heap.set(childIndex, temp);
	}
	
	/**
	 * delete the best element, i.e., the first one
	 * @return
	 */
	public int deleteBest(){
		return delete(0);
	}
	
	/**
	 * delete the element, given a specific index 
	 * @param index the index of the removed value
	 * @return the value removed
	 */
	public int delete(int index){
		if(index > getHeapSize()){
			throw new ArrayIndexOutOfBoundsException("the index is too large");
		}
		
		int value = heap.get(index);
		heap.set(index, heap.get(heap.size()-1));
		
		heap.remove(heap.size()-1);
		siftDown(index);
		
		return value;
		
	}
	
	
	/**
	 * sift down the node of a specific index
	 * @param index the index of node to sift down
	 */
	private void siftDown(int index){
		int bestChildIndex;
		int temp = heap.get(index);

		//searching for the heap size
		while(d*index+1 < getHeapSize()){
			bestChildIndex = getBestChildIndex(index);
			//if the best child index is better, swap
			if( isBetter(heap.get(bestChildIndex), temp) ){
				heap.set(index, heap.get(bestChildIndex));
			}
			else{  //otherwise, stop the search, the heap is in order
				break;
			}
			
			index = bestChildIndex;
		}
		
		heap.set(index, temp);
		
	}
	
	/**
	 * get the best child index, 
	 * for max heap, it would be the max child index
	 * for min heap, it would be the min child index
	 * @index the index of the child
	 * @return
	 */
	private int getBestChildIndex(int index){
		int bestIndex = d*index + 1;
		int bestValue = heap.get(bestIndex);
		
		//get the max index for its children
		int maxI = Math.min(d*index + d, getHeapSize()-1);
		
		//find the best possible child
		for(int i=d*index + 2; i<= maxI; i++){
			if(isBetter(heap.get(i), bestValue) ){
				bestIndex = i;
				bestValue = heap.get(bestIndex);
			}
		}
		
		return bestIndex;
	}
	
	/**
	 * insert a new element to the heap
	 * @param x the element to be inserted
	 */
	public void insertNewElement(int x){
		//add an element to the end, and the sift it up
		heap.add(x);
		siftUp(getHeapSize()-1);
	}
	
	/**
	 * For min heap, we check first < second
	 * For max heap, we check first > second
	 * @return true if the first element is better than the second one
	 */
	protected abstract boolean isBetter(int first, int second);
}
